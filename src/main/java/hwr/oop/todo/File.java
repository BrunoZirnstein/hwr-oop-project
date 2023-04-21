package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;

public class File {

    public static void createCSV(String projectName, ArrayList<String> tasks, String date, String filePath) {

        CSVCreate.writeFile(projectName, tasks, date, filePath);
    }

    public static List<String[]> readCSV(String filePath) {
        return CSVReader.readCSV(filePath);
    }
}