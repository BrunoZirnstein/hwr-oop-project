package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface CreateProjectInPort {
    void createProject(CreateProjectCommand createProjectCommand);

    record CreateProjectCommand(ToDoList list, Project newProject) {}
}
