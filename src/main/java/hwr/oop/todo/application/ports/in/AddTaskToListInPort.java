package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface AddTaskToListInPort {
    void addTaskToList(AddTaskToListCommand addTaskToListCommand);

    record AddTaskToListCommand(ToDoList list, Task newTask) {}
}
