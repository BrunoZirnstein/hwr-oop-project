package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface AddTaskToListInPort {
    public void addTaskToList(AddTaskToListCommand addTaskToListCommand);

    public class AddTaskToListCommand {
        public ToDoList list;
        public Task newTask;

        private AddTaskToListCommand(ToDoList list, Task newTask) {
            this.list = list;
            this.newTask = newTask;
        }
    }
}
