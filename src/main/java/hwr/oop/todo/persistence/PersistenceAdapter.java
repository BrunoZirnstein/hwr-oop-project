package hwr.oop.todo.persistence;

import hwr.oop.todo.application.Task;
import hwr.oop.todo.application.ToDoList;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;

public interface PersistenceAdapter extends LoadListByIdOutPort, OverwriteListOutPort {
    public void overwriteList(ToDoList toDoList);
    public ToDoList loadListById(String id);
}
