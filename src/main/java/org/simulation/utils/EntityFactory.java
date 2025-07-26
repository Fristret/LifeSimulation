package org.simulation.utils;

import org.simulation.entities.Entity;
import org.simulation.config.Constants;
import org.simulation.entities.Herbivore;
import org.simulation.entities.Predator;
import org.simulation.errors.MapIsFullException;
import org.simulation.entities.objects.Grass;
import org.simulation.entities.objects.Rock;
import org.simulation.entities.objects.Tree;
import org.simulation.core.Coordinates;
import org.simulation.core.GameMap;

import java.util.concurrent.ThreadLocalRandom;

public class EntityFactory {

    public static <T extends Entity> Entity createEntity(GameMap gameMap, Class<T> clazz) throws MapIsFullException, IllegalArgumentException {
        Coordinates coordinates = findEmptyCoordinates(gameMap);

        if (Herbivore.class.isAssignableFrom(clazz)) {
            return new Herbivore(coordinates, gameMap.getGameParameters().herbHp(), gameMap.getGameParameters().herbSpeed());
        } else if (Grass.class.isAssignableFrom(clazz)) {
            return new Grass(coordinates);
        } else if (Predator.class.isAssignableFrom(clazz)) {
            return new Predator(coordinates, Constants.PREDATOR_HP, gameMap.getGameParameters().predatorSpeed(), gameMap.getGameParameters().predatorDamage());
        } else if (Rock.class.isAssignableFrom(clazz)) {
            return new Rock(coordinates);
        } else if (Tree.class.isAssignableFrom(clazz)) {
            return new Tree(coordinates);
        } else {
            throw new IllegalArgumentException(Constants.UNKNOWN_CREATURE_CLASS + clazz.getName());
        }

    }

    private static Coordinates findEmptyCoordinates(GameMap gameMap) throws MapIsFullException {
        int mapSize = gameMap.getGameParameters().mapSize();
        int mapAttempt = mapSize * mapSize;
        for (int i = 0; i < mapAttempt;  i++) {
            Coordinates coordinates = new Coordinates(randomPos(gameMap), randomPos(gameMap));
            if (gameMap.getEntityAt(coordinates) == Constants.NOTHING) {
                return coordinates;
            }
        }
        throw new MapIsFullException(Constants.NO_PLACE_ON_MAP);
    }

    public static int randomPos(GameMap gameMap){
        return ThreadLocalRandom.current().nextInt(0, gameMap.getGameParameters().mapSize());
    }

}
