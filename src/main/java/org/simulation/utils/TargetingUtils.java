package org.simulation.utils;

import org.simulation.entities.Entity;
import org.simulation.config.Constants;
import org.simulation.entities.Creature;
import org.simulation.core.Coordinates;
import org.simulation.core.GameMap;

import java.util.List;

public class TargetingUtils {

    public static <T extends Entity> Entity findClosestEntity(GameMap gameMap, Creature creature, Class<T> clazz) {
        List<T> entities = gameMap.getListOf(clazz);
        Entity closestEntity = Constants.NOTHING;
        double closestDistance = gameMap.getGameParameters().mapSize();

        for(Entity entity : entities) {
            if (clazz.isInstance(entity)) {
                double distance = distanceCalculation(entity.getCoordinates(), creature.getCoordinates());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestEntity = clazz.cast(entity);
                }
            }
        }
        return closestEntity;
    }

    private static double distanceCalculation(Coordinates coordinates, Coordinates coordinatesTarget){
        int x = coordinates.x() - coordinatesTarget.x();
        int y = coordinates.y() - coordinatesTarget.y();
        return Math.sqrt(x * x + y * y);
    }
}
