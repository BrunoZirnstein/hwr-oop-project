package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface DeleteTaskInPort {
    public void deleteTask(
            DeleteTaskInPort.DeleteTaskCommand deleteTaskCommand);

    public class DeleteTaskCommand {
        public ToDoList list;
        public Task task;

        private DeleteTaskCommand(ToDoList list, Task task) {
            this.list = list;
            this.task = task;
        }
    }
}
