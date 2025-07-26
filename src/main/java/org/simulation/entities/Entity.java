package org.simulation.entities;

import org.simulation.core.Coordinates;

public abstract class Entity {

    private Coordinates coordinates;

    public Entity(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

}
