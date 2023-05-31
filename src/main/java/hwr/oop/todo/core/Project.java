package hwr.oop.todo.core;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.UUID;

public class Project {
    private final UUID id;
    private String title;
    private LocalDate deadline;

    private Project(String title, LocalDate deadline) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.deadline = deadline;
    }

    public UUID id() {
        return this.id;
    }
    public String title() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title.isEmpty())  {
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
        private String title;
        private LocalDate deadline;

        public Builder (String title) {
            this.title = title;
        }
        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Project build() {
            return new Project(title,deadline);
        }


    }
}