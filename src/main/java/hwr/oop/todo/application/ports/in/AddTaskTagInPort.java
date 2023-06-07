package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;

public interface AddTaskTagInPort {
    void addTaskTag(AddTaskTagCommand addTaskTagCommand);

    record AddTaskTagCommand(ToDoListId listId, TaskId taskId,
                             String newTagTitle) {}
}