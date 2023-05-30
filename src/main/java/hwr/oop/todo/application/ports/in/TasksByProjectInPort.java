package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

import java.util.List;

public interface TasksByProjectInPort {
    List<Task> tasksByProject(TasksByProjectCommand tasksByProjectCommand);

    record TasksByProjectCommand(ToDoList list, Project project) {}
}
