package hwr.oop.todo.core;

import java.time.LocalDate;

public class Project {
    private final String title;
    private LocalDate deadline;

    public Project(String title, LocalDate deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    public String title() {
        return this.title;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate deadline() {
        return deadline;
    }
}