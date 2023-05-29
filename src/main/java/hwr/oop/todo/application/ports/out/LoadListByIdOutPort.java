package hwr.oop.todo.application.ports.out;

import hwr.oop.todo.application.ToDoList;

public interface LoadListByIdOutPort {
    public ToDoList loadListById(String id);
}
