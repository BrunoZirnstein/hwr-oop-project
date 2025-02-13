package hwr.oop.todo.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test task")
class TaskTest {
    @Test
    @DisplayName("New Task created with Title and UUID")
    void newTask_WithOnlyTitle() {
        Task task = new Task.Builder("Water plants").build();
        TaskId id = task.id();
        assertThat(id).isInstanceOf(TaskId.class);
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
    void newTask_WithAllParameters() {
        Task task = new Task.Builder("Water plants").description(
                        "Water all the plants in the living room and in the bedroom.")
                .tags(List.of(new TaskTag("home")))
                .deadline(LocalDate.of(2023, 5, 30)).priority(TaskPriority.HIGH)
                .projectName("university").status(TaskStatus.IN_PROGRESS)
                .build();
        assertThat(task.title()).isEqualTo("Water plants");
        assertThat(task.tags()).isEqualTo(List.of(new TaskTag("home")));
        assertThat(task.description()).isEqualTo(
                "Water all the plants in the living room and in the bedroom.");
        assertThat(task.deadline()).isEqualTo(LocalDate.of(2023, 5, 30));
        assertThat(task.status()).isEqualTo(TaskStatus.IN_PROGRESS);
        assertThat(task.priority()).isEqualTo(TaskPriority.HIGH);
        assertThat(task.projectName()).contains(
                "university"); // contains checks if optional type value is equal to testProject
    }

    @Test
    @DisplayName("New Task contains one tag")
    void newTask_WithOneTag() {
        Task task = new Task.Builder("Water plants").build();
        TaskTag tag = new TaskTag("Watering");
        task.addTag(tag);
        assertThat(task.tags()).containsExactly(tag);

    }

    @Test
    @DisplayName("New Task contains multiple tags")
    void newTask_WithMultipleTags() {
        Task task = new Task.Builder("Water plants").build();
        TaskTag tag1 = new TaskTag("1");
        task.addTag(tag1);

        TaskTag tag2 = new TaskTag("2");
        task.addTag(tag2);

        assertThat(task.tags()).containsAll(Arrays.asList(tag1, tag2));
    }

    @Test
    @DisplayName("Adding and removing a task by Tagname")
    void removeTagByTagname() {
        Task task = new Task.Builder("Build a chair").build();
        task.addTag(new TaskTag("home"));
        TaskTag task2 = new TaskTag("comfort");
        task.addTag(task2);

        task.removeTagByName("home");
        assertThat(task.tags()).containsExactly(task2);
    }

    @Test
    @DisplayName("Adding and removing a task by Object")
    void removeTagByObject() {
        Task task = new Task.Builder("Build a chair").build();
        task.addTag(new TaskTag("home"));
        TaskTag task2 = new TaskTag("comfort");
        task.addTag(task2);
        TaskTag taskGet = task.tags().get(0);
        assertThat(task.tags().get(0)).isEqualTo(taskGet);

        task.removeTagByObject(taskGet);
        assertThat(task.tags()).containsExactly(task2);
    }

    @Test
    @DisplayName("Renaming a task")
    void renameTask() {
        Task task = new Task.Builder("Build a chair").build();
        task.renameTitle("Build a house");
        String taskTitle = task.title();
        assertThat(taskTitle).contains("Build a house");

    }

    @Test
    @DisplayName("Changing description of task")
    void descriptionOfTask() {
        Task task = new Task.Builder("Build a chair").description(
                "Build a chair out of wood").build();
        task.changeDescription("Build a chair out of metal");
        assertThat(task.description()).isEqualTo("Build a chair out of metal");
    }

    @Test
    @DisplayName("Changing deadline of task")
    void changeDeadline_ValidDate() {
        Task task = new Task.Builder("Build a chair").deadline(
                LocalDate.of(2023, 5, 1)).build();
        task.moveDeadline(LocalDate.of(2023, 6, 1));
        assertThat(task.deadline()).isEqualTo(LocalDate.of(2023, 6, 1));
    }

    @Test
    @DisplayName("Changing deadline of task with invalid date")
    void changeDeadline_InvalidDate_ThrowException() {
        try {
            Task task = new Task.Builder("Build a chair").deadline(
                    LocalDate.of(2023, 5, 1)).build();
            task.moveDeadline(LocalDate.of(0, 0, 0));
        } catch (DateTimeException e) {
            assertThat(e).isNotNull();
        }
    }

    @ParameterizedTest
    @DisplayName("Changing status of task")
    @EnumSource(TaskStatus.class)
    void statusOfTask(TaskStatus status) {
        Task task = new Task.Builder("Build a chair").build();
        task.updateStatus(status);
        TaskStatus statusName = task.status();
        assertThat(statusName).isEqualTo(status);
    }


    @ParameterizedTest
    @DisplayName("Changing priority of task")
    @EnumSource(TaskPriority.class)
    void priorityOfTask(TaskPriority priority) {
        Task task = new Task.Builder("Build a chair").build();
        task.changePriority(priority);
        TaskPriority prio = task.priority();
        assertThat(prio).isEqualTo(priority);
    }

    @Test
    @DisplayName("Adding a project")
    void projectOfTask_Changing() {
        Task task = new Task.Builder("Build a chair").build();
        task.changeProject("Renovation");
        assertThat(task.projectName()).contains("Renovation");

    }

    @Test
    @DisplayName("Removing a project")
    void projectOfTask_Removing() {
        Task task = new Task.Builder("Build a chair").projectName(
                "Test Project").build();
        task.deleteProject();
        assertThat(task.projectName()).isEmpty();
    }

}
