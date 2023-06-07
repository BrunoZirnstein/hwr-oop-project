package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ToDoList;

public interface CreateListInPort {
    ToDoList createList(CreateListCommand createListCommand);

    record CreateListCommand(String owner) {}
}
