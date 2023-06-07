package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Project;

public interface ProjectByNameInPort {
    Project projectByName(ProjectByNameCommand projectByNameCommand);

    record ProjectByNameCommand(ToDoListId listId, String projectName) {}
}
