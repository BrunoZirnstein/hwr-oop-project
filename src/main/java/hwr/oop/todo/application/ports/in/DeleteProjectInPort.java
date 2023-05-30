package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface DeleteProjectInPort {
    void deleteProject(DeleteProjectCommand deleteProjectCommand);

    record DeleteProjectCommand(ToDoList list, Project project) {}
}
