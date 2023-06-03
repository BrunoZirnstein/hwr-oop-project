package hwr.oop.todo.application.ports.out;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.core.ToDoListId;

public interface LoadListByIdOutPort {
    ToDoList loadListById(ToDoListId id);
}
