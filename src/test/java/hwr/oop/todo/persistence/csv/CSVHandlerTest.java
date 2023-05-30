package hwr.oop.todo.persistence.csv;

import hwr.oop.todo.core.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static hwr.oop.todo.core.TaskPriority.HIGH;
import static org.junit.jupiter.api.Assertions.*;

class CSVHandlerTest {

    private static final String TEST_FILEPATH_TODO = "test_todo.csv";
    private static final String TEST_FILEPATH_PROJECT = "test_project.csv";
    private static final String TEST_FILEPATH_TASK = "test_task.csv";

    private CSVHandler csvHandler;

    // CSVHandler csvHandler = new CSVHandler();
    private ToDoList todo;
    @TempDir
    static Path tempDir;

    private void createTaskTestData(String filePath) {
        try (BufferedWriter todoWriter = new BufferedWriter(new FileWriter(filePath))) {
            todoWriter.write("Task 1,Description 1,tag1;tag2,2023-06-01,DONE,HIGH,Project 1,Test_User_1\n");
            todoWriter.write("Task 2,Description 2,tag3,2023-06-02,BLOCKED,LOW,Project 1,Test_User_1\n");
            todoWriter.write("Task 3,Description 3,,,TODO,HIGH,Project 2,Test_User_1\n");
            todoWriter.write("Task 4,Description 4,,,TODO,,Project 2,Test_User_2\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createProjectTestData(String filePath) {
        try (BufferedWriter projectWriter = new BufferedWriter(new FileWriter(filePath))) {
            projectWriter.write("Project 1,2023-06-01\n");
            projectWriter.write("Project 2,2023-06-02\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createToDoUserTestData(String filePath) {
        try (BufferedWriter ToDOUserWriter = new BufferedWriter(new FileWriter(filePath))) {
            ToDOUserWriter.write("Test_User_1\n");
            ToDOUserWriter.write("Test_User_2\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean doesTaskExist(String taskID, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0 && fields[0] != null && fields[0].equals(taskID)) {
                    return true;
                }
            }
        }
        return false;
    }

    @BeforeEach
    void cleanupAndSetUp() throws IOException {
        csvHandler = new CSVHandler();
        Files.deleteIfExists(tempDir.resolve(TEST_FILEPATH_TODO));
        Files.deleteIfExists(tempDir.resolve(TEST_FILEPATH_PROJECT));
        Files.deleteIfExists(tempDir.resolve(TEST_FILEPATH_TASK));
    }
    /*
     * @BeforeEach
     * void setUp() throws IOException {
     * String tempPathList = tempDir.resolve(TODO_CSV_FILENAME).toString();
     * //String tempPathProject = tempDir.resolve(PROJECT_CSV_FILENAME).toString();
     * todo = CSVReader.readToDoFile("user1", tempPathList);
     * }
     */

    @Test
    void testSetFilePathToDoUser() {
        csvHandler.setFilePathToDoUser(TEST_FILEPATH_TODO);
        assertEquals(TEST_FILEPATH_TODO, csvHandler.getFilePathTodo());
    }

    @Test
    void testSetFilePathProject() {
        csvHandler.setFilePathProject(TEST_FILEPATH_PROJECT);
        assertEquals(TEST_FILEPATH_PROJECT, csvHandler.getFilePathProject());
    }

    @Test
    void testSetFilePathTask() {
        csvHandler.setFilePathTask(TEST_FILEPATH_TASK);
        assertEquals(TEST_FILEPATH_TASK, csvHandler.getFilePathTask());
    }

    @Test
    void testGetFilePathTodo() {
        csvHandler.setFilePathToDoUser(TEST_FILEPATH_TODO);
        assertEquals(TEST_FILEPATH_TODO, csvHandler.getFilePathTodo());
    }

    @Test
    void testGetFilePathTask() {
        csvHandler.setFilePathTask(TEST_FILEPATH_TASK);
        assertEquals(TEST_FILEPATH_TASK, csvHandler.getFilePathTask());
    }

    @Test
    void testGetFilePathProject() {
        csvHandler.setFilePathProject(TEST_FILEPATH_PROJECT);
        assertEquals(TEST_FILEPATH_PROJECT, csvHandler.getFilePathProject());
    }

    @Test
    void testSaveNewQuickTaskFile() throws IOException {
        Task task = new Task.Builder("Test Task")
                .status(TaskStatus.TODO)
                .build();
        ToDoList todo = new ToDoList("Test User");
        String filePathTask = tempDir.resolve(TEST_FILEPATH_TASK).toString();
        csvHandler.setFilePathTask(filePathTask);
        csvHandler.saveNewQuickTask(task, todo);
        File file = new File(filePathTask);
        Assertions.assertTrue(file.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String expectedLine = "Test Task,,,,TODO,,,Test User";
            assertEquals(expectedLine, line);
        }
    }

    @Test
    void testSaveNewQuickTask_ExceptionHandling() {
        Task task = new Task.Builder("Test Task").status(TaskStatus.TODO).build();
        ToDoList todo = new ToDoList("Test User");
        String filePathTask = "nonexistent_directory/test_task.csv";
        csvHandler.setFilePathTask(filePathTask);
        assertThrows(IOException.class, () -> csvHandler.saveNewQuickTask(task, todo));
    }

    @Test
    void testSaveNewQuickTaskIOException() {
        Task task = new Task.Builder("Task 1")
                .status(TaskStatus.TODO)
                .build();
        ToDoList todo = new ToDoList("testuser");
        String filePathTask = "invalidpath/task.csv";
        csvHandler.setFilePathTask(filePathTask);
        try {
            csvHandler.saveNewQuickTask(task, todo);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            assertEquals("Error in CSV-Creation!", e.getMessage());
        }
    }

    @Test
    void testSaveNewTask() throws IOException {
        Task task = new Task.Builder("Test Task")
                .description("This is a test task")
                .tags(List.of(new TaskTag("test"), new TaskTag("test 2")))
                .deadline(LocalDate.of(2023, 5, 7))
                .status(TaskStatus.TODO)
                .priority(TaskPriority.HIGH)
                .projectName("Test Project")
                .build();
        ToDoList todo = new ToDoList("Test User");
        String filePathTask = tempDir.resolve(TEST_FILEPATH_TASK).toString();
        csvHandler.setFilePathTask(filePathTask);
        csvHandler.saveNewTask(task, todo);
        File file = new File(filePathTask);
        Assertions.assertTrue(file.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String expectedLine = "Test Task,This is a test task,test;test 2,2023-05-07,TODO,HIGH,Test Project,Test User";
            assertEquals(expectedLine, line);
        }
    }

    @Test
    void testSaveNewTask_ExceptionHandling() {
        Task task = new Task.Builder("Test Task")
                .description("This is a test task")
                .tags(Collections.singletonList(new TaskTag("test")))
                .deadline(LocalDate.of(2023, 5, 7))
                .status(TaskStatus.TODO)
                .priority(TaskPriority.HIGH)
                .projectName("Test Project")
                .build();
        ToDoList todo = new ToDoList("Test User");
        String filePathTask = "nonexistent_directory/test_task.csv";
        csvHandler.setFilePathTask(filePathTask);
        assertThrows(IOException.class, () -> csvHandler.saveNewTask(task, todo));
    }

    @Test
    void testSaveNewTaskIOException() {
        Task task = new Task.Builder("Task 1")
                .status(TaskStatus.TODO)
                .build();
        ToDoList todo = new ToDoList("testuser");
        String filePathTask = "invalidpath/test_task.csv";
        csvHandler.setFilePathTask(filePathTask);

        try {
            csvHandler.saveNewTask(task, todo);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            assertEquals("Error in CSV-Creation!", e.getMessage());
        }
    }

    @Test
    void testSaveNewToDoList() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_TODO).toString();
        csvHandler.setFilePathToDoUser(filePath);
        ToDoList toDoList = new ToDoList("Test User");
        csvHandler.saveNewToDoList(toDoList);
        File file = new File(filePath);
        assertTrue(file.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String expectedLine = "Test User";
            assertEquals(expectedLine, line);
        }
    }

    @Test
    void testSaveNewToDoList_ExceptionHandling() {
        csvHandler.setFilePathToDoUser("/invalid/file/test_todo.csv");
        ToDoList toDoList = new ToDoList("Test User");
        IOException exception = assertThrows(IOException.class, () -> csvHandler.saveNewToDoList(toDoList));
        assertEquals("Error in CSV-Creation!", exception.getMessage());
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof FileNotFoundException);
    }

    @Test
    void testSaveNewProject() throws IOException {
        ToDoList todo = new ToDoList("Test User");
        Project project = new Project.Builder("Test Project").deadline(LocalDate.of(2023, 5, 7)).build();
        String filePathProject = tempDir.resolve(TEST_FILEPATH_PROJECT).toString();
        csvHandler.setFilePathProject(filePathProject);
        csvHandler.saveNewProject(project, todo);
        File file = new File(filePathProject);
        Assertions.assertTrue(file.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String expectedLine = "Test Project,2023-05-07,Test User";
            assertEquals(expectedLine, line);
        }
    }

    @Test
    void testSaveNewProject_ExceptionHandling() {
        Project project = new Project.Builder("Test Project").build();
        ToDoList todo = new ToDoList("Test User");
        String filePathProject = "nonexistent_directory/test_project.csv";
        csvHandler.setFilePathProject(filePathProject);
        assertThrows(IOException.class, () -> csvHandler.saveNewProject(project, todo));
    }

    @Test
    void testSaveNewProjectIOException() {
        Project project = new Project.Builder("Test Project").deadline(LocalDate.of(2023, 6, 1)).build();
        String filePathProject = "invalidpath/test_project.csv";
        csvHandler.setFilePathProject(filePathProject);
        ToDoList todo = new ToDoList("Test User");
        try {
            csvHandler.saveNewProject(project, todo);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            assertEquals("Error in CSV-Creation!", e.getMessage());
        }
    }

    @Test
    void testLoadAllTasksFromFile() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_TASK).toString();
        csvHandler.setFilePathTask(filePath);
        createTaskTestData(filePath);
        List<Task> tasks = csvHandler.loadAllTasksFromFile();
        assertEquals(4, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals("Task 1", task1.title());
        assertEquals("Description 1", task1.description());
        assertEquals(2, task1.tags().size());
        assertEquals("tag1", task1.tags().get(0).title());
        assertEquals("tag2", task1.tags().get(1).title());
        assertEquals(LocalDate.of(2023, 6, 1), task1.deadline());
        assertEquals(TaskStatus.DONE, task1.status());
        assertEquals(TaskPriority.HIGH, task1.priority());
        assertEquals("Project 1", task1.projectName().orElse(null));
    }

    @Test
    void testLoadAllTasksFromFileIOExceptionFileNotFound() {
        String filePathTask = "nonexistentfile.csv";
        csvHandler.setFilePathTask(filePathTask);
        try {
            csvHandler.loadAllTasksFromFile();
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            // Exception successfully caught
        }
    }

    @Test
    void testLoadAToDoListFromUserWithAllTasks() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_TASK).toString();
        csvHandler.setFilePathTask(filePath);
        createTaskTestData(filePath);
        ToDoList todo = new ToDoList("Test_User_1");
        Project project1 = new Project.Builder("Project 1").deadline(LocalDate.of(2023, 6, 1)).build();

        todo.addProject(project1);
        Project project2 = new Project.Builder("Project 2").deadline(LocalDate.of(2023, 6, 2)).build();
        todo.addProject(project2);
        todo = csvHandler.loadAToDoListFromUserWithAllTasks(todo);
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
        assertNull(todo.tasks().get(2).deadline());

        assertEquals(TaskStatus.DONE, todo.tasks().get(0).status());
        assertEquals(TaskStatus.BLOCKED, todo.tasks().get(1).status());
        assertEquals(TaskStatus.TODO, todo.tasks().get(2).status());

        assertEquals(HIGH, todo.tasks().get(0).priority());
        assertEquals(TaskPriority.LOW, todo.tasks().get(1).priority());
        assertEquals(HIGH, todo.tasks().get(2).priority());

        assertEquals("Project 1", todo.tasks().get(0).projectName().orElse(null));
        assertEquals("Project 1", todo.tasks().get(1).projectName().orElse(null));
        assertEquals("Project 2", todo.tasks().get(2).projectName().orElse(null));

        ToDoList todo2 = new ToDoList("Test_User_2");
        todo2.addProject(project2);
        todo2 = csvHandler.loadAToDoListFromUserWithAllTasks(todo2);
        assertEquals(1, todo2.tasks().size());
        assertEquals(HIGH, todo2.tasks().get(0).priority());
    }

    @Test
    void testLoadAToDoFileFromUserWithAllTasksIOExceptionFileNotFound() {
        ToDoList todo = new ToDoList("Test_User_1");
        String filePathTask = "nonexistentfile.csv";
        csvHandler.setFilePathTask(filePathTask);
        try {
            csvHandler.loadAToDoListFromUserWithAllTasks(todo);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            // Exception successfully caught
        }
    }

    @Test
    void testLoadAllProjectFromFile() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_PROJECT).toString();
        csvHandler.setFilePathProject(filePath);
        createProjectTestData(filePath);
        List<Project> projects = csvHandler.loadAllProjectFromFile();
        assertNotNull(projects);
        assertEquals(2, projects.size());
        assertEquals("Project 1", projects.get(0).title());
        assertEquals(LocalDate.of(2023, 6, 1), projects.get(0).deadline());
        assertEquals("Project 2", projects.get(1).title());
        assertEquals(LocalDate.of(2023, 6, 2), projects.get(1).deadline());
    }

    @Test
    void testLoadAllToDoListUsersFromFile() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_TODO).toString();
        csvHandler.setFilePathToDoUser(filePath);
        createToDoUserTestData(filePath);
        List<ToDoList> todos = csvHandler.loadAllToDoListUsersFromFile();
        assertNotNull(todos);
        assertEquals(2, todos.size());
        assertEquals("Test_User_1", todos.get(0).owner());
        assertEquals("Test_User_2", todos.get(1).owner());
    }

    @Test
    void testRemoveTask() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_TASK).toString();
        csvHandler.setFilePathTask(filePath);
        createTaskTestData(filePath);
        List<Task> tasksBeforeRemoval = csvHandler.loadAllTasksFromFile();
        assertEquals(4, tasksBeforeRemoval.size());
        Task taskToRemove = tasksBeforeRemoval.get(0);
        csvHandler.removeTask(taskToRemove.title());
        List<Task> tasksAfterRemoval = csvHandler.loadAllTasksFromFile();
        assertEquals(3, tasksAfterRemoval.size());
        assertFalse(tasksAfterRemoval.contains(taskToRemove));
    }

    @Test
    void testRemoveTask_2() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_TASK).toString();
        csvHandler.setFilePathTask(filePath);
        createTaskTestData(filePath);
        int initialTaskCount = csvHandler.loadAllTasksFromFile().size();
        String taskIDToRemove = "Task 1";
        csvHandler.removeTask(taskIDToRemove);
        List<Task> tasksAfterRemoval = csvHandler.loadAllTasksFromFile();
        assertEquals(initialTaskCount - 1, tasksAfterRemoval.size());
        assertFalse(tasksAfterRemoval.stream().anyMatch(task -> task.title().equals(taskIDToRemove)));
    }

    @Test
    void testRemoveTask_3() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_TASK).toString();
        csvHandler.setFilePathTask(filePath);
        File file = new File(filePath);
        file.createNewFile();
        int initialTaskCount = csvHandler.loadAllTasksFromFile().size();
        String taskIDToRemove = "noneExistenceTask";
        csvHandler.removeTask(taskIDToRemove);
        List<Task> tasksAfterRemoval = csvHandler.loadAllTasksFromFile();
        assertEquals(initialTaskCount, tasksAfterRemoval.size());
        assertFalse(tasksAfterRemoval.stream().anyMatch(task -> task.title().equals(taskIDToRemove)));
    }

    @Test
    public void testRemoveTask_4() {
        String filePath = tempDir.resolve(TEST_FILEPATH_TASK).toString();
        csvHandler.setFilePathTask(filePath);
        createTaskTestData(filePath);
        String taskIDToRemove = "Task 1";
        try {
            csvHandler.removeTask(taskIDToRemove);
            assertFalse(doesTaskExist(taskIDToRemove, filePath));
        } catch (IOException e) {
            fail("An error occurred while removing the task: " + e.getMessage());
        }
    }

    @Test
    void testRemoveProject() throws IOException {
        String filePathProject = tempDir.resolve(TEST_FILEPATH_PROJECT).toString();
        csvHandler.setFilePathProject(filePathProject);
        createProjectTestData(filePathProject);
        List<Project> projectsBeforeRemoval = csvHandler.loadAllProjectFromFile();
        assertEquals(2, projectsBeforeRemoval.size());
        Project projectToRemove = projectsBeforeRemoval.get(0);
        csvHandler.removeProject(projectToRemove.title());
        List<Project> projectsAfterRemoval = csvHandler.loadAllProjectFromFile();
        assertEquals(1, projectsAfterRemoval.size());
        assertFalse(projectsAfterRemoval.contains(projectToRemove));
    }

    @Test
    void testRemoveProject_2() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_PROJECT).toString();
        csvHandler.setFilePathProject(filePath);
        createProjectTestData(filePath);
        int initialTaskCount = csvHandler.loadAllProjectFromFile().size();
        String ProjectIDToRemove = "Project 1";
        csvHandler.removeProject(ProjectIDToRemove);
        List<Project> projectAfterRemoval = csvHandler.loadAllProjectFromFile();
        assertEquals(initialTaskCount - 1, projectAfterRemoval.size());
        assertFalse(projectAfterRemoval.stream().anyMatch(project -> project.title().equals(ProjectIDToRemove)));
    }

    @Test
    public void testRemoveProject_4() throws IOException {
        String filePath = tempDir.resolve(TEST_FILEPATH_PROJECT).toString();
        csvHandler.setFilePathProject(filePath);
        createProjectTestData(filePath);
        csvHandler.removeProject("Project 2");
        List<String> expectedLines = List.of(
                "Project 1,2023-06-01");
        List<String> actualLines = Files.readAllLines(Path.of(filePath));
        Assertions.assertEquals(expectedLines, actualLines);
    }
}