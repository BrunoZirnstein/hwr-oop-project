package hwr.oop.todo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {

    private static String testFilePath;
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";
    private static final List<String[]> testProjectDetails = Arrays.asList(
            new String[][]{
                    {"Project 1", "Task 1,Task 2,Task 3", "2022-04-23"},
                    {"Project 2", "Task 2,Task 3,Task 4,Task 5", "2022-04-24"},
            });

    @BeforeAll
    public static void createTestFile() throws IOException {
        File testFile = new File("test.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        for (String[] projectDetail : testProjectDetails) {
            writer.write(String.join(COMMA_DELIMITER, projectDetail));
            writer.write(LINE_SEPARATOR);
        }
        writer.close();
        testFilePath = testFile.getAbsolutePath();
    }

    @AfterAll
    public static void deleteTestFile() {
        File testFile = new File(testFilePath);
        testFile.delete();
    }

    @Test
    void testReadCSVFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> {
            CSVReader.readCSV("nonexistent_file.csv");
        });
    }

    @Test
    void testReadCSVEmptyFile() throws IOException {
        String fileName = "empty_file.csv";
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        writer.close();
        List<String[]> projectDetails = CSVReader.readCSV("empty_file.csv");
        assertTrue(projectDetails.isEmpty());
    }

    @Test
    void testAddElement() {
        String[] arr = new String[]{"a", "b", "c"};
        String[] result = CSVReader.addElement(arr, "d");
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, result);
    }

    @Test
    @DisplayName("Test readCSV method with valid file path")
    void testReadCSVWithValidFilePath() throws FileNotFoundException {
        String filePath = "test.csv";
        List<String[]> projectDetails = CSVReader.readCSV(filePath);
        String[][] expectedProjectDetails = new String[][]{
                {"Project 1", "Task 1, Task 2, Task 3", "2022-04-23"},
                {"Project 2", "Task 2, Task 3, Task 4, Task 5", "2022-04-24"}
        };
        assertArrayEquals(expectedProjectDetails, projectDetails.toArray());
    }

    @Test
    @DisplayName("Test readCSV method with invalid file path")
    void testReadCSVWithInvalidFilePath() {
        String filePath = "invalid/file/path.csv";
        assertThrows(FileNotFoundException.class, () -> CSVReader.readCSV(filePath));
    }
}


