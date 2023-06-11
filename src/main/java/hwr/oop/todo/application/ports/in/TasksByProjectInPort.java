package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ProjectId;
import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Task;

import java.util.List;

public interface TasksByProjectInPort {
    List<Task> tasksByProject(TasksByProjectCommand tasksByProjectCommand);

    record TasksByProjectCommand(ToDoListId listId, ProjectId projectId) {}
}
