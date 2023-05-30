package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

import java.time.LocalDate;

public interface MoveTaskDeadlineInPort {
    public void moveTaskDeadline(
            MoveTaskDeadlineInPort.MoveTaskDeadlineCommand moveTaskDeadlineCommand);

    public class MoveTaskDeadlineCommand {
        public ToDoList list;
        public Task task;
        public LocalDate newDeadline;

        private MoveTaskDeadlineCommand(ToDoList list, Task task,
                                        LocalDate newDeadline) {
            this.list = list;
            this.task = task;
            this.newDeadline = newDeadline;
        }
    }
}
