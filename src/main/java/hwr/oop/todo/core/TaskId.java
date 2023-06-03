package hwr.oop.todo.core;

import java.util.UUID;

public record TaskId(UUID id) {
    public TaskId() {
        this(UUID.randomUUID());
    }
}
