package org.simulation.entities;

import org.simulation.intentions.AttackIntent;
import org.simulation.intentions.MoveIntent;
import org.simulation.intentions.TargetIntent;
import org.simulation.core.Coordinates;


public class Predator extends Creature {
    private final int damage;

    public Predator(Coordinates coordinates, int hp, int speed, int damage) {
        super(coordinates, hp, speed);
        this.damage = damage;
    }

    @Override
    public void go() {
        addIntent(new MoveIntent(this));
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void makeMove() {
        setOldCoordinates(getCoordinates());
        boolean hasValidTarget = isTargetFound() && ((Creature) getTarget()).isAlive();

        if (hasValidTarget) {
            go();
            if (isNearTarget()) {
                attack();
            }
        }
        else {
            addIntent(new TargetIntent<>(this, Herbivore.class));
        }
    }

    public void attack() {
        addIntent(new AttackIntent(this));
    }

}
