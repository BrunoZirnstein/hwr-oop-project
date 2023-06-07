package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.ToDoListId;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskTag;

import java.util.List;

public interface TasksByTagInPort {
    List<Task> tasksByTag(TasksByTagCommand tasksByTagCommand);

    record TasksByTagCommand(ToDoListId listId, TaskTag taskTag) {}
}
