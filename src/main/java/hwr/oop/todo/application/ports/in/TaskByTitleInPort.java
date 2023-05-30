package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface TaskByTitleInPort {
    Task taskByTitle(TaskByTitleCommand taskByTitleCommand);

    record TaskByTitleCommand(ToDoList list, String title) {}
}
