package hwr.oop.todo.core;

import java.util.Objects;

public class TaskTag {
    private final String title;

    public TaskTag(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTag taskTag = (TaskTag) o;
        return title == taskTag.title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
