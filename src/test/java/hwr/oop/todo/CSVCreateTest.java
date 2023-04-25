package hwr.oop.todo;

import hwr.oop.todo.CSVCreate;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVCreateTest {

    @Test
    void testWriteFile() throws IOException {
        String projectName = "Project X";
        List<String> tasks = Arrays.asList("Task 1", "Task 2", "Task 3");
        String date = "2023-04-24";
        String filePath = "createTest.csv";
        CSVCreate.writeFile(projectName, tasks, date, filePath);
        assertTrue(new File(filePath).exists());
        List<String> lines = Files.readAllLines(new File(filePath).toPath());
        assertEquals(1, lines.size());
        assertEquals("Project X,Task 1,Task 2,Task 3,2023-04-24", lines.get(0));
        assertTrue(new File(filePath).delete());
    }
    @Test
    void testWriteFileWithIOException() {
        String projectName = "Project X";
        List<String> tasks = Arrays.asList("Task 1", "Task 2", "Task 3");
        String date = "2023-04-24";
        String filePath = "invalid-path/invalid-file.csv";
        assertThrows(IOException.class, () -> {
            CSVCreate.writeFile(projectName, tasks, date, filePath);
        });
        assertFalse(new File(filePath).exists());
    }
}
