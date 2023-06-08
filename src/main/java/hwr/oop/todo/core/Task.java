package hwr.oop.todo.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Task {
    private final TaskId id;
    private final List<TaskTag> tags;
    private String title;
    private String description;
    private LocalDate deadline;
    private TaskStatus status;
    private TaskPriority priority;
    private String projectName;

    private Task(String title, List<TaskTag> tags, String description,
                 LocalDate deadline, TaskStatus status, TaskPriority priority,
                 String projectName) {
        this.id = new TaskId();
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.projectName = projectName;
    }

    public TaskId id() {
        return id;
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

    public Optional<String> projectName() {
        return Optional.ofNullable(projectName);
    }

    public void renameTitle(String title) {
        this.title = title;
    }

    public void addTag(TaskTag tag) {
        this.tags.add(tag);
    }

    public void removeTagByObject(TaskTag tag) {
        this.tags.remove(tag);
    }

    public void removeTagByName(String tagname) {
        this.tags.removeIf(tag -> tag.title().equals(tagname));
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void moveDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void updateStatus(TaskStatus status) {
        this.status = status;
    }

    public void changePriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void changeProject(String projectName) {
        this.projectName = projectName;
    }

    public void deleteProject() {
        this.projectName = null;
    }

    public boolean equals(Task o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;
        return title.equals(
                task.title()) && description.equals(
                task.description()) && tags.equals(
                task.tags()) && deadline.equals(
                task.deadline()) && status.equals(
                task.status()) && priority.equals(
                task.priority()) && projectName.equals(task.projectName());
    }

    public int hashCode() {
        return Objects.hash(title, description, tags, deadline, status,
                priority, projectName);
    }

    public static class Builder {
        private final String title;
        private List<TaskTag> tags = new ArrayList<>();
        private String description = null;
        private LocalDate deadline = null;
        private TaskStatus status = TaskStatus.TODO;
        private TaskPriority priority = null;
        private String projectName = null;

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

        public Builder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Builder priority(TaskPriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Task build() {
            return new Task(title, tags, description, deadline, status,
                    priority, projectName);
        }
    }
}
