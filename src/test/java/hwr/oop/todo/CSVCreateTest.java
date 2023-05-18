package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CSVCreateTest {

    private static final String TEST_FILEPATH_TODO = "test_todo.csv";
    private static final String TEST_FILEPATH_PROJECT = "test_project.csv";

    @TempDir
    static Path tempDir;

    @BeforeEach
    void cleanup() throws IOException {
        Files.deleteIfExists(tempDir.resolve(TEST_FILEPATH_TODO));
        Files.deleteIfExists(tempDir.resolve(TEST_FILEPATH_PROJECT));
    }

    @Test
    void testGetFilePathTodo() {
        String expectedFilePath = "ToDo_List.csv";
        String actualFilePath = CSVCreate.getFilePathTodo();
        assertEquals(expectedFilePath, actualFilePath);
    }

    @Test
    void testGetFilePathProject() {
        String expectedFilePath = "Project_List.csv";
        CSVCreate csvCreate = new CSVCreate();
        String actualFilePath = csvCreate.getFilePathProject();
        assertEquals(expectedFilePath, actualFilePath);
    }

    @Test
    void testWriteToDoFile() throws IOException {
        Task task = new Task.Builder("Test Task")
                .description("This is a test task")
                .tags(List.of(new TaskTag("test"), new TaskTag("test 2")))
                .deadline(LocalDate.of(2023, 5, 7))
                .status(TaskStatus.TODO)
                .priority(TaskPriority.HIGH)
                .projectName("Test Project")
                .build();
        ToDoList todo = new ToDoList("Test User");
        String filePathTodo = tempDir.resolve(TEST_FILEPATH_TODO).toString();
        CSVCreate.writeToDoFile(task, todo, filePathTodo);
        File file = new File(filePathTodo);
        Assertions.assertTrue(file.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String expectedLine = "Test Task,This is a test task,test;test 2,2023-05-07,TODO,HIGH,Test Project,Test User";
            assertEquals(expectedLine, line);
        }
    }

    @Test
    void testWriteToDoFile_ExceptionHandling() {
        // Set up test data
        Task task = new Task.Builder("Test Task")
                .description("This is a test task")
                .tags(Collections.singletonList(new TaskTag("test")))
                .deadline(LocalDate.of(2023, 5, 7))
                .status(TaskStatus.TODO)
                .priority(TaskPriority.HIGH)
                .projectName("Test Project")
                .build();
        ToDoList todo = new ToDoList("Test User");
        String filePathTodo = "nonexistent_directory/test_todo.csv";

        assertThrows(IOException.class, () ->
                CSVCreate.writeToDoFile(task, todo, filePathTodo));
    }

    @Test
    void testWriteProjectFile() throws IOException {
        Project project = new Project("Test Project", LocalDate.of(2023, 5, 7));
        String filePathProject = tempDir.resolve(TEST_FILEPATH_PROJECT).toString();
        CSVCreate.writeProjectFile(project, filePathProject);
        File file = new File(filePathProject);
        Assertions.assertTrue(file.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String expectedLine = "Test Project,2023-05-07";
            assertEquals(expectedLine, line);
        }
    }

    @Test
    void testWriteProjectFile_ExceptionHandling() {
        Project project = new Project("Test Project", null);
        String filePathProject = "nonexistent_directory/test_project.csv";
        assertThrows(IOException.class, () ->
                CSVCreate.writeProjectFile(project, filePathProject));
    }

    @Test
    void testWriteQuickToDoFile() throws IOException {
        Task task = new Task.Builder("Test Task")
                .status(TaskStatus.TODO)
                .build();
        ToDoList todo = new ToDoList("Test User");
        String filePathTodo = tempDir.resolve(TEST_FILEPATH_TODO).toString();
        CSVCreate.writeQuickToDoFile(task, todo, filePathTodo);
        File file = new File(filePathTodo);
        Assertions.assertTrue(file.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String expectedLine = "Test Task,,,,TODO,,,Test User";
            assertEquals(expectedLine, line);
        }
    }

    @Test
    void testWriteQuickToDoFile_ExceptionHandling() {
        // Set up test data
        Task task = new Task.Builder("Test Task").status(TaskStatus.TODO).build();
        ToDoList todo = new ToDoList("Test User");
        String filePathTodo = "nonexistent_directory/test_todo.csv";  // Invalid file path

        // Perform the test
        assertThrows(IOException.class, () ->
                CSVCreate.writeQuickToDoFile(task, todo, filePathTodo));
    }

    @Test
    void testWriteQuickToDoFileIOException() {
        Task task = new Task.Builder("Task 1")
                .status(TaskStatus.TODO)
                .build();
        ToDoList todo = new ToDoList("testuser");
        String filePathTodo = "invalidpath/todo.csv";

        try {
            CSVCreate.writeQuickToDoFile(task, todo, filePathTodo);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            assertEquals("Error in CSV-Creation!", e.getMessage());
        }
    }

    @Test
    void testWriteToDoFileIOException() {
        Task task = new Task.Builder("Task 1")
                .status(TaskStatus.TODO)
                .build();
        ToDoList todo = new ToDoList("testuser");
        String filePathTodo = "invalidpath/todo.csv";

        try {
            CSVCreate.writeToDoFile(task, todo, filePathTodo);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            assertEquals("Error in CSV-Creation!", e.getMessage());
        }
    }

    @Test
    void testWriteProjectFileIOException() {
        Project project = new Project("Project 1", LocalDate.of(2023, 6, 1));
        String filePathProject = "invalidpath/project.csv";

        try {
            CSVCreate.writeProjectFile(project, filePathProject);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            assertEquals("Error in CSV-Creation!", e.getMessage());
        }
    }
}