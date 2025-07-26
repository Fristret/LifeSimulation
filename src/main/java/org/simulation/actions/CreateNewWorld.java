package org.simulation.actions;

import org.simulation.entities.Entity;
import org.simulation.entities.Herbivore;
import org.simulation.entities.Predator;
import org.simulation.logs.ConsoleLogger;
import org.simulation.logs.LogFormatter;
import org.simulation.entities.objects.Grass;
import org.simulation.entities.objects.Rock;
import org.simulation.entities.objects.Tree;
import org.simulation.core.GameMap;

import static org.simulation.utils.EntityFactory.createEntity;

public class CreateNewWorld implements Action {

    @Override
    public void execute(GameMap gameMap, ConsoleLogger consoleLogger) {

        for (int i = 0; i < gameMap.getGameParameters().rockCount(); i++) {
            creatingEntity(gameMap, consoleLogger, Rock.class);
        }
        for (int i = 0; i < gameMap.getGameParameters().treeCount(); i++) {
            creatingEntity(gameMap, consoleLogger, Tree.class);
        }
        for (int i = 0; i < gameMap.getGameParameters().grassCount(); i++) {
            creatingEntity(gameMap, consoleLogger, Grass.class);
        }
        for (int i = 0; i < gameMap.getGameParameters().herbivoreCount(); i++) {
            creatingEntity(gameMap, consoleLogger, Herbivore.class);
        }
        for (int i = 0; i < gameMap.getGameParameters().predatorCount(); i++) {
            creatingEntity(gameMap, consoleLogger, Predator.class);
        }
    }

    private <T extends Entity> void creatingEntity(GameMap gameMap, ConsoleLogger consoleLogger, Class<T> tEntity) {
        Entity entity = createEntity(gameMap, tEntity);
        gameMap.addEntity(entity);
        consoleLogger.log(LogFormatter.addCreateLog(entity));
    }


}