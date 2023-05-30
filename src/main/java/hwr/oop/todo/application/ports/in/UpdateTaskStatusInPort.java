package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskStatus;
import hwr.oop.todo.core.ToDoList;

public interface UpdateTaskStatusInPort {
    Task updateTaskStatus(UpdateTaskStatusCommand updateTaskStatusCommand);

    record UpdateTaskStatusCommand(ToDoList list, Task task,
                                   TaskStatus newStatus) {}
}
