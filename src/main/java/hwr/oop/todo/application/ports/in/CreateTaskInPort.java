package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface CreateTaskInPort {
    public void createTaskInPort(
            CreateTaskInPort.CreateTaskCommand createTaskCommand);

    public class CreateTaskCommand {
        private ToDoList list;
        private Task newTask;

        private CreateTaskCommand(ToDoList list, Task newTask) {
            this.list = list;
            this.newTask = newTask;
        }
    }
}
