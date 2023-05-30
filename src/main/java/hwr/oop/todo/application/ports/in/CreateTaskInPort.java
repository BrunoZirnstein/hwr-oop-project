package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface CreateTaskInPort {
    void createTask(CreateTaskCommand createTaskCommand);

    record CreateTaskCommand(ToDoList list, Task newTask) {}
}
