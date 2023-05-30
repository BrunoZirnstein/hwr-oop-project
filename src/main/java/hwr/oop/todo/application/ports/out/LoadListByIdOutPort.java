package hwr.oop.todo.application.ports.out;

import hwr.oop.todo.core.ToDoList;

public interface LoadListByIdOutPort {
    ToDoList loadListById(String id);
}
