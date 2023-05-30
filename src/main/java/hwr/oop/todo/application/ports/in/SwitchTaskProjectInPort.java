package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface SwitchTaskProjectInPort {
    void switchTaskProject(SwitchTaskProjectCommand switchTaskProjectCommand);

    record SwitchTaskProjectCommand(ToDoList list, Task tasks,
                                    Project newProject) {}
}
