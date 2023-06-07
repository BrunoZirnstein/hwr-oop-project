package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ProjectId;
import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;

public interface SwitchTaskProjectInPort {
    void switchTaskProject(SwitchTaskProjectCommand switchTaskProjectCommand);

    record SwitchTaskProjectCommand(ToDoListId listId, TaskId taskId,
                                    ProjectId newProjectId) {}
}
