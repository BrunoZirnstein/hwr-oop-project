package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTest {
    @Test
    void createTaskWithOnlyTitleTest() {
        Task task = new Task.Builder("Water plants").build();
        assertThat(task.title()).isEqualTo("Water plants");
        assertThat(task.tags()).isEqualTo(new ArrayList<TaskTag>());
        assertThat(task.description()).isNull();
        assertThat(task.deadline()).isNull();
        assertThat(task.status()).isEqualTo(TaskStatus.TODO);
        assertThat(task.priority()).isNull();
    }

    @Test
    void createTaskWithParameters() {
        Task task = new Task.Builder("Water plants").description("Water all the plants in the living room and in the bedroom.").tags(List.of(new TaskTag("home"))).deadline(LocalDate.of(2023, 5, 30)).priority(TaskPriority.HIGH).build();
        assertThat(task.title()).isEqualTo("Water plants");
        assertThat(task.tags()).isEqualTo(List.of(new TaskTag("home")));
        assertThat(task.description()).isEqualTo("Water all the plants in the living room and in the bedroom.");
        assertThat(task.deadline()).isEqualTo(LocalDate.of(2023, 5, 30));
        assertThat(task.status()).isEqualTo(TaskStatus.TODO);
        assertThat(task.priority()).isEqualTo(TaskPriority.HIGH);
    }
}
