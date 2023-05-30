package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface RenameTaskInPort {
    void renameTask(RenameTaskCommand renameTaskCommand);

    record RenameTaskCommand(ToDoList list, Task tasks, String newName) {}
}
