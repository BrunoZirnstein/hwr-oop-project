package hwr.oop.todo;

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

    public static String getFilePathTodo() {
        return FILEPATHTODO;
    }


    public String getFilePathProject() {
        return FILEPATHPROJECT;
    }


    public static void writeToDoFile(Task task, ToDoList todo,
                                     String filePathTodo) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePathTodo, true)) {
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
            fileWriter.append(todo.user());
            fileWriter.append(LINE_SEPARATOR);

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Creation!");
        }
    }

    public static void writeProjectFile(Project project,
                                        String filePathProject) throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(filePathProject),
                true)) {
            fileWriter.append(project.title());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(project.deadline().toString());
            fileWriter.append(LINE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Creation!");
        }
    }

}
