package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskTag;
import hwr.oop.todo.core.ToDoList;

public interface AddTaskTagInPort {
    public void addTaskTag(AddTaskTagCommand addTaskTagCommand);

    public class AddTaskTagCommand {
        public ToDoList list;
        public Task task;
        public TaskTag newTaskTag;

        private AddTaskTagCommand(ToDoList list, Task task,
                                  TaskTag newTaskTag) {
            this.list = list;
            this.task = task;
            this.newTaskTag = newTaskTag;
        }
    }
}
