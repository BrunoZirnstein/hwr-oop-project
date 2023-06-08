package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;

public interface ChangeTaskDescriptionInPort {
    void changeTaskDescription(
            ChangeTaskDescriptionCommand changeTaskDescriptionCommand);

    record ChangeTaskDescriptionCommand(ToDoListId listId, TaskId taskId,
                                        String newDescription) {}
}
