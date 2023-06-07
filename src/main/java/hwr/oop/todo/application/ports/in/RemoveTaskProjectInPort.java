package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;

public interface RemoveTaskProjectInPort {
    void removeTaskProject(RemoveTaskProjectCommand removeTaskProjectCommand);

    record RemoveTaskProjectCommand(ToDoListId listId, TaskId taskId) {}
}
