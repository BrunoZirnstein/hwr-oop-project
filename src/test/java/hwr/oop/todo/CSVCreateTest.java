package hwr.oop.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CSVCreateTest {

    @TempDir
    static Path tempDir;

    private static final Task validTask = new Task.Builder("ValidTask")
            .description("This is a valid task.")
            .tags(Collections.singletonList(new TaskTag("Tag")))
            .deadline(LocalDate.of(2023, 5, 1))
            .status(TaskStatus.DONE)
            .priority(TaskPriority.HIGH)
            .projectName("Project")
            .build();

    private static final Task validTaskNoDescription = new Task.Builder("ValidTaskNoDescription")
            .tags(Collections.singletonList(new TaskTag("Tag")))
            .deadline(LocalDate.of(2023, 5, 1))
            .status(TaskStatus.DONE)
            .priority(TaskPriority.HIGH)
            .projectName("Project")
            .build();

    private static final Task validTaskNoTags = new Task.Builder("ValidTaskNoTags")
            .description("This is a valid task.")
            .deadline(LocalDate.of(2023, 5, 1))
            .status(TaskStatus.DONE)
            .priority(TaskPriority.HIGH)
            .projectName("Project")
            .build();

    private static final Task validTaskNoDeadline = new Task.Builder("ValidTaskNoDeadline")
            .description("This is a valid task.")
            .tags(Collections.singletonList(new TaskTag("Tag")))
            .status(TaskStatus.DONE)
            .priority(TaskPriority.HIGH)
            .projectName("Project")
            .build();

    private static final Task validTaskNoPriority = new Task.Builder("ValidTaskNoPriority")
            .description("This is a valid task.")
            .tags(Collections.singletonList(new TaskTag("Tag")))
            .deadline(LocalDate.of(2023, 5, 1))
            .status(TaskStatus.DONE)
            .projectName("Project")
            .build();

    private static final Task validTaskNoProjectName = new Task.Builder("ValidTaskNoProjectName")
            .description("This is a valid task.")
            .tags(Collections.singletonList(new TaskTag("Tag")))
            .deadline(LocalDate.of(2023, 5, 1))
            .status(TaskStatus.DONE)
            .priority(TaskPriority.HIGH)
            .build();

    private static final ToDo validToDo = new ToDo("User");

    private static final Project validProject = new Project("Valid Project", LocalDate.of(2023, 5, 1));

    @BeforeEach
    void setUp() {
        CSVCreate.FILEPATHTODO = tempDir.resolve("TODO_List.csv").toString();
        CSVCreate.FILEPATHPROJECT = tempDir.resolve("Project_List.csv").toString();
    }

    @Test
    void testWriteToDoFileWithValidTaskAndToDo() throws IOException {
        CSVCreate.writeToDoFile(validTask, validToDo);
        File file = new File(CSVCreate.FILEPATHTODO);
    }
}
