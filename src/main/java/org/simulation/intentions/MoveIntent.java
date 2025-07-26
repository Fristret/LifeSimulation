package org.simulation.intentions;

import org.simulation.entities.Creature;
import org.simulation.logs.ConsoleLogger;
import org.simulation.logs.LogFormatter;
import org.simulation.utils.PathFinderUtils;
import org.simulation.core.Coordinates;
import org.simulation.core.GameMap;

import java.util.List;

public class MoveIntent implements Intent {

    private final Creature creature;

    public MoveIntent(Creature creature) {
        this.creature = creature;
    }

    @Override
    public void apply(GameMap gameMap, ConsoleLogger consoleLogger) {
        List<Coordinates> path = PathFinderUtils.pathFinderA(gameMap, creature);
        if (!path.isEmpty()) {
            creature.setCoordinates(path.getLast());
            gameMap.moveCreature(creature);
        }
        consoleLogger.log(LogFormatter.addIntent(this));
    }

    public Creature getCreature() {
        return creature;
    }
}