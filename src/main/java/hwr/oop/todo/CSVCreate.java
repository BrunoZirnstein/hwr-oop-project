package hwr.oop.todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVCreate {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";

    public static void writeFile(String projectName, List<String> tasks, String date, String filePath) throws IOException {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePath);

            fileWriter.append(projectName);
            for (String task : tasks) {
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(task);
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(date);
            fileWriter.append(LINE_SEPARATOR);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Successfully created CSV-File!");

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Creation!");
        }
    }
}