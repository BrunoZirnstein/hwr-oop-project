package hwr.oop.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private final String title;
    private final List<TaskTag> tags;
    private final String description;
    private final LocalDate deadline;
    private final TaskStatus status;
    private final TaskPriority priority;
    public Project project;

    private Task(Builder builder) {
        this.title = builder.title;
        this.tags = builder.tags;
        this.description = builder.description;
        this.deadline = builder.deadline;
        this.status = builder.status;
        this.priority = builder.priority;
        this.project = builder.project;
    }

    public String title() {
        return title;
    }

    public List<TaskTag> tags() {
        return tags;
    }

    public String description() {
        return description;
    }

    public LocalDate deadline() {
        return deadline;
    }

    public TaskStatus status() {
        return status;
    }

    public TaskPriority priority() {
        return priority;
    }

    public Project project() {
        return project;
    }

    public static class Builder {
        private final String title;
        private List<TaskTag> tags = new ArrayList<>();
        private String description = null;
        private LocalDate deadline = null;
        private TaskStatus status = TaskStatus.TODO;
        private TaskPriority priority = null;
        private Project project = new Project("untitled", null);

        public Builder(String title) {
            this.title = title;
        }

        public Builder tags(List<TaskTag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder priority(TaskPriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder project(Project project) {
            this.project = project;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
