package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskTag;
import hwr.oop.todo.core.ToDoList;

import java.util.List;

public interface TasksByTagInPort {
    public List<Task> tasksByTag(
            TasksByTagInPort.TasksByTagCommand tasksByTagCommand);

    public class TasksByTagCommand {
        public ToDoList list;
        public TaskTag taskTag;

        private TasksByTagCommand(ToDoList list, TaskTag taskTag) {
            this.list = list;
            this.taskTag = taskTag;
        }
    }
}
