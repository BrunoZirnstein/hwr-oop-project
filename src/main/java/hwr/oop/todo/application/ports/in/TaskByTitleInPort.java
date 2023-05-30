package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface TaskByTitleInPort {
    public Task taskByTitle(
            TaskByTitleInPort.TaskByTitleCommand taskByTitleCommand);

    public class TaskByTitleCommand {
        public ToDoList list;
        public String title;

        private TaskByTitleCommand(ToDoList list, String title) {
            this.list = list;
            this.title = title;
        }
    }
}
