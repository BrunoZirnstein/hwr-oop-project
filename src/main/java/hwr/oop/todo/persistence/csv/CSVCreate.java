package hwr.oop.todo.persistence.csv;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskTag;
import hwr.oop.todo.core.ToDoList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class CSVCreate {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEMICOLON_DELIMITER = ";";

    private static final String FILEPATHTODO = "ToDo_List.csv";
    private static final String FILEPATHPROJECT = "Project_List.csv";
    private static final String FILEPATHTASK = "Task_List.csv";

    private static final String ERROR_CSV = "Error in CSV-Creation!";
    public static String getFilePathTodo() {
        return FILEPATHTODO;
    }
    public static String getFilePathTask() { return FILEPATHTASK; }
    public static String getFilePathProject() {
        return FILEPATHPROJECT;
    }

    public static void writeQuickTaskFile(Task task, ToDoList todo, String filePathTask) throws IOException{
        try (FileWriter fileWriter = new FileWriter(filePathTask, true)) {
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

    public static void writeTaskFile(Task task, ToDoList todo,
                                     String filePathTask) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePathTask, true)) {
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

    public static void writeToDoListFile(ToDoList toDoList, String filePathToDo) throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(filePathToDo),
                true)) {
            fileWriter.append(toDoList.owner());
            fileWriter.append(LINE_SEPARATOR);
        } catch (IOException e) {
            throw new IOException(ERROR_CSV, e);
        }
    }

    public static void writeProjectFile(Project project, ToDoList todo,
                                        String filePathProject) throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(filePathProject),
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

}