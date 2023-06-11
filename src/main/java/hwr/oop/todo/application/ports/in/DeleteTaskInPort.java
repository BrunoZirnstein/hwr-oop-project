package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;

public interface DeleteTaskInPort {
    void deleteTask(DeleteTaskCommand deleteTaskCommand);

    record DeleteTaskCommand(ToDoListId listId, TaskId taskId) {}
}
