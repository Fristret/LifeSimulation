package org.simulation.intentions;

import org.simulation.logs.ConsoleLogger;
import org.simulation.core.GameMap;

public interface Intent {

    void apply(GameMap gameMap, ConsoleLogger consoleLogger);

}
