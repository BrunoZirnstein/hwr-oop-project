package hwr.oop.todo;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Arrays;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ToDoListListTest {
    ToDoListListTest() {
    }

    @Test
    @DisplayName("New ToDo list is associated with user")
    void newToDoList_IsAssociatedWithUser() {
        ToDoList list = new ToDoList("Jason");
        Assertions.assertThat(list.user()).isEqualTo("Jason");
    }

    @Test
    @DisplayName("New ToDo list has correct initialization of tasks")
    void newToDoList_TasksInitialization() {
        ToDoList list = new ToDoList("Jason");
        Assertions.assertThat(list.tasks()).isEmpty();
    }

    @Test
    @DisplayName("New ToDo list has correct initialization of projects")
    void newToDoList_ProjectsInitialization() {
        ToDoList list = new ToDoList("Jason");
        Assertions.assertThat(list.projects()).isEmpty();
    }

    @Test
    @DisplayName("Add one task to list")
    void addTask_ContainsOneTask() {
        ToDoList list = new ToDoList("Jason");
        Task task = (new Task.Builder("Water plants")).build();
        list.addTask(task);
        Assertions.assertThat(list.tasks()).containsExactly(new Task[]{task});
    }

    @Test
    @DisplayName("Add two tasks to list")
    void addTask_ContainsTwoTasks() {
        ToDoList list = new ToDoList("Jason");
        Task task1 = (new Task.Builder("Water plants")).build();
        list.addTask(task1);
        Task task2 = (new Task.Builder("Clean floor")).build();
        list.addTask(task2);
        Assertions.assertThat(list.tasks()).containsAll(Arrays.asList(task1, task2));
    }

    @Test
    @DisplayName("Cannot add task with non-existent project name")
    void addTask_InvalidProjectName_ThrowException() {
        ToDoList list = new ToDoList("Bruno");
        Task task = (new Task.Builder("Water plants")).projectName("non-existent project name").build();
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            list.addTask(task);
        }).isInstanceOf(InvalidParameterException.class)).hasMessageStartingWith("Cannot add project to ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Add task with project name")
    void addTask_WithProject() {
        ToDoList list = new ToDoList("Bruno");
        Task task = (new Task.Builder("Water plants")).projectName("university").build();
        Project project = new Project("university", (LocalDate)null);
        list.addProject(project);
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            list.addTask(task);
        });
    }

    @Test
    @DisplayName("Remove given task object from list")
    void removeTaskByObject() {
        ToDoList list = new ToDoList("Jason");
        Task task1 = (new Task.Builder("Water plants")).build();
        list.addTask(task1);
        Task task2 = (new Task.Builder("Clean floor")).build();
        list.addTask(task2);
        list.removeTaskByObject(task1);
        Assertions.assertThat(list.tasks()).containsExactly(new Task[]{task2});
    }

    @Test
    @DisplayName("Remove task with given name from list")
    void removeTaskByName() {
        ToDoList list = new ToDoList("Jason");
        Task task1 = (new Task.Builder("Water plants")).build();
        list.addTask(task1);
        Task task2 = (new Task.Builder("Clean floor")).build();
        list.addTask(task2);
        list.removeTaskByName("Water plants");
        Assertions.assertThat(list.tasks()).containsExactly(new Task[]{task2});
    }

    @Test
    @DisplayName("List owner can be updated")
    void updateOwner() {
        ToDoList list = new ToDoList("Jason");
        list.updateOwner("Thomas");
        Assertions.assertThat(list.user()).isEqualTo("Thomas");
    }

    @Test
    @DisplayName("Create project in list")
    void addProject() {
        ToDoList list = new ToDoList("Bruno");
        Project project = new Project("university", (LocalDate)null);
        list.addProject(project);
        Assertions.assertThat(list.projects()).containsExactly(new Project[]{project});
    }

    @Test
    @DisplayName("Remove project from list by name")
    void removeProject() {
        ToDoList list = new ToDoList("Bruno");
        Project project = new Project("university", (LocalDate)null);
        list.addProject(project);
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            list.removeProject("university");
        });
        Assertions.assertThat(list.projects()).isEmpty();
    }

    @Test
    @DisplayName("Remove given project object from list")
    void removeProjectByObject() {
        ToDoList list = new ToDoList("Bruno");
        Project project = new Project("university", (LocalDate)null);
        list.addProject(project);
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            list.removeProjectByObject(project);
        });
        Assertions.assertThat(list.projects()).isEmpty();
    }

    @Test
    @DisplayName("Get task with given title, valid task")
    void taskByTitle_ValidTask() {
        ToDoList list = new ToDoList("Jason");
        Task task = (new Task.Builder("Water plants")).build();
        list.addTask(task);
        Assertions.assertThat(list.taskByTitle("Water plants")).isEqualTo(task);
    }

    @Test
    @DisplayName("Get task with given title, empty task list")
    void taskByTitle_EmptyTasks() {
        ToDoList list = new ToDoList("Jason");
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            list.taskByTitle("Clean floor");
        }).isInstanceOf(InvalidParameterException.class)).hasMessageStartingWith("Cannot get task from ToDo list, given task title does not exist.");
    }

    @Test
    @DisplayName("Get task with invalid title")
    void taskByTitle_InvalidTask_ThrowException() {
        ToDoList list = new ToDoList("Jason");
        Task task = (new Task.Builder("Water plants")).build();
        list.addTask(task);
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            list.taskByTitle("Clean floor");
        }).isInstanceOf(InvalidParameterException.class)).hasMessageStartingWith("Cannot get task from ToDo list, given task title does not exist.");
    }

    @Test
    @DisplayName("Get tasks by tag name, no matched task")
    void taskByTagName_NoTagNameMatch() {
        ToDoList list = new ToDoList("Jason");
        Task task = (new Task.Builder("Call Bruno")).tags(Arrays.asList(new TaskTag("Phone"))).build();
        list.addTask(task);
        Assertions.assertThat(list.taskByTagName("Home")).isEmpty();
    }

    @Test
    @DisplayName("Get tasks by tag name, one task with one tag")
    void taskByTagName_OneTask_OneTag() {
        ToDoList list = new ToDoList("Jason");
        Task task = (new Task.Builder("Call Bruno")).tags(Arrays.asList(new TaskTag("Phone"))).build();
        list.addTask(task);
        Assertions.assertThat(list.taskByTagName("Phone")).containsExactly(new Task[]{task});
    }

    @Test
    @DisplayName("Get tasks by tag name, two tasks with one and two tags")
    void taskByTagName_TwoTasks_TwoTags() {
        ToDoList list = new ToDoList("Jason");
        TaskTag taskTagHome = new TaskTag("Home");
        TaskTag taskTagFreeTime = new TaskTag("Free Time");
        Task task1 = (new Task.Builder("Water plants")).tags(Arrays.asList(taskTagHome, taskTagFreeTime)).build();
        Task task2 = (new Task.Builder("Home office")).tags(Arrays.asList(taskTagHome)).build();
        list.addTask(task1);
        list.addTask(task2);
        Assertions.assertThat(list.taskByTagName("Home")).containsExactly(new Task[]{task1, task2});
        Assertions.assertThat(list.taskByTagName("Free Time")).containsExactly(new Task[]{task1});
    }

    @Test
    @DisplayName("Get tasks by project name, no projects in list")
    void taskByProject_EmptyProjects_ThrowException() {
        ToDoList list = new ToDoList("Jason");
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            list.taskByProject("university");
        }).isInstanceOf(InvalidParameterException.class)).hasMessageStartingWith("Cannot get tasks from ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Get tasks by project name, deleted project in list")
    void taskByProject_WithDeletedProject_ThrowException() {
        ToDoList list = new ToDoList("Jason");
        Project project = new Project("university", LocalDate.of(2023, 4, 4));
        list.addProject(project);
        list.addTask((new Task.Builder("Water plants")).projectName("university").build());
        list.removeProjectByObject(project);
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            list.taskByProject("university");
        }).isInstanceOf(InvalidParameterException.class)).hasMessageStartingWith("Cannot get tasks from ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Get tasks by project name, valid project, no task found")
    void taskByProject_ValidProject_NoTask() {
        ToDoList list = new ToDoList("Jason");
        Project project = new Project("university", LocalDate.of(2023, 4, 4));
        list.addProject(project);
        Assertions.assertThat(list.taskByProject("university")).isEmpty();
    }

    @Test
    @DisplayName("Get tasks by project name, valid project")
    void taskByProject_ValidProject_OneTask() {
        ToDoList list = new ToDoList("Jason");
        Project project = new Project("university", LocalDate.of(2023, 4, 4));
        Task universityTask = (new Task.Builder("Study OOP")).projectName("university").build();
        list.addProject(project);
        list.addTask(universityTask);
        Assertions.assertThat(list.taskByProject("university")).containsExactly(new Task[]{universityTask});
    }

    @Test
    @DisplayName("Get project object by name, valid project")
    void getProjectByName_ValidProject() {
        ToDoList list = new ToDoList("Bruno");
        Project project = new Project("university", LocalDate.of(2023, 4, 4));
        list.addProject(project);
        Assertions.assertThat(list.getProjectByName("university")).isEqualTo(project);
    }

    @Test
    @DisplayName("Get project object by name, invalid project")
    void getProjectByName_InvalidProject_ThrowException() {
        ToDoList list = new ToDoList("Bruno");
        Project project = new Project("home", LocalDate.of(2023, 4, 4));
        list.addProject(project);
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            list.getProjectByName("university");
        }).isInstanceOf(InvalidParameterException.class)).hasMessageStartingWith("Cannot get project object from ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Get project object by name, empty projects")
    void getProjectByName_EmptyProjects_ThrowException() {
        ToDoList list = new ToDoList("Bruno");
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            list.getProjectByName("university");
        }).isInstanceOf(InvalidParameterException.class)).hasMessageStartingWith("Cannot get project object from ToDo list, given project name does not exist.");
    }
}
