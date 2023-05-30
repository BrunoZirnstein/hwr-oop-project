package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface DeleteTaskInPort {
    void deleteTask(DeleteTaskCommand deleteTaskCommand);

    record DeleteTaskCommand(ToDoList list, Task task) {}
}
