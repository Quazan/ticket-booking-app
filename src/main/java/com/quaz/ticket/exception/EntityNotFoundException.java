package com.quaz.ticket.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> clazz, Long id) {
        super("Entity not found: " + clazz.getSimpleName() + " with id: " + id + ".");
    }

}
