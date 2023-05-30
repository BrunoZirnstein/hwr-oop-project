package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface ProjectByNameInPort {
    Project projectByName(ProjectByNameCommand projectByNameCommand);

    record ProjectByNameCommand(ToDoList list, String projectName) {}
}
