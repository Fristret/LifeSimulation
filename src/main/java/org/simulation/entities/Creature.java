package org.simulation.entities;

import org.simulation.intentions.DeathIntent;
import org.simulation.intentions.Intent;
import org.simulation.config.Constants;
import org.simulation.core.Coordinates;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature extends Entity {

    private int hp;
    private final int speed;
    private boolean alive = true;
    private Coordinates oldCoordinates;
    private Entity target = Constants.NOTHING;
    private final List<Intent> intents;

    public Creature(Coordinates coordinates, int hp, int speed) {
        super(coordinates);
        this.hp = hp;
        this.speed = speed;
        this.intents = new ArrayList<>();
    }

    public abstract void go();
    public abstract void makeMove();

    public void addIntent(Intent intent) {
        intents.add(intent);
    }

    public void clearIntents() {
        intents.clear();
    }

    public void death() {
        kill();
        addIntent(new DeathIntent(getCoordinates(),this));
    }

    public Entity getTarget() {
        return target;
    }

    public int getHp() {
        return hp;
    }

    public List<Intent> getIntents() {
        return intents;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public Coordinates getOldCoordinates() {
        return oldCoordinates;
    }

    public boolean isNearTarget() {
        Coordinates diff = getCoordinates().compare(target.getCoordinates());
        return diff.x() <= 1 && diff.y() <= 1;
    }

    public boolean isTargetFound() {
        return target != Constants.NOTHING;
    }

    public void setOldCoordinates(Coordinates oldCoordinates) {
        this.oldCoordinates = oldCoordinates;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

}