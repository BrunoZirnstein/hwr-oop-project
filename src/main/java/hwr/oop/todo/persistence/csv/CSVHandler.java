package hwr.oop.todo.persistence.csv;

import hwr.oop.todo.core.*;
import hwr.oop.todo.persistence.PersistenceAdapter;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CSVHandler implements PersistenceAdapter {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEMICOLON_DELIMITER = ";";
    private static final String ERROR_CSV = "Error in CSV-Creation!";
    private String filePathToDoUser = "ToDo_List.csv";
    private String filePathProject = "Project_List.csv";
    private String filePathTask = "Task_List.csv";

    public void setFilePathToDoUser(String filePathToDoUser) {
        this.filePathToDoUser = filePathToDoUser;
    }

    public void setFilePathProject(String filePathProject) {
        this.filePathProject = filePathProject;
    }

    public void setFilePathTask(String filePathTask) {
        this.filePathTask = filePathTask;
    }

    public String getFilePathTodo() {
        return filePathToDoUser;
    }

    public String getFilePathTask() {
        return filePathTask;
    }

    public String getFilePathProject() {
        return filePathProject;
    }

    void saveNewTask(Task task, ToDoList todo) throws IOException {
        try (FileWriter fileWriter = new FileWriter(getFilePathTask(), true)) {
            fileWriter.append(task.title());
            fileWriter.append(COMMA_DELIMITER);
            if (task.description() != null) {
                fileWriter.append(task.description());
            }
            fileWriter.append(COMMA_DELIMITER);

            if (task.tags() != null) {
                int size = task.tags().size();
                for (int i = 0; i < size; i++) {
                    TaskTag tag = task.tags().get(i);
                    fileWriter.append(tag.title());
                    if (i != size - 1) {
                        fileWriter.append(SEMICOLON_DELIMITER);
                    }
                }
            }
            fileWriter.append(COMMA_DELIMITER);

            if (task.deadline() != null) {
                fileWriter.append(task.deadline().toString());
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(task.status().name());
            fileWriter.append(COMMA_DELIMITER);
            if (task.priority() != null) {
                fileWriter.append(task.priority().name());
            }
            fileWriter.append(COMMA_DELIMITER);
            Optional<String> projectName = task.projectName();
            if (projectName.isPresent()) {
                fileWriter.append(projectName.get());
            }
            fileWriter.append(COMMA_DELIMITER);
            Optional<String> owner = todo.owner();
            if (owner.isPresent()) {
                fileWriter.append(owner.get());
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(todo.id().toString());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(task.id().toString());
            fileWriter.append(LINE_SEPARATOR);
        } catch (IOException e) {
            throw new IOException(ERROR_CSV, e);
        }
    }

    void saveNewToDoList(ToDoList toDoList) throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(getFilePathTodo()),
                true)) {
            Optional<String> owner = toDoList.owner();
            if (owner.isPresent()) {
                fileWriter.append(owner.get());
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(toDoList.id().toString());
            fileWriter.append(LINE_SEPARATOR);
        } catch (IOException e) {
            throw new IOException(ERROR_CSV, e);
        }
    }

    void saveNewProject(Project project, ToDoList todo) throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(getFilePathProject()),
                true)) {
            fileWriter.append(project.title());
            fileWriter.append(COMMA_DELIMITER);
            Optional<LocalDate> deadline = Optional.ofNullable(project.deadline());
            if (deadline.isPresent()) {
                fileWriter.append(deadline.get().toString());
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(todo.id().toString());
            fileWriter.append(LINE_SEPARATOR);
        } catch (IOException e) {
            throw new IOException(ERROR_CSV, e);
        }
    }

    ToDoList loadAToDoListFromUserWithAllTasks(ToDoList todo) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader(getFilePathTask()))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                String title = values.get(0);
                String description = null;
                if (!values.get(1).isEmpty()) {
                    description = values.get(1);
                }
                List<TaskTag> tags = new ArrayList<>();
                if (!values.get(2).isEmpty()) {
                    String[] tagValues = values.get(2).split(";");
                    for (String tagValue : tagValues) {
                        tags.add(new TaskTag(tagValue));
                    }
                }
                LocalDate deadline = null;
                if (!values.get(3).isEmpty()) {
                    deadline = LocalDate.parse(values.get(3));
                }
                TaskStatus status = TaskStatus.valueOf(
                        values.get(4).toUpperCase());
                // Should be changed if all Tasks have a priority!
                TaskPriority priority = null;
                if (values.get(5).isEmpty()) {
                    priority = null;
                } else {
                    priority = TaskPriority.valueOf(
                            values.get(5).toUpperCase());
                }
                Optional<String> projectName = Optional.empty();
                if (!values.get(6).isEmpty()) {
                    projectName = Optional.ofNullable(values.get(6));
                    for (Project pro : todo.projects()) {
                        if (pro.title().equals(projectName.orElse(null))) {
                            todo.removeProject(projectName.orElse(null));
                            break;
                        }
                    }
                    todo = createAllSavedProjects(projectName.orElse(null), todo);
                }
                Optional<String> owner = Optional.empty();
                if ((!values.get(7).isEmpty()) && values.get(8).equals(todo.id().toString())) {
                    owner = Optional.of(values.get(7));
                    todo.updateOwner(owner.orElse(null));
                }
                if (values.get(8).equals(todo.id().toString())) {
                    String idBeforeEdit = values.get(9);
                    String taskIdString = idBeforeEdit.substring(idBeforeEdit.indexOf('=') + 1, idBeforeEdit.lastIndexOf(']'));
                    UUID taskUUID = UUID.fromString(taskIdString);
                    TaskId id = new TaskId(taskUUID);
                    Task task = new Task.Builder(title)
                            .id(id)
                            .description(description)
                            .tags(tags)
                            .deadline(deadline)
                            .status(status)
                            .priority(priority)
                            .projectName(projectName.orElse(null))
                            .build();
                    todo.addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + getFilePathTask(), e);
        }
        return todo;
    }

    ToDoList loadAllProjectsFromUser(ToDoList toDoList) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(getFilePathProject()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                String title = values.get(0);
                LocalDate deadline = LocalDate.parse(values.get(1));
                if (values.get(2).equals(toDoList.id().toString())) {
                    Project project = new Project.Builder(title).deadline(deadline).build();
                    toDoList.addProject(project);
                }
            }
        }
        return toDoList;
    }

    ToDoList createAllSavedProjects(String projectName, ToDoList toDoList) throws IOException {
        Project project = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(getFilePathProject()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                String title = values.get(0);
                LocalDate deadline = LocalDate.parse(values.get(1));
                if (values.get(2).equals(toDoList.id().toString()) && values.get(0).equals(projectName)) {
                    project = new Project.Builder(title).deadline(deadline).build();
                    toDoList.addProject(project);
                }
            }
        }
        return toDoList;
    }

    String getOwnerFromID(ToDoList toDoList) throws IOException{
        String owner = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(getFilePathTodo()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                String idBeforeEdit = values.get(1);
                String todoIdString = idBeforeEdit.substring(idBeforeEdit.indexOf('=') + 1, idBeforeEdit.lastIndexOf(']'));
                UUID todoUUID = UUID.fromString(todoIdString);
                if (todoUUID.equals(toDoList.id().id())) {
                    owner = values.get(0);
                }
            }
            return owner;
        }
    }

    List<ToDoList> loadAIdAndToDoListFromUser(String username) throws IOException {
        List<ToDoList> userToDos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(getFilePathTodo()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                if (values.get(0).equals(username)) {
                    String idBeforeEdit = values.get(1);
                    String id = idBeforeEdit.substring(idBeforeEdit.indexOf('=') + 1, idBeforeEdit.lastIndexOf(']'));
                    ToDoListId listId = new ToDoListId(UUID.fromString(id));
                    ToDoList list = new ToDoList(listId);
                    userToDos.add(list);
                }
            }
        }
        return userToDos;
    }

    void removeTaskByID(String iD) throws IOException {
        File inputFile = new File(getFilePathTask());
        File tempFile = new File(getFilePathTask() + "tasktemp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> fields = Arrays.asList(line.split(COMMA_DELIMITER));

                if (!fields.isEmpty() && fields.size() >= 9 && !fields.get(8).equals(iD)) {
                    writer.write(line);
                    writer.write(LINE_SEPARATOR);
                }
            }
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    void removeProjectByID(String iD) throws IOException {
        File inputFile = new File(getFilePathProject());
        File tempFile = new File(getFilePathProject() + "projecttemp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> fields = Arrays.asList(line.split(COMMA_DELIMITER));

                if (!fields.isEmpty() && fields.size() >= 3 && !fields.get(2).equals(iD)) {
                    writer.write(line);
                    writer.write(LINE_SEPARATOR);
                }
            }
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    void removeToDoListByID(String iD) throws IOException {
        File inputFile = new File(getFilePathTodo());
        File tempFile = new File(getFilePathTodo() + "todotemp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> fields = Arrays.asList(line.split(COMMA_DELIMITER));

                if (!fields.isEmpty() && fields.size() >= 2 && !fields.get(1).equals(iD)) {
                    writer.write(line);
                    writer.write(LINE_SEPARATOR);
                }
            }
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }


    public void overwriteList(ToDoList toDoList) {
        try {
            removeProjectByID(toDoList.id().toString());
            for (int i = 0; i < toDoList.projects().size(); i++) {
                Project element = toDoList.projects().get(i);
                saveNewProject(element, toDoList);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading / saving the project file", e);
        }
        try {
            removeTaskByID(toDoList.id().toString());
            for (int i = 0; i < toDoList.tasks().size(); i++) {
                Task element = toDoList.tasks().get(i);
                saveNewTask(element, toDoList);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading / saving the task file", e);
        }
        try {
            removeToDoListByID(toDoList.id().toString());
            saveNewToDoList(toDoList);
        } catch (IOException e) {
            throw new RuntimeException("Error reading / saving the todo file",e);
        }
    }

    public ToDoList loadListById(ToDoListId id) {
        ToDoList todo = new ToDoList(id);
        try {
            todo = loadAToDoListFromUserWithAllTasks(todo);
            String owner = getOwnerFromID(todo);
            todo.updateOwner(owner);
            return todo;
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file", e);
        }
    }

}