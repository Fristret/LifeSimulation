package org.simulation;

import org.simulation.entities.Entity;
import org.simulation.entities.Herbivore;
import org.simulation.entities.Predator;
import org.simulation.entities.objects.Grass;
import org.simulation.entities.objects.Rock;
import org.simulation.entities.objects.Tree;
import org.simulation.config.Constants;
import org.simulation.core.Coordinates;
import org.simulation.core.GameMap;

public class Renderer {

    public void renderMap(GameMap gameMap) {

        int size = gameMap.getGameParameters().mapSize();

        String[][] display = new String[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                display[y][x] = Constants.DEFAULT;
            }
        }

        for (Entity entity : gameMap.getAllEntities()) {
            Coordinates coords = entity.getCoordinates();
            String symbol = getEntitySymbol(entity);
            display[coords.x()][coords.y()] = symbol;
        }

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(display[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private String getEntitySymbol(Entity entity) {
        return switch (entity) {
            case Grass _ -> Constants.GRASS;
            case Tree _ -> Constants.TREE;
            case Rock _ -> Constants.ROCK;
            case Herbivore _ -> Constants.HERBIVORE;
            case Predator _ -> Constants.PREDATOR;
            default -> Constants.DEFAULT;
        };
    }
}
