package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVCreateTest {

    private static final String TEST_FILEPATH_TODO = "test_todo.csv";
    private static final String TEST_FILEPATH_PROJECT = "test_project.csv";

    @TempDir
    static Path tempDir;

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
        // Create test data
        Task task = new Task.Builder("Test Task")
                .description("This is a test task")
                .tags(Collections.singletonList(new TaskTag("test")))
                .deadline(LocalDate.of(2023, 5, 7))
                .status(TaskStatus.TODO)
                .priority(TaskPriority.HIGH)
                .projectName("Test Project")
                .build();
        ToDoList todo = new ToDoList("Test User");

        // Call method under test
        CSVCreate.writeToDoFile(task, todo,tempDir.resolve(TEST_FILEPATH_TODO).toString());

        // Verify file was created with correct contents
        File file = new File(tempDir.resolve(TEST_FILEPATH_TODO).toString());
        Assertions.assertTrue(file.exists());
        byte[] bytes = Files.readAllBytes(Paths.get(tempDir.resolve(TEST_FILEPATH_TODO).toString()));
        String fileContents = new String(bytes);
        String expectedContents = "Test Task,This is a test task,test,2023-05-07,TODO,HIGH,Test Project,Test User" + System.lineSeparator() ;
        assertEquals(expectedContents, fileContents);
    }

    @Test
    void testWriteProjectFile() throws IOException {
        // Create test data
        Project project = new Project("Test Project", LocalDate.of(2023, 5, 7));

        // Call method under test
        CSVCreate.writeProjectFile(project, tempDir.resolve(TEST_FILEPATH_PROJECT).toString());

        // Verify file was created with correct contents
        File file = new File(tempDir.resolve(TEST_FILEPATH_PROJECT).toString());
        Assertions.assertTrue(file.exists());
        byte[] bytes = Files.readAllBytes(Paths.get(tempDir.resolve(TEST_FILEPATH_PROJECT).toString()));
        String fileContents = new String(bytes);
        String expectedContents = "Test Project,2023-05-07" + System.lineSeparator();
        assertEquals(expectedContents, fileContents);
    }
}
