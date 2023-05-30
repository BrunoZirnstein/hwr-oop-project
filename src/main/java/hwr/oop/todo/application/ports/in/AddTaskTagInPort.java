package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskTag;
import hwr.oop.todo.core.ToDoList;

public interface AddTaskTagInPort {
    void addTaskTag(AddTaskTagCommand addTaskTagCommand);

    record AddTaskTagCommand(ToDoList list, Task task, TaskTag newTaskTag) {}
}
