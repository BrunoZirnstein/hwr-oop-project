package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.TaskId;
import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.TaskPriority;

public interface ChangeTaskPriorityInPort {
    void changeTaskPriority(
            ChangeTaskPriorityCommand changeTaskPriorityCommand);

    record ChangeTaskPriorityCommand(ToDoListId listId, TaskId taskId,
                                     TaskPriority newPriority) {}
}
