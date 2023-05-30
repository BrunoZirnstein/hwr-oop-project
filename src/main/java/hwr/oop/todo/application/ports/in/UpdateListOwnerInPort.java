package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface UpdateListOwnerInPort {
    Task updateListOwner(UpdateListOwnerCommand updateListOwnerCommand);

    record UpdateListOwnerCommand(ToDoList list, String newOwner) {}
}
