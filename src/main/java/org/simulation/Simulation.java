package org.simulation;

import org.simulation.core.GameMap;
import org.simulation.entities.Entity;
import org.simulation.actions.*;
import org.simulation.logs.ConsoleLogger;
import org.simulation.logs.LogFormatter;
import org.simulation.entities.objects.Grass;
import org.simulation.config.Constants;
import org.simulation.config.GameParameters;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final GameMap gameMap;
    private final ConsoleLogger consoleLogger;
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();
    private volatile boolean isRunning = false;
    private int counter = 0;
    private final Renderer renderer = new Renderer();
    private final Object simulationLock = new Object();


    private final Action move = new MoveAllCreatures();

    public Simulation(GameParameters parameters) {
        gameMap = new GameMap(parameters);
        consoleLogger = new ConsoleLogger();
        Action newWorld = new CreateNewWorld();
        addInitAction(newWorld);
        addTurnAction(move);
    }

    public void addDefaultTurnActions() {
        addTurnAction(move);
    }

    public <T extends Entity> void addCreateAction(Class<T> tClass) {
        turnActions.add(new CreateEntity<>(tClass));
    }

    public void addInitAction(Action action) {
        initActions.add(action);
    }

    public void addTurnAction(Action action) {
        turnActions.add(action);
    }

    public void clearLogs(){
        consoleLogger.clear();
    }

    private void executeActions(List<Action> actions) {
        for (Action action : actions) {
            action.execute(gameMap, consoleLogger);
        }
        actions.clear();
    }

    private void nextTurn() {
        counter++;
        consoleLogger.log(LogFormatter.addCounter(counter));
        executeActions(turnActions);
        renderer.renderMap(gameMap);
        if(gameMap.getListOf(Grass.class).size() < gameMap.getGameParameters().grassCount()) {
            addTurnAction(new CreateEntity<>(Grass.class));
        }
        addDefaultTurnActions();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String printLogs() {
        return consoleLogger.getFormattedLogs();
    }

    public void pauseSimulation() {
        synchronized (simulationLock) {
            isRunning = false;
        }
    }

    public void resumeSimulation() {
        synchronized (simulationLock) {
            isRunning = true;
            simulationLock.notify();
        }
    }

    public void startSimulation() {
        isRunning = true;
        executeActions(initActions);
        renderer.renderMap(gameMap);
        Thread simulation = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    synchronized (simulationLock) {
                        while(!isRunning) {
                            simulationLock.wait();
                        }
                        nextTurn();
                        if (gameMap.isNoHerbivores()) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    Thread.sleep(Constants.SIMULATION_DELAY_MS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            consoleLogger.log(Constants.NO_HERBIVORES);
        });
        simulation.start();
    }

}
