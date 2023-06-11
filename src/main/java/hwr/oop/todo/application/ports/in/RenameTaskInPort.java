package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;

public interface RenameTaskInPort {
    void renameTask(RenameTaskCommand renameTaskCommand);

    record RenameTaskCommand(ToDoListId listId, TaskId taskId, String newName) {}
}
