package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Task;

public interface UpdateListOwnerInPort {
    Task updateListOwner(UpdateListOwnerCommand updateListOwnerCommand);

    record UpdateListOwnerCommand(ToDoListId listId, String newOwner) {}
}
