package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Project;

public interface CreateProjectInPort {
    void createProject(CreateProjectCommand createProjectCommand);

    record CreateProjectCommand(ToDoListId listId, Project newProject) {}
}
