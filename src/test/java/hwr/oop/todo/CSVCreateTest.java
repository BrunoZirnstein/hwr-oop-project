package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVCreateTest {

    @Test
    void testWriteToDoFile() throws IOException {
        String filePathTestFile = "Todo_TEST.csv";
        Project testProject = new Project("university", null);
        Task task = new Task.Builder("Water plants").description("Water all the plants in the living room and in the bedroom.").tags(List.of(new TaskTag("home"), new TaskTag("water"))).deadline(LocalDate.of(2023, 5, 30)).priority(TaskPriority.HIGH).project(testProject).build();
        ToDo list = new ToDo("Bernd");
        CSVCreate.writeToDoFile(task, list, filePathTestFile);
        assertTrue(new File(filePathTestFile).exists());
        List<String> lines = Files.readAllLines(new File(filePathTestFile).toPath());
        assertEquals(1, lines.size());
        assertEquals("Water plants,Water all the plants in the living room and in the bedroom.,home,water,2023-05-30,TODO,HIGH,university,Bernd", lines.get(0));
        assertTrue(new File(filePathTestFile).delete());
    }
    @Test
    void testWriteToDoFileWithIOException() {
        String filePathTestFile = "NOTPOSSIBLE/NOTPOSSIBLE.csv";
        Project testProject = new Project("university", null);
        Task task = new Task.Builder("Water plants").description("Water all the plants in the living room and in the bedroom.").tags(List.of(new TaskTag("home"), new TaskTag("water"))).deadline(LocalDate.of(2023, 5, 30)).priority(TaskPriority.HIGH).project(testProject).build();
        ToDo list = new ToDo("Bernd");
        assertThrows(IOException.class, () -> {
            CSVCreate.writeToDoFile(task, list, filePathTestFile);
        });
        assertFalse(new File(filePathTestFile).exists());
    }

    @Test
    void testWriteProjectFile() throws IOException{
        String filePathTestFile = "Project_TEST.csv";
        Project testProject = new Project("university",LocalDate.of(2023, 5, 30));
        CSVCreate.writeProjectFile(testProject,filePathTestFile);
        assertTrue(new File(filePathTestFile).exists());
        List<String> lines = Files.readAllLines(new File(filePathTestFile).toPath());
        assertEquals(1, lines.size());
        assertEquals("university,2023-05-30",lines.get(0));
        assertTrue(new File(filePathTestFile).delete());
    }

    @Test
    void testWriteProjectFileWithIOException(){
        String filePathTestFile = "NOTPOSSIBLE/NOTPOSSIBLE.csv";
        Project testProject = new Project("university",LocalDate.of(2023, 5, 30));
        assertThrows(IOException.class, () -> {
            CSVCreate.writeProjectFile(testProject, filePathTestFile);
        });
        assertFalse(new File(filePathTestFile).exists());
    }
}
