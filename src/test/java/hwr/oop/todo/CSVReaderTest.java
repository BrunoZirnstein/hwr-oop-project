package hwr.oop.todo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hwr.oop.todo.CSVReader;
import hwr.oop.todo.Project;
import hwr.oop.todo.Task;
import hwr.oop.todo.TaskPriority;
import hwr.oop.todo.TaskStatus;
import hwr.oop.todo.TaskTag;
import hwr.oop.todo.ToDo;

class CSVReaderTest {

    private static final String TEST_TODO_FILE = "test/todo.csv";
    private static final String TEST_PROJECT_FILE = "testProjectFile.csv";
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";

    @BeforeAll
    public static void createTestFile() throws IOException {
        File testFile = new File("testProjectFile.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        Project project = new Project("TestProject",LocalDate.of(2023,5,30));
        writer.write(project.title());
        writer.write(COMMA_DELIMITER);
        writer.write(project.getDeadline().toString());
        writer.write(LINE_SEPARATOR);
        writer.close();
    }

    @AfterAll
    public static void deleteTestFile() {
        File testFile = new File("testProjectFile.csv");
        testFile.delete();
    }

    @Test
    void testReadToDoFile() throws IOException {

    }

    @Test
    void testReadProject() throws IOException {
        List<Project> projects = CSVReader.readProject(TEST_PROJECT_FILE);

        assertEquals(1, projects.size());

        Project firstProject = projects.get(0);
        assertEquals("TestProject", firstProject.title());
        assertEquals(LocalDate.of(2023, 5, 30), firstProject.getDeadline());
    }
}



