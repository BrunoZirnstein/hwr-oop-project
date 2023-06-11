package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;

import java.time.LocalDate;

public interface MoveTaskDeadlineInPort {
    void moveTaskDeadline(MoveTaskDeadlineCommand moveTaskDeadlineCommand);

    record MoveTaskDeadlineCommand(ToDoListId listId, TaskId taskId,
                                   LocalDate newDeadline) {}
}
