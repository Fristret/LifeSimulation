package org.simulation.intentions;

import org.simulation.logs.ConsoleLogger;
import org.simulation.logs.LogFormatter;
import org.simulation.entities.objects.Grass;
import org.simulation.core.GameMap;

public class EatIntent implements Intent {

    private final Grass grass;

    public EatIntent(Grass grass) {
        this.grass = grass;
    }

    @Override
    public void apply(GameMap gameMap, ConsoleLogger consoleLogger) {
        grass.eaten();
        gameMap.removeEntity(grass);
        consoleLogger.log(LogFormatter.addIntent(this));
    }

    public Grass getGrass() {
        return grass;
    }
}
