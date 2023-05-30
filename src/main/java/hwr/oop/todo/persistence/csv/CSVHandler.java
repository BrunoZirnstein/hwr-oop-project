package hwr.oop.todo.persistence.csv;

import hwr.oop.todo.core.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CSVHandler {
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

    public void saveNewQuickTask(Task task, ToDoList todo) throws IOException {
        try (FileWriter fileWriter = new FileWriter(getFilePathTask(), true)) {
            fileWriter.append(task.title());
            for (int i = 0; i < 4; i++) {
                fileWriter.append(COMMA_DELIMITER);
            }
            fileWriter.append(task.status().name());
            for (int i = 0; i < 3; i++) {
                fileWriter.append(COMMA_DELIMITER);
            }
            fileWriter.append(todo.owner());
            fileWriter.append(LINE_SEPARATOR);

        } catch (IOException e) {
            throw new IOException(ERROR_CSV, e);
        }
    }

    public void saveNewTask(Task task, ToDoList todo) throws IOException {
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
            fileWriter.append(todo.owner());
            fileWriter.append(LINE_SEPARATOR);

        } catch (IOException e) {
            throw new IOException(ERROR_CSV, e);
        }
    }

    public void saveNewToDoList(ToDoList toDoList) throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(getFilePathTodo()),
                true)) {
            fileWriter.append(toDoList.owner());
            fileWriter.append(LINE_SEPARATOR);
        } catch (IOException e) {
            throw new IOException(ERROR_CSV, e);
        }
    }

    public void saveNewProject(Project project, ToDoList todo) throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(getFilePathProject()),
                true)) {
            fileWriter.append(project.title());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(project.deadline().toString());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(todo.owner());
            fileWriter.append(LINE_SEPARATOR);
        } catch (IOException e) {
            throw new IOException(ERROR_CSV, e);
        }
    }

    public List<Task> loadAllTasksFromFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(getFilePathTask()))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                String title = values.get(0);
                String description = values.get(1);
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
                    priority = TaskPriority.valueOf("HIGH");
                } else {
                    priority = TaskPriority.valueOf(
                            values.get(5).toUpperCase());
                }
                Optional<String> projectName = Optional.empty();
                if (!values.get(6).isEmpty()) {
                    projectName = Optional.ofNullable(values.get(6));
                }

                Task task = new Task.Builder(title)
                        .description(description)
                        .tags(tags)
                        .deadline(deadline)
                        .status(status)
                        .priority(priority)
                        .projectName(projectName.orElse(null))
                        .build();
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + getFilePathTask(), e);
        }
        return tasks;
    }

    public ToDoList loadAToDoListFromUserWithAllTasks(ToDoList todo) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader(getFilePathTask()))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                String title = values.get(0);
                String description = values.get(1);
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
                    priority = TaskPriority.valueOf("HIGH");
                } else {
                    priority = TaskPriority.valueOf(
                            values.get(5).toUpperCase());
                }
                Optional<String> projectName = Optional.empty();
                if (!values.get(6).isEmpty()) {
                    projectName = Optional.ofNullable(values.get(6));
                }

                if (values.get(7).equals(todo.owner())) {
                    Task task = new Task.Builder(title)
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

    public List<Project> loadAllProjectFromFile() throws IOException {
        List<Project> projects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(getFilePathProject()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(COMMA_DELIMITER);
                String title = fields[0];
                LocalDate deadline = LocalDate.parse(fields[1]);

                Project project = new Project.Builder(title).deadline(deadline).build();
                projects.add(project);
            }
        }

        return projects;
    }

    public List<ToDoList> loadAllToDoListUsersFromFile() throws IOException {
        List<ToDoList> todos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(getFilePathTodo()))) {
            String line;
            while ((line = reader.readLine()) != null) {

                ToDoList todo = new ToDoList(line);
                todos.add(todo);
            }
        }

        return todos;
    }

    public void removeTask(String taskID) throws IOException {
        File inputFile = new File(getFilePathTask());
        File tempFile = new File(getFilePathTask() + "tasktemp.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> fields = Arrays.asList(line.split(COMMA_DELIMITER));

                if (!fields.isEmpty() && fields.get(0) != null && !fields.get(0).equals(taskID)) {
                    writer.write(line);
                    writer.write(LINE_SEPARATOR);
                }
            }
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void removeProject(String projectTitle) throws IOException {
        File inputFile = new File(getFilePathProject());
        File tempFile = new File(getFilePathProject() + "projecttemp.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                boolean firstLine = true;
                while ((line = reader.readLine()) != null) {
                    List<String> fields = Arrays.asList(line.split(COMMA_DELIMITER));

                    if (!fields.isEmpty() && fields.get(0) != null && !fields.get(0).equals(projectTitle)) {
                        if (!firstLine) {
                            writer.write(LINE_SEPARATOR);
                        } else {
                            firstLine = false;
                        }
                        writer.write(line);
                    }
                }
            }
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

}