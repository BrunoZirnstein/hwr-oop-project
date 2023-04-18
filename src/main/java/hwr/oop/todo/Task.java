package hwr.oop.todo;

import java.time.LocalDate;
import java.util.ArrayList;

public record Task(String title, ArrayList<TaskTag> tags, Project project, String description, LocalDate deadline, TaskStatus status,
                   TaskPriority priority) {
}
