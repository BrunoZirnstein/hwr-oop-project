package hwr.oop.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class ProjectTest {
    @Test
    void testProject() {
        Assertions.assertDoesNotThrow(() -> new Project("Test", LocalDate.of(2000, 1, 1)));
        Project project = new Project("Test", LocalDate.of(2000, 1, 1));
        assertThat(project.title()).isEqualTo("Test");
    }
/*
    @Test
    void testTask() {
        ArrayList<Task> task_list = new ArrayList<Task>();
        Project project = new Project("", task_list, LocalDate.of(2000, 1, 1));

        project.addTask(new Task(null, null, null, null, null, null));
        assertThat(project.tasklist().get(0)).isNotNull();

        project.deleteTask(project.tasklist().get(0));
        assertThat(project.tasklist()).isEmpty();
    }

    @Test
    void taskByTagname() {
        ArrayList<Task> list = new ArrayList<Task>();
        Project project = new Project("Test", list, LocalDate.of(2023, 01, 01));
        ArrayList<TaskTag> taglist = new ArrayList<TaskTag>();
        ArrayList<TaskTag> taglist2 = new ArrayList<TaskTag>();
        taglist.add(new TaskTag("1"));
        taglist.add(new TaskTag("2"));
        taglist2.add(new TaskTag("3"));
        project.addTask(new Task("Amen", taglist, null, null, null, null));
        project.addTask(new Task("Bamen", taglist, null, null, null, null));
        project.addTask(new Task("Damen", taglist2, null, null, null, null));
        System.out.println(project.taskByTagname("1"));
        System.out.println(project.taskByTitle("Amen"));
        System.out.println(project.taskByTitle("Bamen"));
        assertThat(project.taskByTagname("1")).contains(project.taskByTitle("Amen"), project.taskByTitle("Bamen"));
        assertThat(project.taskByTagname("3")).contains(project.taskByTitle("Damen"));
    }
*/
    @Test
    void testDeadline() {
        Project project = new Project("Test", LocalDate.of(2000, 1, 1));
        assertThat(project.getDeadline()).isEqualTo(LocalDate.of(2000, 1, 1));
        LocalDate date = LocalDate.of(2024, 4, 4);
        project.setDeadline(date);
        assertThat(date).isEqualTo(project.getDeadline());
    }
}