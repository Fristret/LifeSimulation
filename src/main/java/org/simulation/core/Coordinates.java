package org.simulation.core;

import static java.lang.Math.abs;

public record Coordinates(int x, int y) {

    public Coordinates compare(Coordinates coordinates) {
        return new Coordinates(abs(x - coordinates.x()), abs(y - coordinates.y()));
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
