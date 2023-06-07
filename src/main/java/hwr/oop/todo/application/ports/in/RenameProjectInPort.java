package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ProjectId;
import hwr.oop.todo.core.ToDoListId;

public interface RenameProjectInPort {
    void renameProject(RenameProjectCommand renameProjectCommand);

    record RenameProjectCommand(ToDoListId listId, ProjectId projectId,
                                String newName) {}
}
