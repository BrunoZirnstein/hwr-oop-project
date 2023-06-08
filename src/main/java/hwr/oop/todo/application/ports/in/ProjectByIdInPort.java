package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ProjectId;
import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Project;

public interface ProjectByIdInPort {
    Project projectById(ProjectByIdCommand projectByIdCommand);

    record ProjectByIdCommand(ToDoListId listId, ProjectId projectId) {}
}
