package org.simulation.utils;

import org.simulation.core.Coordinates;

public record Cage(Coordinates coordinates, int costF) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cage that = (Cage) o;
        return costF == that.costF;
    }

}

