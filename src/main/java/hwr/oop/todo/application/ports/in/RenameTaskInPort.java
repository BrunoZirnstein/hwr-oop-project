package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface RenameTaskInPort {
    public void renameTask(
            RenameTaskInPort.RenameTaskCommand renameTaskCommand);

    public class RenameTaskCommand {
        public ToDoList list;
        public Task task;
        public String newName;

        private RenameTaskCommand(ToDoList list, Task task, String newName) {
            this.list = list;
            this.task = task;
            this.newName = newName;

        }
    }
}
