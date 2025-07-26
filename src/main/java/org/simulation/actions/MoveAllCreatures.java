package org.simulation.actions;

import org.simulation.entities.Creature;
import org.simulation.entities.Herbivore;
import org.simulation.entities.Predator;
import org.simulation.intentions.Intent;
import org.simulation.logs.ConsoleLogger;
import org.simulation.config.Constants;
import org.simulation.core.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MoveAllCreatures implements Action {

    @Override
    public void execute(GameMap gameMap, ConsoleLogger consoleLogger) {
        List<Creature> list = new ArrayList<>(Stream.concat(gameMap.getListOf(Predator.class).stream(), gameMap.getListOf(Herbivore.class).stream()).toList());
        try {
            list.forEach(creature -> {
                if (creature.isAlive()) creature.makeMove();
                else creature.death();
                for (Intent intent : creature.getIntents()) {
                    intent.apply(gameMap, consoleLogger);
                }
                creature.clearIntents();
            });
        }
        catch (NullPointerException e) {
            consoleLogger.log(Constants.NO_CREATURES_ON_MAP);
        }
    }
}
