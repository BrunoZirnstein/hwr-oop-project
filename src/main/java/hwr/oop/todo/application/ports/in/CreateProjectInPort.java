package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ToDoListId;

import java.time.LocalDate;

public interface CreateProjectInPort {
    void createProject(CreateProjectCommand createProjectCommand);

    record CreateProjectCommand(ToDoListId listId, String projectOwner,
                                LocalDate projectDeadline) {}
}
