package org.simulation.entities;

import org.simulation.intentions.*;
import org.simulation.entities.objects.Grass;
import org.simulation.config.Constants;
import org.simulation.core.Coordinates;

public class Herbivore extends Creature{

    private int hunger;
    private boolean isHungry = false;

    public Herbivore(Coordinates coordinates, int hp, int speed) {
        super(coordinates, hp, speed);
        hunger = Constants.HUNGER_INIT;
    }

    @Override
    public void go() {
        addIntent(new MoveIntent(this));
        eat();
    }

    public void isHungry(){
        isHungry = hunger >= Constants.HUNGER_STG;
    }

    @Override
    public void makeMove() {
        setOldCoordinates(getCoordinates());
        isHungry();

        boolean hasValidTarget = isTargetFound() && !((Grass) getTarget()).isEaten();

        if (isHungry) {
            if (hasValidTarget) {
                go();
            } else {
                addIntent(new TargetIntent<>(this, Grass.class));
                walk();
            }
        }
        else {
            walk();
        }
        hunger += Constants.HUNGER_INC;
        if (hunger >= Constants.HUNGER_MAX) {
            death();
        }
    }

    public void eat() {
        if (isNearTarget()) {
            addIntent(new EatIntent((Grass) getTarget()));
            setTarget(Constants.NOTHING);

            if (Constants.HUNGER_DEC_FULL) {
                hunger = 0;
            } else hunger -= Constants.HUNGER_DEC;
        }
    }

    public void walk() {
        addIntent(new WalkIntent(this));
    }

}
