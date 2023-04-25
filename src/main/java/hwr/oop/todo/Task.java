package hwr.oop.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Task {
    private String title;
    private List<TaskTag> tags;
    private String description;
    private LocalDate deadline;
    private TaskStatus status;
    private TaskPriority priority;
    private Project project;

    private Task(String title, List<TaskTag> tags, String description, LocalDate deadline, TaskStatus status, TaskPriority priority, Project project) {
        this.title = title;
        this.tags = tags;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.project = project;
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

    public Optional<Project> project() {
        return Optional.ofNullable(project);
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

    public void changeProject(Project project) {
        this.project = project;
    }

    public void deleteProject() {
        this.project = null;
    }

    public static class Builder {
        private String title;
        private List<TaskTag> tags = new ArrayList<>();
        private String description = null;
        private LocalDate deadline = null;
        private TaskStatus status = TaskStatus.TODO;
        private TaskPriority priority = null;
        private Project project = null;

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
            return new Task(title, tags, description, deadline, status, priority, project);
        }
    }
}
