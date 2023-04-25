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
        assertThat(task.project()).isEmpty();
    }

    @Test
    void createTaskWithParameters() {
        Project testProject = new Project("university", null);
        Task task = new Task.Builder("Water plants").description("Water all the plants in the living room and in the bedroom.").tags(List.of(new TaskTag("home"))).deadline(LocalDate.of(2023, 5, 30)).priority(TaskPriority.HIGH).project(testProject).build();
        assertThat(task.title()).isEqualTo("Water plants");
        assertThat(task.tags()).isEqualTo(List.of(new TaskTag("home")));
        assertThat(task.description()).isEqualTo("Water all the plants in the living room and in the bedroom.");
        assertThat(task.deadline()).isEqualTo(LocalDate.of(2023, 5, 30));
        assertThat(task.status()).isEqualTo(TaskStatus.TODO);
        assertThat(task.priority()).isEqualTo(TaskPriority.HIGH);
        assertThat(task.project()).contains(testProject); // contains checks if optional type value is equal to testProject
    }

    @Test
    void changeAttributesOfTask() {
        Project testProject = new Project("university", null);
        List<TaskTag> taglist = new ArrayList<>();
        taglist.add(new TaskTag("home"));
        Task task = new Task.Builder("Water plants").description("Water all the plants in the living room and in the bedroom.").tags(taglist).deadline(LocalDate.of(2023, 5, 30)).priority(TaskPriority.HIGH).project(testProject).build();
        task.renameTitle("Rollercoaster City");
        assertThat(task.title()).isEqualTo("Rollercoaster City");
        task.addTag(new TaskTag("Building"));
        assertThat(task.tags().get(1)).isEqualTo(new TaskTag("Building"));
        task.removeTag("home");
        assertThat(task.tags().get(0)).isEqualTo(new TaskTag("Building"));
        task.changeDescription("Let's build a Rollercoaster");
        assertThat(task.description()).isEqualTo("Let's build a Rollercoaster");
        task.moveDeadline(LocalDate.of(2024,2,2));
        assertThat(task.deadline()).isEqualTo(LocalDate.of(2024,2,2));
        task.updateStatus(TaskStatus.BLOCKED);
        assertThat(task.status()).isEqualTo(TaskStatus.BLOCKED);
        task.changePriority(TaskPriority.LOW);
        assertThat(task.priority()).isEqualTo(TaskPriority.LOW);
        Project testProject2 = new Project("Themepark", null);
        task.changeProject(testProject2);
        assertThat(task.project()).contains(testProject2);
        task.deleteProject();
        assertThat(task.project()).isEmpty();

    }
}
