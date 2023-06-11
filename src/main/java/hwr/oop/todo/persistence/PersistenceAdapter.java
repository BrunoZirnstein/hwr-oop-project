package hwr.oop.todo.persistence;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;
import hwr.oop.todo.core.ToDoListId;

public interface PersistenceAdapter extends LoadListByIdOutPort, OverwriteListOutPort {
    void overwriteList(ToDoList toDoList);

    ToDoList loadListById(ToDoListId id);
}
