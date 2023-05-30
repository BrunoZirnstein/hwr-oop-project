package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface AddTaskToListInPort {
    public void addTaskToListInPort(AddTaskToListCommand addTaskToListCommand);

    public class AddTaskToListCommand {
        private ToDoList list;
        private Task newTask;

        private AddTaskToListCommand(ToDoList list, Task newTask) {
            this.list = list;
            this.newTask = newTask;
        }
    }
}
