package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskPriority;
import hwr.oop.todo.core.ToDoList;

public interface ChangeTaskPriorityInPort {
    public void changeTaskPriority(
            ChangeTaskPriorityInPort.ChangeTaskPriorityCommand changeTaskPriorityCommand);

    public class ChangeTaskPriorityCommand {
        public ToDoList list;
        public Task task;
        public TaskPriority newPriority;

        private ChangeTaskPriorityCommand(ToDoList list, Task task,
                                          TaskPriority newPriority) {
            this.list = list;
            this.task = task;
            this.newPriority = newPriority;
        }
    }
}
