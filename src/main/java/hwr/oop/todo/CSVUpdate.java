package hwr.oop.todo;

import java.io.*;

public class CSVUpdate {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void removeTask(String taskTitle, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("tasktemp.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(COMMA_DELIMITER);

                if (fields.length > 0 && !fields[0].equals(taskTitle)) {
                    writer.write(line);
                    writer.write(LINE_SEPARATOR);
                }
            }
        }
        try {
            if (!inputFile.delete()) {
                throw new IOException("Failed to delete the original file: " + filePath);
            }
            if (!tempFile.renameTo(inputFile)) {
                throw new IOException("Failed to rename the temporary file to the original file: " + filePath);
            }
        } catch (IOException e) {
            throw e;
        }
    }
    public static void updateTask(Task task, String oldTaskTitle, ToDoList todo, String filePath) throws IOException {
        removeTask(oldTaskTitle, filePath);
        CSVCreate.writeTaskFile(task, todo, filePath);
    }
    public static void removeProject(String projectTitle, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("projecttemp.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(COMMA_DELIMITER);

                if (fields.length > 0 && !fields[0].equals(projectTitle)) {
                    writer.write(line);
                    writer.write(LINE_SEPARATOR);
                }
            }
        }
        try {
            if (!inputFile.delete()) {
                throw new IOException("Failed to delete the original file: " + filePath);
            }
            if (!tempFile.renameTo(inputFile)) {
                throw new IOException("Failed to rename the temporary file to the original file: " + filePath);
            }
        } catch (IOException e) {
            throw e;
        }
    }
    public static void updateProject(Project project, String oldProjectTitle, ToDoList todo, String filePath) throws IOException {
        removeProject(oldProjectTitle, filePath);
        CSVCreate.writeProjectFile(project,todo,filePath);
    }
}
