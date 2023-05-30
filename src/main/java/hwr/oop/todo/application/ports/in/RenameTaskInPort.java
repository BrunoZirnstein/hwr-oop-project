package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface RenameTaskInPort {
    public void renameTaskInPort(
            RenameTaskInPort.RenameTaskCommand renameTaskCommand);

    public class RenameTaskCommand {
        private ToDoList list;
        private Task task;
        private String newName;

        private RenameTaskCommand(ToDoList list, Task task, String newName) {
            this.list = list;
            this.task = task;
            this.newName = newName;

        }
    }
}
