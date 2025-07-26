package org.simulation.actions;

import org.simulation.logs.ConsoleLogger;
import org.simulation.core.GameMap;

public interface Action {

    void execute(GameMap gameMap, ConsoleLogger consoleLogger);

}
