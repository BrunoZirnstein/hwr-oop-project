package hwr.oop.todo.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ToDoListTest {
    @Test
    @DisplayName("New ToDo list is associated with user")
    void newToDoList_IsAssociatedWithUser() {
        ToDoList list = new ToDoList("Jason");
        assertThat(list.owner()).isEqualTo("Jason");
    }

    @Test
    @DisplayName("New ToDo list has correct initialization of tasks")
    void newToDoList_TasksInitialization() {
        ToDoList list = new ToDoList("Jason");
        List<Task> result = list.tasks();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("New ToDo list has correct initialization of projects")
    void newToDoList_ProjectsInitialization() {
        ToDoList list = new ToDoList("Jason");
        List<Project> result = list.projects();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Add one task to list")
    void addTask_ContainsOneTask() {
        ToDoList list = new ToDoList("Jason");
        Task task = new Task.Builder("Water plants").build();
        list.addTask(task);
        assertThat(list.tasks()).containsExactly(task);
    }

    @Test
    @DisplayName("Add two tasks to list")
    void addTask_ContainsTwoTasks() {
        ToDoList list = new ToDoList("Jason");

        Task task1 = new Task.Builder("Water plants").build();
        list.addTask(task1);

        Task task2 = new Task.Builder("Clean floor").build();
        list.addTask(task2);

        assertThat(list.tasks()).containsAll(Arrays.asList(task1, task2));
    }

    @Test
    @DisplayName("Cannot add task with non-existent project name")
    void addTask_InvalidProjectName_ThrowException() {
        ToDoList list = new ToDoList("Bruno");

        Task task = new Task.Builder("Water plants").projectName(
                "non-existent project name").build();

        assertThatThrownBy(() -> list.addTask(task)).isInstanceOf(
                InvalidParameterException.class).hasMessage(
                "Cannot add task to ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Add task with project name")
    void addTask_WithProject() {
        ToDoList list = new ToDoList("Bruno");
        Project project = new Project("university", null);
        list.addProject(project);

        Task task = new Task.Builder("Water plants").projectName("university")
                .build();

        assertDoesNotThrow(() -> list.addTask(task));
    }

    @Test
    @DisplayName("Remove given task object from list")
    void removeTaskByObject() {
        ToDoList list = new ToDoList("Jason");

        Task task1 = new Task.Builder("Water plants").build();
        list.addTask(task1);
        Task task2 = new Task.Builder("Clean floor").build();
        list.addTask(task2);

        list.removeTaskByObject(task1);

        List<Task> tasks = list.tasks();
        assertThat(tasks).containsExactly(task2);
    }

    @Test
    @DisplayName("Remove task with given name from list")
    void removeTaskByName() {
        ToDoList list = new ToDoList("Jason");

        Task task1 = new Task.Builder("Water plants").build();
        list.addTask(task1);
        Task task2 = new Task.Builder("Clean floor").build();
        list.addTask(task2);

        list.removeTaskByName("Water plants");

        List<Task> tasks = list.tasks();
        assertThat(tasks).containsExactly(task2);
    }

    @Test
    @DisplayName("List owner can be updated")
    void updateOwner() {
        ToDoList list = new ToDoList("Jason");
        list.updateOwner("Thomas");

        String owner = list.owner();
        assertThat(owner).isEqualTo("Thomas");
    }

    @Test
    @DisplayName("Create project in list")
    void addProject() {
        ToDoList list = new ToDoList("Bruno");
        Project project = new Project("university", null);

        list.addProject(project);

        List<Project> projects = list.projects();
        assertThat(projects).containsExactly(project);
    }

    @Test
    @DisplayName("Remove project from list by name")
    void removeProject() {
        ToDoList list = new ToDoList("Bruno");

        Project project = new Project("university", null);
        list.addProject(project);

        assertDoesNotThrow(() -> list.removeProject("university"));

        List<Project> projects = list.projects();
        assertThat(projects).isEmpty();
    }

    @Test
    @DisplayName("Remove given project object from list")
    void removeProjectByObject() {
        ToDoList list = new ToDoList("Bruno");

        Project project = new Project("university", null);
        list.addProject(project);

        assertDoesNotThrow(() -> list.removeProjectByObject(project));

        List<Project> projects = list.projects();
        assertThat(projects).isEmpty();
    }

    @Test
    @DisplayName("Get task with given title, valid task")
    void taskByTitle_ValidTask() {
        ToDoList list = new ToDoList("Jason");

        Task task = new Task.Builder("Water plants").build();
        list.addTask(task);

        Task filteredTask = list.taskByTitle("Water plants");
        assertThat(filteredTask).isEqualTo(task);
    }

    @Test
    @DisplayName("Get task with given title, empty task list")
    void taskByTitle_EmptyTasks() {
        ToDoList list = new ToDoList("Jason");
        assertThatThrownBy(() -> list.taskByTitle("Clean floor")).isInstanceOf(
                InvalidParameterException.class).hasMessage(
                "Cannot get task from ToDo list, given task title does not exist.");
    }

    @Test
    @DisplayName("Get task with invalid title")
    void taskByTitle_InvalidTask_ThrowException() {
        ToDoList list = new ToDoList("Jason");

        Task task = new Task.Builder("Water plants").build();
        list.addTask(task);

        assertThatThrownBy(() -> list.taskByTitle("Clean floor")).isInstanceOf(
                InvalidParameterException.class).hasMessage(
                "Cannot get task from ToDo list, given task title does not exist.");
    }

    @Test
    @DisplayName("Get tasks by tag name, no matched task")
    void tasksByTagName_NoTagNameMatch() {
        ToDoList list = new ToDoList("Jason");

        Task task = new Task.Builder("Call Bruno").tags(
                Collections.singletonList(new TaskTag("Phone"))).build();
        list.addTask(task);

        List<Task> filteredTasks = list.tasksByTagName("Home");
        assertThat(filteredTasks).isEmpty();
    }

    @Test
    @DisplayName("Get tasks by tag name, one task with one tag")
    void tasksByTagName_OneTask_OneTag() {
        ToDoList list = new ToDoList("Jason");

        TaskTag taskTag = new TaskTag("Phone");
        Task task = new Task.Builder("Call Bruno").tags(
                Collections.singletonList(taskTag)).build();
        list.addTask(task);

        List<Task> filteredTasks = list.tasksByTagName("Phone");
        assertThat(filteredTasks).containsExactly(task);
    }

    @Test
    @DisplayName("Get tasks by tag name, two tasks with one and two tags")
    void tasksByTagName_TwoTasks_TwoTags() {
        ToDoList list = new ToDoList("Jason");

        TaskTag taskTagHome = new TaskTag("Home");
        TaskTag taskTagFreeTime = new TaskTag("Free Time");

        List<TaskTag> task1Tags = Arrays.asList(taskTagHome, taskTagFreeTime);
        Task task1 = new Task.Builder("Water plants").tags(task1Tags).build();
        list.addTask(task1);


        List<TaskTag> task2Tags = Collections.singletonList(taskTagHome);
        Task task2 = new Task.Builder("Home office").tags(task2Tags).build();
        list.addTask(task2);

        List<Task> filteredTasksHome = list.tasksByTagName("Home");
        assertThat(filteredTasksHome).containsExactly(task1, task2);

        List<Task> filteredTasksFreeTime = list.tasksByTagName("Free Time");
        assertThat(filteredTasksFreeTime).containsExactly(task1);
    }

    @Test
    @DisplayName("Get tasks by project name, no projects in list")
    void tasksByProject_EmptyProjects_ThrowException() {
        ToDoList list = new ToDoList("Jason");
        assertThatThrownBy(
                () -> list.tasksByProject("university")).isInstanceOf(
                InvalidParameterException.class).hasMessage(
                "Cannot get tasks from ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Get tasks by project name, invalid project name")
    void tasksByProject_InvalidProjects_ThrowException() {
        ToDoList list = new ToDoList("Jason");

        LocalDate projectDeadline = LocalDate.of(2023, 4, 4);
        Project project = new Project("university", projectDeadline);
        list.addProject(project);

        assertThatThrownBy(
                () -> list.tasksByProject("work")).isInstanceOf(
                InvalidParameterException.class).hasMessage(
                "Cannot get tasks from ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Get tasks by project name, deleted project in list")
    void tasksByProject_WithDeletedProject_ThrowException() {
        ToDoList list = new ToDoList("Jason");

        LocalDate projectDeadline = LocalDate.of(2023, 4, 4);
        Project project = new Project("university", projectDeadline);
        list.addProject(project);

        Task task = new Task.Builder("Water plants").projectName("university")
                .build();
        list.addTask(task);

        list.removeProjectByObject(project);

        assertThatThrownBy(
                () -> list.tasksByProject("university")).isInstanceOf(
                InvalidParameterException.class).hasMessage(
                "Cannot get tasks from ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Get tasks by project name, valid project, no task found")
    void tasksByProject_ValidProject_NoTask() {
        ToDoList list = new ToDoList("Jason");

        LocalDate date = LocalDate.of(2023, 4, 4);
        Project project = new Project("university", date);
        list.addProject(project);

        List<Task> filteredTasks = list.tasksByProject("university");
        assertThat(filteredTasks).isEmpty();
    }

    @Test
    @DisplayName("Get tasks by project name, valid project, multiple projects available)")
    void tasksByProject_ValidProject_MultipleAvailableProjects_OneTask() {
        ToDoList list = new ToDoList("Jason");

        LocalDate projectDate = LocalDate.of(2023, 4, 4);
        Project project = new Project("university", projectDate);
        list.addProject(project);

        Project otherProject = new Project("home", projectDate);
        list.addProject(otherProject);

        Task universityTask = new Task.Builder("Study OOP").projectName(
                "university").build();
        list.addTask(universityTask);

        Task homeTask = new Task.Builder("Clean floor").projectName("home").build();
        list.addTask(homeTask);

        List<Task> filteredTasks = list.tasksByProject("university");
        assertThat(filteredTasks).containsExactly(universityTask);
    }

    @Test
    @DisplayName("Get project object by name, valid project")
    void projectByName_ValidProject() {
        ToDoList list = new ToDoList("Bruno");

        LocalDate projectDate = LocalDate.of(2023, 4, 4);
        Project project = new Project("university", projectDate);
        list.addProject(project);

        Project filteredProject = list.projectByName("university");
        assertThat(filteredProject).isEqualTo(project);
    }

    @Test
    @DisplayName("Get project object by name, invalid project")
    void projectByName_InvalidProject_ThrowException() {
        ToDoList list = new ToDoList("Bruno");

        LocalDate projectDate = LocalDate.of(2023, 4, 4);
        Project project = new Project("home", projectDate);
        list.addProject(project);

        assertThatThrownBy(
                () -> list.projectByName("university")).isInstanceOf(
                InvalidParameterException.class).hasMessage(
                "Cannot get project object from ToDo list, given project name does not exist.");
    }

    @Test
    @DisplayName("Get project object by name, empty projects")
    void projectByName_EmptyProjects_ThrowException() {
        ToDoList list = new ToDoList("Bruno");
        
        assertThatThrownBy(
                () -> list.projectByName("university")).isInstanceOf(
                InvalidParameterException.class).hasMessage(
                "Cannot get project object from ToDo list, given project name does not exist.");
    }
}
