package org.simulation.utils;

import org.simulation.entities.Entity;
import org.simulation.config.Constants;
import org.simulation.entities.Creature;
import org.simulation.core.Coordinates;
import org.simulation.core.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PathFinderUtils {

    public static List<Coordinates> walk(GameMap gameMap, Creature creature) {
        List<Coordinates> path = new ArrayList<>();
        int currentX = creature.getCoordinates().x();
        int currentY = creature.getCoordinates().y();
        for(int i = 0; i < creature.getSpeed(); i++) {
            int x = ThreadLocalRandom.current().nextInt(-1, 2);
            int y = ThreadLocalRandom.current().nextInt(-1, 2);
            int nextX = currentX + x;
            int nextY = currentY + y;
            Coordinates coordinates = new Coordinates(nextX, nextY);

            if (gameMap.isInBounds(coordinates) && gameMap.isCellEmpty(coordinates)) {
                path.add(coordinates);
                currentX = nextX;
                currentY = nextY;
            }
        }
        return path;
    }

    public static List<Coordinates> pathFinderA(GameMap gameMap, Creature current){
        List<Cage> openList = new ArrayList<>();
        List<Coordinates> closedList = new ArrayList<>();
        Coordinates end = current.getTarget().getCoordinates();
        int step = current.getSpeed();
        while(step != 0) {
            step--;
            getCloseCages(gameMap, current, openList, end);
            if (openList.isEmpty()) break;
            else closedList.add(filterCages(openList));
            openList.clear();
        }

        return closedList;
    }

    private static void getCloseCages(GameMap gameMap, Creature current, List<Cage> list, Coordinates targetCoordinates) {
        int startX = current.getCoordinates().x();
        int startY = current.getCoordinates().y();

        start:
        for(int i = -1; i < 2; i++) {
            for(int k = -1; k < 2; k++) {
                if (i == 0 && k == 0) continue;
                int x = startX + i;
                int y = startY + k;
                Coordinates newCoordinates = new Coordinates(x, y);
                if(gameMap.isInBounds(newCoordinates)) {
                    Entity entity = gameMap.getEntityAt(newCoordinates);
                    if (entity == current.getTarget()) {
                        list.clear();
                        break start;
                    }
                    if (entity == Constants.NOTHING) {
                        int G = (i == 0 || k == 0) ? 10 : 14;
                        list.add(cageCalculation(G, newCoordinates, targetCoordinates));
                    }
                }
            }
        }
    }

    private static Cage cageCalculation(int G, Coordinates newCoordinates, Coordinates targetCoordinates) {
        int H = (int) (Math.sqrt(Math.pow(targetCoordinates.x() - newCoordinates.x(), 2) + Math.pow(targetCoordinates.y() - newCoordinates.y(), 2)) * 10)  ;
        int F = G + H;
        return new Cage(newCoordinates, F);
    }

    private static Coordinates filterCages(List<Cage> list) {
        Cage bestCage = list.getFirst();
        for(Cage cage : list) {
            if (cage.costF() < bestCage.costF()) {
                bestCage = cage;
            }
        }
        return bestCage.coordinates();
    }
}
