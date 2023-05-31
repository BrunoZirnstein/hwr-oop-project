package hwr.oop.todo.core;

import java.util.UUID;

public record ProjectId(UUID id) {
    public ProjectId() {
        this(UUID.randomUUID());
    }
}
