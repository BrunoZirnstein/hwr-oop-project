package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface UpdateListOwnerInPort {
    public Task updateListOwner(
            UpdateListOwnerInPort.UpdateListOwnerCommand updateListOwnerCommand);

    public class UpdateListOwnerCommand {
        public ToDoList list;
        public String newOwner;

        private UpdateListOwnerCommand(ToDoList list, String newOwner) {
            this.list = list;
            this.newOwner = newOwner;
        }
    }
}
