package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskTag;
import hwr.oop.todo.core.ToDoList;

public interface RemoveTaskTagInPort {
    public void removeTaskTag(
            RemoveTaskTagInPort.RemoveTaskTagCommand removeTaskTagCommand);

    public class RemoveTaskTagCommand {
        private ToDoList list;
        private Task task;
        private TaskTag taskTag;

        private RemoveTaskTagCommand(ToDoList list, Task task,
                                     TaskTag taskTag) {
            this.list = list;
            this.task = task;
            this.taskTag = taskTag;
        }
    }
}
