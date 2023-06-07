package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.TaskTag;

public interface RemoveTaskTagInPort {
    void removeTaskTag(RemoveTaskTagCommand removeTaskTagCommand);

    record RemoveTaskTagCommand(ToDoListId listId, TaskId taskId, TaskTag taskTag) {}
}
