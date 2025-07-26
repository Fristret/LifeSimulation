package org.simulation.actions;

import org.simulation.entities.Entity;
import org.simulation.errors.MapIsFullException;
import org.simulation.logs.ConsoleLogger;
import org.simulation.logs.LogFormatter;
import org.simulation.utils.EntityFactory;
import org.simulation.core.GameMap;

public class CreateEntity<T extends Entity> implements Action{

    private final Class<T> tClass;

    public CreateEntity(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public void execute(GameMap gameMap, ConsoleLogger consoleLogger) {
        try {
            Entity entity = EntityFactory.createEntity(gameMap, tClass);
            gameMap.addEntity(entity);
            consoleLogger.log(LogFormatter.addCreateLog(entity));
        } catch (MapIsFullException | IllegalAccessError e){
            consoleLogger.log(e.getMessage());
        }
    }

}
