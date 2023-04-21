package hwr.oop.todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static final String COMMA_DELIMITER = ",";

    public static List<String[]> readCSV(String filePath) {
        List<String[]> projectDetails = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String projectName = data[0];
                ArrayList<String> tasks = new ArrayList<>();
                for (int i = 1; i < data.length - 1; i++) {
                    tasks.add(data[i]);
                }
                String date = data[data.length - 1];
                projectDetails.add(new String[]{projectName, String.join(", ", tasks), date});
            }
        } catch (IOException e) {
            System.out.println("Error in CSVReader!");
            e.printStackTrace();
        }

        return projectDetails;
    }

    public static String[] addElement(String[] arr, String element) {
        String[] result = new String[arr.length + 1];
        System.arraycopy(arr, 0, result, 0, arr.length);
        result[arr.length] = element;
        return result;
    }
}