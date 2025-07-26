package org.simulation.entities.objects;

import org.simulation.entities.Entity;
import org.simulation.core.Coordinates;

public class Grass extends Entity {

    private boolean isEaten = false;

    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void eaten() {
        this.isEaten = true;
    }
}
