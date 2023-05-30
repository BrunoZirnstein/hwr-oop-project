package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskTag;
import hwr.oop.todo.core.ToDoList;

public interface RemoveTaskTagInPort {
    void removeTaskTag(RemoveTaskTagCommand removeTaskTagCommand);

    record RemoveTaskTagCommand(ToDoList list, Task task, TaskTag taskTag) {}
}
