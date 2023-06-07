package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ProjectId;
import hwr.oop.todo.core.ToDoListId;

public interface DeleteProjectInPort {
    void deleteProject(DeleteProjectCommand deleteProjectCommand);

    record DeleteProjectCommand(ToDoListId listId, ProjectId projectId) {}
}
