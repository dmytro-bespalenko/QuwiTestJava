package com.example.quwitestjava.helper;

public class Event<T> {

    private final T value;

    public Boolean handled = false;

    public Event(T value) {
        this.value = value;
    }

    public T getValue() {
        if (handled) return null;
        handled = true;

        return value;
    }
}
