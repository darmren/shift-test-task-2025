package org.example;

public enum Type {
    STRING,
    FLOAT,
    INTEGER;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
