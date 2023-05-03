package hwr.oop.todo;

import java.io.*;

public class CSVCreate {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";
    private static final String SEMICOLON_DELIMITER = ";";
    public static String FILEPATHTODO ="TODO_List.csv";
    public static String FILEPATHPROJECT  ="Project_List.csv";

    public static void writeToDoFile(Task task, ToDo todo) throws IOException {
        FileWriter fileWriter = null;

        try {
            File file = new File(FILEPATHTODO);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);

            fileWriter.append(task.title());
            fileWriter.append(COMMA_DELIMITER);
            if (task.description() != null){
                fileWriter.append(task.description());
            }
            fileWriter.append(COMMA_DELIMITER);

            if (task.tags() != null){
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

            if (task.deadline() != null){
                fileWriter.append(task.deadline().toString());
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(task.status().name());
            fileWriter.append(COMMA_DELIMITER);
            if (task.priority() != null){
                fileWriter.append(task.priority().name());
            }
            fileWriter.append(COMMA_DELIMITER);
            if (task.projectName().isPresent()) {
                fileWriter.append(task.projectName().get());
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(todo.user());
            fileWriter.append(LINE_SEPARATOR);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Successfully created CSV-Task-File!");

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Creation!");
        }
    }

    public static void writeProjectFile(Project project) throws IOException {
        FileWriter fileWriter = null;

        try {
            File file = new File(FILEPATHPROJECT);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);

            fileWriter.append(project.title());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(project.deadline().toString());
            fileWriter.append(LINE_SEPARATOR);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Successfully created CSV-Project-File!");

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Creation!");
        }
    }
}
