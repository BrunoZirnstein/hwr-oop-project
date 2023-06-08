package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskStatus;

public interface UpdateTaskStatusInPort {
    Task updateTaskStatus(UpdateTaskStatusCommand updateTaskStatusCommand);

    record UpdateTaskStatusCommand(ToDoListId listId, TaskId taskId,
                                   TaskStatus newStatus) {}
}
