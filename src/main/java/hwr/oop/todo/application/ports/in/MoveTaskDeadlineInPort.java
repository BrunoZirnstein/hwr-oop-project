package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

import java.time.LocalDate;

public interface MoveTaskDeadlineInPort {
    void moveTaskDeadline(MoveTaskDeadlineCommand moveTaskDeadlineCommand);

    record MoveTaskDeadlineCommand(ToDoList list, Task task,
                                   LocalDate newDeadline) {}
}
