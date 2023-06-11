package hwr.oop.todo.core;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Objects;

public class Project {
    private final ProjectId id;
    private String title;
    private LocalDate deadline;

    private Project(ProjectId id, String title, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(title, project.title) && Objects.equals(deadline, project.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, deadline);
    }

    public ProjectId id() {
        return id;
    }

    public String title() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) {
            throw new InvalidParameterException();
        }
        this.title = title;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate deadline() {
        return deadline;
    }

    public static class Builder {
        private final String title;
        private ProjectId id = new ProjectId();
        private LocalDate deadline;

        public Builder(String title) {
            this.title = title;
        }

        public Builder id(ProjectId id) {
            this.id = id;
            return this;
        }

        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Project build() {
            return new Project(id, title, deadline);
        }


    }
}