package org.simulation.intentions;

import org.simulation.entities.Creature;
import org.simulation.entities.Predator;
import org.simulation.logs.ConsoleLogger;
import org.simulation.logs.LogFormatter;
import org.simulation.core.GameMap;

public class AttackIntent implements Intent{

    private final Predator predator;
    private final Creature creature;

    public AttackIntent(Predator predator) {
        this.predator = predator;
        creature = (Creature) predator.getTarget();
    }

    @Override
    public void apply(GameMap gameMap, ConsoleLogger consoleLogger) {
        creature.setHp(creature.getHp() - predator.getDamage());
        if (creature.getHp() <= 0) {
            creature.kill();
        }
        consoleLogger.log(LogFormatter.addIntent(this));

    }

    public Creature getCreature() {
        return creature;
    }

    public Predator getPredator() {
        return predator;
    }
}
