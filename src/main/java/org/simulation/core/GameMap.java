package org.simulation.core;

import org.simulation.entities.Entity;
import org.simulation.config.GameParameters;
import org.simulation.entities.Creature;
import org.simulation.entities.Herbivore;
import org.simulation.config.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameMap {
    private final HashMap<Coordinates, Entity> map;
    private boolean noHerbivores = false;
    private final GameParameters gameParameters;

    public GameMap(GameParameters gameParameters) {
        map = new HashMap<>();
        this.gameParameters = gameParameters;
    }

    public void addEntity(Entity entity){
        map.put(entity.getCoordinates(), entity);
    }

    public List<Entity> getAllEntities() {
        return map.values().stream().toList();
    }

    public Entity getEntityAt(Coordinates coordinates) {
        return map.getOrDefault(coordinates, Constants.NOTHING);
    }

    public GameParameters getGameParameters() {
        return gameParameters;
    }

    public <T extends Entity> List<T> getListOf(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for(Entity values : map.values()) {
            if (clazz.isInstance(values)) {
                list.add(clazz.cast(values));
            }
        }
        return list;
    }

    public void checkHerbivores() {
        noHerbivores = getListOf(Herbivore.class).isEmpty();
    }

    public boolean isNoHerbivores() {
        return noHerbivores;
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return getEntityAt(coordinates) == Constants.NOTHING;
    }

    public boolean isInBounds(Coordinates coordinates) {
        return coordinates.x() >= 0 && coordinates.y() >= 0 && coordinates.x() < gameParameters.mapSize() && coordinates.y() < gameParameters.mapSize();
    }

    public void moveCreature(Creature creature) {
        removeEntity(creature.getOldCoordinates(), creature);
        addEntity(creature);
    }

    public void removeEntity(Coordinates coordinates, Entity entity){
        map.remove(coordinates, entity);
    }

    public void removeEntity(Entity entity){
        map.remove(entity.getCoordinates(), entity);
    }
}
