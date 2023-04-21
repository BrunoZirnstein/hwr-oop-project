package hwr.oop.todo;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CSVTest.CaptureSystemOutput.class)
class CSVTest {

    private static final String FILE_PATH = "test.csv";

    private List<String[]> testData;

    @BeforeEach
    public void setUp() {
        // Initialize test data
        testData = new ArrayList<>();
        testData.add(new String[] {"Project 1", "Task 1, Task 2", "2023-04-19"});
        testData.add(new String[] {"Project 2", "Task 1", "2023-04-20"});
        testData.add(new String[] {"Project 3", "Task 1, Task 2, Task 3", "2023-04-21"});
    }

    @AfterEach
    public void tearDown() {
        // Delete test file
        java.io.File file = new java.io.File(FILE_PATH);
        file.delete();
    }

    @Test
    void testCSVReader() {
        // Write test data to file
        CSVCreate.writeFile(testData.get(0)[0], new ArrayList<String>(List.of(testData.get(0)[1].split(", "))), testData.get(0)[2], FILE_PATH);

        String filePath = "nonexistent_file.csv";
        IOException exception = assertThrows(IOException.class, () -> {
            CSVReader.readCSV(filePath);
        });
        assertEquals("Error in CSVReader!", exception.getMessage());
        // Read data from file
        List<String[]> result = CSVReader.readCSV(FILE_PATH);

        // Check that data matches test data
        assertEquals(testData, result);


    }

    @Test
    void testWriteFileIOException() {
        FileWriter fileWriter = null;
        String filePath = "C:/test/Exception/csvcreate.csv";
        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            assertThat(e).isInstanceOf(java.io.FileNotFoundException.class);
        }
    }
    @Test
    void testCSVCreate(CaptureSystemOutput outputCapture) {
        // Write test data to file
        CSVCreate.writeFile(testData.get(0)[0], new ArrayList<String>(List.of(testData.get(0)[1].split(", "))), testData.get(0)[2], FILE_PATH);

        // Check that success message is printed to console
        assertEquals("Successfully created CSV-File!\n", outputCapture.toString());
    }

    static class CaptureSystemOutput implements org.junit.jupiter.api.extension.BeforeEachCallback,
            org.junit.jupiter.api.extension.AfterEachCallback {

        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        private PrintStream previousOutput;

        @Override
        public void beforeEach(org.junit.jupiter.api.extension.ExtensionContext context) {
            previousOutput = System.out;
            System.setOut(new PrintStream(outputStream));
        }

        @Override
        public void afterEach(org.junit.jupiter.api.extension.ExtensionContext context) {
            System.setOut(previousOutput);
        }

        public String toString() {
            return outputStream.toString();
        }
        @Test
        void testAddElement() {
            String[] arr = {"a", "b", "c"};
            String element = "d";
            String[] expected = {"a", "b", "c", "d"};
            String[] actual = CSVReader.addElement(arr, element);
            assertArrayEquals(expected, actual);
        }
    }
}
