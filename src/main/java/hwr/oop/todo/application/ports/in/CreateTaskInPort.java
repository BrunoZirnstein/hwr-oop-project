package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.*;

import java.time.LocalDate;
import java.util.List;

public interface CreateTaskInPort {
    Task createTask(CreateTaskCommand createTaskCommand);

    record CreateTaskCommand(ToDoListId listId, String title,
                             String description, List<TaskTag> tags,
                             LocalDate deadline, String projectName,
                             TaskPriority priority) {}
}
