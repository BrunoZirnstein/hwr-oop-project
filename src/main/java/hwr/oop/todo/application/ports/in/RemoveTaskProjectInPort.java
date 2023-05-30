package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface RemoveTaskProjectInPort {
    void removeTaskProject(RemoveTaskProjectCommand removeTaskProjectCommand);

    record RemoveTaskProjectCommand(ToDoList list, Task task) {}
}
