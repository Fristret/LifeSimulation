package org.simulation.intentions;

import org.simulation.entities.Entity;
import org.simulation.entities.Creature;
import org.simulation.entities.Herbivore;
import org.simulation.logs.ConsoleLogger;
import org.simulation.logs.LogFormatter;
import org.simulation.config.Constants;
import org.simulation.utils.TargetingUtils;
import org.simulation.core.GameMap;

public class TargetIntent<T extends Entity> implements Intent {

    private final Creature creature;
    private final Class<T> targerClass;

    public TargetIntent(Creature creature, Class<T> targetClass) {
        this.creature = creature;
        this.targerClass = targetClass;
    }

    @Override
    public void apply(GameMap gameMap, ConsoleLogger consoleLogger) {
        Entity entity = TargetingUtils.findClosestEntity(gameMap, creature, targerClass);
        if (targerClass.isAssignableFrom(Herbivore.class) && entity == Constants.NOTHING) {
            gameMap.checkHerbivores();
        }
        creature.setTarget(entity);
        consoleLogger.log(LogFormatter.addIntent(this));
    }

    public Creature getCreature() {
        return creature;
    }

}
