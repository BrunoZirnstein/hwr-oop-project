package hwr.oop.todo.core;

import java.util.UUID;

public record ToDoListId(UUID id) {
    public ToDoListId() {
        this(UUID.randomUUID());
    }
}
