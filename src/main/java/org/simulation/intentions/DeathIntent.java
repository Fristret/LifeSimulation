package org.simulation.intentions;

import org.simulation.entities.Creature;
import org.simulation.logs.ConsoleLogger;
import org.simulation.logs.LogFormatter;
import org.simulation.core.Coordinates;
import org.simulation.core.GameMap;

public class DeathIntent implements Intent{

    private final Creature creature;
    private final Coordinates coordinates;

    public DeathIntent(Coordinates coordinates, Creature creature) {
        this.creature = creature;
        this.coordinates = coordinates;
    }

    @Override
    public void apply(GameMap gameMap, ConsoleLogger consoleLogger) {
        gameMap.removeEntity(coordinates, creature);
        consoleLogger.log(LogFormatter.addIntent(this));
    }

    public Creature getCreature() {
        return creature;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
