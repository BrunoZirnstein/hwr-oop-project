package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Task;

public interface CreateTaskInPort {
    void createTask(CreateTaskCommand createTaskCommand);

    record CreateTaskCommand(ToDoListId listId, Task newTask) {}
}
