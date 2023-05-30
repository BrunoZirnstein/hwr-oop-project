package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

import java.time.LocalDate;

public interface MoveProjectDeadlineInPort {
    void moveProjectDeadline(
            MoveProjectDeadlineCommand moveProjectDeadlineCommand);

    record MoveProjectDeadlineCommand(ToDoList list, Project project,
                                      LocalDate newDeadline) {}
}
