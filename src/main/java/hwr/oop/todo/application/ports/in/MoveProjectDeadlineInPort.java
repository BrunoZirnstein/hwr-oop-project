package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ProjectId;
import hwr.oop.todo.core.ToDoListId;

import java.time.LocalDate;

public interface MoveProjectDeadlineInPort {
    void moveProjectDeadline(
            MoveProjectDeadlineCommand moveProjectDeadlineCommand);

    record MoveProjectDeadlineCommand(ToDoListId listId, ProjectId projectId,
                                      LocalDate newDeadline) {}
}
