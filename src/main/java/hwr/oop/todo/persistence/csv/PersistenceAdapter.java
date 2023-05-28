package hwr.oop.todo.persistence.csv;

import hwr.oop.todo.application.Project;
import hwr.oop.todo.application.Task;
import hwr.oop.todo.application.ToDoList;

import java.io.IOException;
import java.util.List;

public interface PersistenceAdapter {
    public void saveNewTask(Task task, ToDoList todo) throws IOException;
    public void saveNewQuickTask(Task task, ToDoList todo) throws IOException;
    public void saveNewToDoList(ToDoList todo) throws IOException;
    public void saveNewProject(Project project, ToDoList todo) throws IOException;
    public List<Task> loadAllTasksFromFile() throws IOException;
    public List<Project> loadAllProjectFromFile() throws IOException;
    public List<ToDoList> loadAllToDoListUsersFromFile() throws IOException;
    public ToDoList loadAToDoListFromUserWithAllTasks(ToDoList todo) throws IOException;
    public void removeTask(String id) throws IOException;

}
