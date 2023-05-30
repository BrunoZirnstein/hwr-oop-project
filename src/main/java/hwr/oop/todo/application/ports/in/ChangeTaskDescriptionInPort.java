package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface ChangeTaskDescriptionInPort {
    public void changeTaskDescription(
            ChangeTaskDescriptionInPort.ChangeTaskDescriptionCommand changeTaskDescriptionCommand);

    public class ChangeTaskDescriptionCommand {
        private ToDoList list;
        private Task task;
        private String newDescription;

        private ChangeTaskDescriptionCommand(ToDoList list, Task task,
                                             String newDescription) {
            this.list = list;
            this.task = task;
            this.newDescription = newDescription;
        }
    }
}
