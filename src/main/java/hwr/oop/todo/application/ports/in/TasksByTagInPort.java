package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskTag;
import hwr.oop.todo.core.ToDoList;

import java.util.List;

public interface TasksByTagInPort {
    public List<Task> tasksByTagInPort(
            TasksByTagInPort.TasksByTagCommand tasksByTagCommand);

    public class TasksByTagCommand {
        private ToDoList list;
        private TaskTag taskTag;

        private TasksByTagCommand(ToDoList list, TaskTag taskTag) {
            this.list = list;
            this.taskTag = taskTag;
        }
    }
}
