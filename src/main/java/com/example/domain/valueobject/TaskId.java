package com.example.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public final class TaskId {

    private final String value;

    private TaskId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("TaskId cannot be blank");
        }
        this.value = value;
    }

    public static TaskId generate() {
        return new TaskId(UUID.randomUUID().toString());
    }

    public static TaskId of(String value) {
        return new TaskId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskId)) return false;
        return value.equals(((TaskId) o).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
