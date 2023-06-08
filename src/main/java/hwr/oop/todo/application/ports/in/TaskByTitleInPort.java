package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Task;

public interface TaskByTitleInPort {
    Task taskByTitle(TaskByTitleCommand taskByTitleCommand);

    record TaskByTitleCommand(ToDoListId listId, String title) {}
}
