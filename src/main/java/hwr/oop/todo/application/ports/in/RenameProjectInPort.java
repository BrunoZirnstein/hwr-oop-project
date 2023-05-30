package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface RenameProjectInPort {
    void renameProject(RenameProjectCommand renameProjectCommand);

    record RenameProjectCommand(ToDoList list, Project project,
                                String newName) {}
}
