package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskStatus;
import hwr.oop.todo.core.ToDoList;

public interface UpdateTaskStatusInPort {
    public Task updateTaskStatus(
            UpdateTaskStatusInPort.UpdateTaskStatusCommand updateTaskStatusCommand);

    public class UpdateTaskStatusCommand {
        public ToDoList list;
        public Task task;
        public TaskStatus newStatus;

        private UpdateTaskStatusCommand(ToDoList list, Task task,
                                        TaskStatus newStatus) {
            this.list = list;
            this.task = task;
            this.newStatus = newStatus;
        }
    }
}
