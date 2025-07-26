package org.simulation.errors;

public class MapIsFullException extends RuntimeException {
    public MapIsFullException(String message) {
        super(message);
    }
}
