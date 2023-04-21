package hwr.oop.todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVCreate {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";

    public static void writeFile(String projectName, ArrayList<String> tasks, String date, String filePath) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePath);

            // Write details to the file
            fileWriter.append(projectName);
            for (String task : tasks) {
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(task);
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(date);
            fileWriter.append(LINE_SEPARATOR);

            System.out.println("Successfully created CSV-File!");

        } catch (IOException e) {
            System.out.println("Error in CSV-File creation!");
            e.printStackTrace();

        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException e) {
                System.out.println("Error while flushing or closing fileWriter!");
                e.printStackTrace();
            }
        }
    }
}