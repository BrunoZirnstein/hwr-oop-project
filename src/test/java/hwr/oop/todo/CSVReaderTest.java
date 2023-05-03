package hwr.oop.todo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CSVReaderTest {

    private static final String TODO_CSV_FILENAME = "TodoTest.csv";
    private static final String PROJECT_CSV_FILENAME = "ProjectTest.csv";
    private ToDo todo;

    CSVReader reader = new CSVReader();
    @TempDir
    static Path tempDir;
    @BeforeAll
    public static void createTestCSVFiles() throws IOException {
        // Create TodoTest.csv file
        BufferedWriter todoWriter = new BufferedWriter(new FileWriter(tempDir.resolve(TODO_CSV_FILENAME).toString()));
        todoWriter.write("Task 1,Description 1,tag1;tag2,2023-06-01,DONE,HIGH,Project 1,user1\n");
        todoWriter.write("Task 2,Description 2,tag3,2023-06-02,BLOCKED,LOW,,user1\n");
        todoWriter.write("Task 3,Description 3,,,TODO,HIGH,Project 2,user1\n");
        todoWriter.flush();
        todoWriter.close();

        // Create ProjectTest.csv file
        BufferedWriter projectWriter = new BufferedWriter(new FileWriter(tempDir.resolve(PROJECT_CSV_FILENAME).toString()));
        projectWriter.write("Project 1,2023-06-01\n");
        projectWriter.write("Project 2,2023-06-02\n");
        projectWriter.flush();
        projectWriter.close();
    }
    @BeforeEach
    void setUp() {
        reader.setFilePathToDo(tempDir.resolve(TODO_CSV_FILENAME).toString());
        reader.setFilePathProject(tempDir.resolve(PROJECT_CSV_FILENAME).toString());
        todo = reader.readToDoFile("user1");
    }
    @Test
    public void testReadToDoFile() {
        assertEquals(3, todo.tasks().size());
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
        assertEquals(null, todo.tasks().get(2).deadline());

        assertEquals(TaskStatus.DONE, todo.tasks().get(0).status());
        assertEquals(TaskStatus.BLOCKED, todo.tasks().get(1).status());
        assertEquals(TaskStatus.TODO, todo.tasks().get(2).status());

        assertEquals(TaskPriority.HIGH, todo.tasks().get(0).priority());
        assertEquals(TaskPriority.LOW, todo.tasks().get(1).priority());
        assertEquals(TaskPriority.HIGH, todo.tasks().get(2).priority());

        //assertEquals("Project 1", todo.tasks().get(0).projectName().toString());
        //assertEquals(null, todo.tasks().get(1).projectName());
        //assertEquals("Project 2", todo.tasks().get(2).projectName());
    }

    @Test
    public void testReadProjectFile() throws IOException {
        List<Project> projects = reader.readProjectFile();
        assertNotNull(projects);
        assertEquals(2, projects.size());
        assertEquals("Project 1", projects.get(0).title());
        assertEquals(LocalDate.of(2023, 6, 1), projects.get(0).deadline());
        assertEquals("Project 2", projects.get(1).title());
        assertEquals(LocalDate.of(2023, 6, 2), projects.get(1).deadline());
    }
}
