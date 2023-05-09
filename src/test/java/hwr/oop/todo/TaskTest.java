package hwr.oop.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test task")
class TaskTest {
    @Test
    @DisplayName("New Task created with Title")
    void testCreateTaskWithOnlyTitle() {
        Task task = new Task.Builder("Water plants").build();
        assertThat(task.title()).isEqualTo("Water plants");
        assertThat(task.tags()).isEqualTo(new ArrayList<TaskTag>());
        assertThat(task.description()).isNull();
        assertThat(task.deadline()).isNull();
        assertThat(task.status()).isEqualTo(TaskStatus.TODO);
        assertThat(task.priority()).isNull();
        assertThat(task.projectName()).isEmpty();
    }

    @Test
    @DisplayName("New Task with all parameters")
    void testCreateTaskWithParameters() {
        Task task = new Task.Builder("Water plants").description("Water all the plants in the living room and in the bedroom.").tags(List.of(new TaskTag("home"))).deadline(LocalDate.of(2023, 5, 30)).priority(TaskPriority.HIGH).projectName("university").build();
        assertThat(task.title()).isEqualTo("Water plants");
        assertThat(task.tags()).isEqualTo(List.of(new TaskTag("home")));
        assertThat(task.description()).isEqualTo("Water all the plants in the living room and in the bedroom.");
        assertThat(task.deadline()).isEqualTo(LocalDate.of(2023, 5, 30));
        assertThat(task.status()).isEqualTo(TaskStatus.TODO);
        assertThat(task.priority()).isEqualTo(TaskPriority.HIGH);
        assertThat(task.projectName()).contains("university"); // contains checks if optional type value is equal to testProject
    }

    @Test
    @DisplayName("Test adding and removing a task")
    void testTagOfTask() {
        Task task = new Task.Builder("Build a chair").build();
        task.addTag(new TaskTag("home"));
        TaskTag taskGet = task.tags().get(0);
        assertThat(task.tags().get(0)).isEqualTo(taskGet);
        task.removeTagByName("home");

        assertThat(task.tags()).isEmpty();
        task.addTag(new TaskTag("home"));
        task.removeTagByObject(taskGet);
        assertThat(task.tags()).isEmpty();
    }

    @Test
    @DisplayName("Changing description of task")
    void testDescriptionOfTask() {
        Task task = new Task.Builder("Build a chair").description("Build a chair out of wood").build();
        task.changeDescription("Build a chair out of metal");
        assertThat(task.description()).isEqualTo("Build a chair out of metal");
    }

    @Test
    @DisplayName("Changing Deadline of task")
    void testDeadlineOfTask() {
        Task task = new Task.Builder("Build a chair").deadline(LocalDate.of(2023,5,1)).build();
        task.moveDeadline(LocalDate.of(2023,6,1));
        assertThat(task.deadline()).isEqualTo(LocalDate.of(2023,6,1));
    }

    @Test
    @DisplayName("Changing Status of task")
    void testStatusOfTask() {
        Task task = new Task.Builder("Build a chair").status(TaskStatus.TODO).build();
        task.updateStatus(TaskStatus.BLOCKED);
        assertThat(task.status()).isEqualTo(TaskStatus.BLOCKED);
    }

    @DisplayName("Changing Priority of task")
    @ParameterizedTest
    @EnumSource(TaskPriority.class)
    void testPriorityOfTask(TaskPriority priority) {
        Task task = new Task.Builder("Build a chair").priority(TaskPriority.LOW).build();
        task.changePriority(priority);
        assertThat(task.priority()).isEqualTo(priority);
    }

    @Test
    @DisplayName("Adding and removing a project")
    void testProjectOfTask() {
        Task task = new Task.Builder("Build a chair").build();
        task.changeProject("Renovation");
        assertThat(task.projectName()).contains("Renovation");
        task.deleteProject();
        assertThat(task.projectName()).isEmpty();

    }
}
