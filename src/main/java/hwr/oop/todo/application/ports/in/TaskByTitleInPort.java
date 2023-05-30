package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface TaskByTitleInPort {
    public Task taskByTitleInPort(
            TaskByTitleInPort.TaskByTitleCommand taskByTitleCommand);

    public class TaskByTitleCommand {
        private ToDoList list;
        private String title;

        private TaskByTitleCommand(ToDoList list, String title) {
            this.list = list;
            this.title = title;
        }
    }
}
