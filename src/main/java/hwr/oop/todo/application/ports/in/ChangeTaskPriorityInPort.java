package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskPriority;
import hwr.oop.todo.core.ToDoList;

public interface ChangeTaskPriorityInPort {
    void changeTaskPriority(
            ChangeTaskPriorityCommand changeTaskPriorityCommand);

    record ChangeTaskPriorityCommand(ToDoList list, Task task,
                                     TaskPriority newPriority) {}
}
