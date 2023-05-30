package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface ChangeTaskDescriptionInPort {
    void changeTaskDescription(
            ChangeTaskDescriptionCommand changeTaskDescriptionCommand);

    record ChangeTaskDescriptionCommand(ToDoList list, Task task,
                                        String newDescription) {}
}
