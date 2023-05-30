package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

import java.time.LocalDate;

public interface MoveTaskDeadlineInPort {
    public void moveTaskDeadlineInPort(
            MoveTaskDeadlineInPort.MoveTaskDeadlineCommand moveTaskDeadlineCommand);

    public class MoveTaskDeadlineCommand {
        private ToDoList list;
        private Task task;
        private LocalDate newDeadline;

        private MoveTaskDeadlineCommand(ToDoList list, Task task,
                                        LocalDate newDeadline) {
            this.list = list;
            this.task = task;
            this.newDeadline = newDeadline;
        }
    }
}
