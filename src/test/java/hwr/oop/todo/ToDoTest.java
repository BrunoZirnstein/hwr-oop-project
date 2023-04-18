package hwr.oop.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


class ToDoTest {

    @Test
    void testTaskMethods() {
        ToDo list = new ToDo("Jason");
        Task task1 = new Task("Test",null,null,null,null,null, null);
        list.addTask(task1);
        assertThat(list.tasks().get(0)).isEqualTo(task1);

        list.removeTask(task1);
        assertThat(list.tasks()).isEmpty();
    }

    @Test
    void taskByTitle() {
    }


    @Test
    void taskByTagname() {
        ArrayList<Task> list = new ArrayList<Task>();
        ToDo user = new ToDo("Jason");
        ArrayList<TaskTag> taglist = new ArrayList<TaskTag>();
        ArrayList<TaskTag> taglist2 = new ArrayList<TaskTag>();
        taglist.add(new TaskTag("1"));
        taglist.add(new TaskTag("2"));
        taglist2.add(new TaskTag("3"));
        user.addTask(new Task("Amen", taglist, null, null, null, null, null));
        user.addTask(new Task("Bamen", taglist, null, null, null, null, null));
        user.addTask(new Task("Damen", taglist2, null, null, null, null, null));
        System.out.println(user.taskByTagname("1"));
        System.out.println(user.taskByTitle("Amen"));
        System.out.println(user.taskByTitle("Bamen"));
        assertThat(user.taskByTagname("1")).contains(user.taskByTitle("Amen"), user.taskByTitle("Bamen"));
        assertThat(user.taskByTagname("3")).contains(user.taskByTitle("Damen"));
    }

    @Test
    void taskByProject() {
    }

    @Test
    void updateTask() {
    }
}