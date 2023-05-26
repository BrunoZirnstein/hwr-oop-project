package hwr.oop.todo.persistence.csv;

import hwr.oop.todo.application.Project;
import hwr.oop.todo.application.TaskPriority;
import hwr.oop.todo.application.TaskStatus;
import hwr.oop.todo.application.ToDoList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static hwr.oop.todo.application.TaskPriority.HIGH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CSVReaderTest {

    private static final String TODO_CSV_FILENAME = "TodoTest.csv";
    private static final String PROJECT_CSV_FILENAME = "ProjectTest.csv";
    private ToDoList todo;
    @TempDir
    static Path tempDir;
    @BeforeAll
    public static void createTestCSVFiles() throws IOException {
        try (BufferedWriter todoWriter = new BufferedWriter(new FileWriter(tempDir.resolve(TODO_CSV_FILENAME).toString()))) {
            todoWriter.write("Task 1,Description 1,tag1;tag2,2023-06-01,DONE,HIGH,Project 1,user1\n");
            todoWriter.write("Task 2,Description 2,tag3,2023-06-02,BLOCKED,LOW,,user1\n");
            todoWriter.write("Task 3,Description 3,,,TODO,HIGH,Project 2,user1\n");
            todoWriter.write("Task 4,Description 4,,,TODO,,,user1\n");
        }
        try (BufferedWriter projectWriter = new BufferedWriter(new FileWriter(tempDir.resolve(PROJECT_CSV_FILENAME).toString()))) {
            projectWriter.write("Project 1,2023-06-01\n");
            projectWriter.write("Project 2,2023-06-02\n");
        }
    }
    @BeforeEach
    void setUp() throws IOException {
        String tempPathList = tempDir.resolve(TODO_CSV_FILENAME).toString();
        //String tempPathProject = tempDir.resolve(PROJECT_CSV_FILENAME).toString();
        todo = CSVReader.readToDoFile("user1", tempPathList);
    }

    @Test
    void testGetFilePathTodo() {
        String expectedFilePath = "ToDo_List.csv";
        String actualFilePath = CSVReader.getFilePathTodo();
        Assertions.assertEquals(expectedFilePath, actualFilePath);
    }

    @Test
    void testGetFilePathProject() {
        String expectedFilePath = "Project_List.csv";
        String actualFilePath = CSVReader.getFilePathProject();
        Assertions.assertEquals(expectedFilePath, actualFilePath);
    }

    @Test
    void testReadToDoFile() throws IOException {
        assertEquals(4, todo.tasks().size());
        assertEquals("Task 1", todo.tasks().get(0).title());
        assertEquals("Task 2", todo.tasks().get(1).title());
        assertEquals("Task 3", todo.tasks().get(2).title());

        assertEquals("Description 1", todo.tasks().get(0).description());
        assertEquals("Description 2", todo.tasks().get(1).description());
        assertEquals("Description 3", todo.tasks().get(2).description());

        assertEquals(2, todo.tasks().get(0).tags().size());
        assertEquals(1, todo.tasks().get(1).tags().size());
        assertEquals(0, todo.tasks().get(2).tags().size());

        assertEquals(LocalDate.of(2023, 6, 1), todo.tasks().get(0).deadline());
        assertEquals(LocalDate.of(2023, 6, 2), todo.tasks().get(1).deadline());
        assertNull(todo.tasks().get(2).deadline());

        Assertions.assertEquals(TaskStatus.DONE, todo.tasks().get(0).status());
        assertEquals(TaskStatus.BLOCKED, todo.tasks().get(1).status());
        assertEquals(TaskStatus.TODO, todo.tasks().get(2).status());

        assertEquals(HIGH, todo.tasks().get(0).priority());
        Assertions.assertEquals(TaskPriority.LOW, todo.tasks().get(1).priority());
        assertEquals(HIGH, todo.tasks().get(2).priority());

        ToDoList todoEmptyPriority = CSVReader.readToDoFile("user1", tempDir.resolve(TODO_CSV_FILENAME).toString());
        assertEquals(4, todoEmptyPriority.tasks().size());
        assertEquals(HIGH, todoEmptyPriority.tasks().get(0).priority());

        //assertEquals("Project 1", todo.tasks().get(0).projectName().toString());
        //assertEquals(null, todo.tasks().get(1).projectName());
        //assertEquals("Project 2", todo.tasks().get(2).projectName());
    }

    @Test
    void testReadToDoFileIOExceptionFileNotFound() {
        ToDoList todo = new ToDoList("testuser");
        String filePathToDo = "nonexistentfile.csv";

        try {
            CSVReader.readToDoFile("testuser", filePathToDo);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            // Exception successfully caught
            // You can perform any necessary assertions or logging here
        }
    }

    @Test
    void testReadProjectFile() throws IOException {
        String tempPathProject = tempDir.resolve(PROJECT_CSV_FILENAME).toString();
        List<Project> projects = CSVReader.readProjectFile(tempPathProject);
        assertNotNull(projects);
        assertEquals(2, projects.size());
        assertEquals("Project 1", projects.get(0).title());
        assertEquals(LocalDate.of(2023, 6, 1), projects.get(0).deadline());
        assertEquals("Project 2", projects.get(1).title());
        assertEquals(LocalDate.of(2023, 6, 2), projects.get(1).deadline());
    }
}