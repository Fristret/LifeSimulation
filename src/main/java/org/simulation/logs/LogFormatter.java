package org.simulation.logs;

import org.simulation.entities.Entity;
import org.simulation.intentions.*;
import org.simulation.config.Constants;

public class LogFormatter {

    public static String addCounter(int counter){
        return String.format(Constants.LOG_TURN_COUNTER, counter);
    }

    public static String addCreateLog(Entity entity){
        return String.format(Constants.LOG_ENTITY_CREATED, entity.getClass().getSimpleName(), entity.getCoordinates());
    }

    public static String addIntent(Intent intent) throws IllegalArgumentException {
        return switch (intent) {
            case AttackIntent info -> String.format(Constants.LOG_ATTACK, info.getCreature().getCoordinates().toString(), info.getPredator().getDamage());
            case DeathIntent info -> String.format(Constants.LOG_DEATH, info.getCreature().getClass().getSimpleName(), info.getCreature().getCoordinates().toString());
            case EatIntent info -> String.format(Constants.LOG_EAT, info.getGrass().getCoordinates().toString());
            case MoveIntent info -> String.format(Constants.LOG_MOVE_TO_TARGET, info.getCreature().getClass().getSimpleName(), info.getCreature().getOldCoordinates().toString(), info.getCreature().getCoordinates().toString());
            case WalkIntent info -> String.format(Constants.LOG_WALK, info.getCreature().getClass().getSimpleName(), info.getCreature().getOldCoordinates().toString(), info.getCreature().getCoordinates().toString());
            case TargetIntent<?> info -> String.format(Constants.LOG_TARGET_FOUND, info.getCreature().getClass().getSimpleName(), info.getCreature().getTarget().getClass().getSimpleName(), info.getCreature().getTarget().getCoordinates());
            default -> throw new IllegalStateException(Constants.UNKNOWN_INTENT + intent);
        };
    }

}
