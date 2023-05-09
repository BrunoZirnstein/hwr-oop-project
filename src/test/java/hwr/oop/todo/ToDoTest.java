package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class ToDoTest {

    @Test
    void testTaskMethods() {
        ToDo list = new ToDo("Jason");
        assertThat(list.user()).isEqualTo("Jason");
        Task task1 = new Task.Builder("Test").build();
        list.addTask(task1);
        assertThat(list.tasks().get(0)).isEqualTo(task1);

        list.removeTaskByObject(task1);
        assertThat(list.tasks()).isEmpty();

        list.changeUser("Thomas");
        assertThat(list.user()).isEqualTo("Thomas");
    }


    @Test
    void testProjectMethods() {
        ToDo list = new ToDo("Bruno");
        assertThat(list.projects()).contains(new ArrayList<>());

        Project testProject = new Project("university", null);
        list.addProject(testProject);
        assertThat(list.projects().get().get(0)).isEqualTo(testProject);
        assertDoesNotThrow(() -> list.removeProject("university"));
        assertThat(list.projects()).contains(new ArrayList<>());
    }

    @Test
    void testAddTaskThrowException() {
        ToDo list = new ToDo("Bruno");
        Task testTask = new Task.Builder("water plants").projectName("none existing project name").build();

        assertThatThrownBy(() -> list.addTask(testTask))
                .isInstanceOf(RuntimeException.class)
                .hasMessageStartingWith("Cannot add project to ToDo list, given project name does not exist.");
    }

    @Test
    void testTaskByTitle() {
        ToDo list = new ToDo("Jason");
        Task task1 = new Task.Builder("Test").build();
        list.addTask(task1);
        assertThat(task1).isEqualTo(list.taskByTitle("Test"));
        list.removeTaskByObject(task1);
        assertThat(task1).isNotIn(list);
        list.addTask(task1);
        list.removeTaskByName("Test");
        assertThat(task1).isNotIn(list);
        assertThat(list.taskByTitle("Test1")).isNull();
    }


    @Test
    void testTaskByTagname() {
        ToDo user = new ToDo("Jason");
        ArrayList<TaskTag> taglist = new ArrayList<>();
        ArrayList<TaskTag> taglist2 = new ArrayList<>();
        taglist.add(new TaskTag("1"));
        taglist.add(new TaskTag("2"));
        taglist2.add(new TaskTag("3"));
        user.addTask(new Task.Builder("Amen").tags(taglist).build());
        user.addTask(new Task.Builder("Bamen").tags(taglist).build());
        user.addTask(new Task.Builder("Damen").tags(taglist2).build());
        assertThat(user.taskByTagname("1")).contains(user.taskByTitle("Amen"), user.taskByTitle("Bamen"));
        assertThat(user.taskByTagname("3")).contains(user.taskByTitle("Damen"));
    }

    @Test
    void testTaskByProject() {
        Project project = new Project("TestProject", LocalDate.of(2023, 4, 4));
        ToDo user = new ToDo("Jason");
        user.createProject(project);
        assertThat(user.projects().get().get(0)).isEqualTo(project);
        user.addTask(new Task.Builder("Namen").projectName(project.title()).build());
        assertThat(user.tasks().get(0)).isEqualTo(user.taskByProject("TestProject").get(0));
        user.deleteProject(project);
        assertThat(user.projects()).contains(new ArrayList<>());
    }
}