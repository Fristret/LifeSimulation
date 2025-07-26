package org.simulation.config;

import org.simulation.entities.Entity;
import org.simulation.entities.objects.Nothing;

public class Constants {

    //WorldSettings
    public static final int MAP_SIZE_MAX = 50;
    public static final int SPEED_MAX = 50;
    public static final int HP_MAX = 100;
    public static final int DMG_MAX = 100;
    public static final int MAX_COUNT_DELIMETER = 6;
    public static final String MAP_SIZE = "Введите размер карты до " + MAP_SIZE_MAX + ": ";
    public static final String TREE_COUNT = "Введите количество деревьев: ";
    public static final String ROCK_COUNT = "Введите количество камней: ";
    public static final String GRASS_COUNT = "Введите количество травы: ";
    public static final String HERBIVORE_COUNT = "Введите количество травоядных: ";
    public static final String PREDATOR_COUNT = "Введите количество хищников: ";
    public static final String HP = "Введите здоровье травоядных до " + HP_MAX + ": ";
    public static final String HERBIVORE_SPEED = "Введите скорость травоядных до " + SPEED_MAX + ": ";
    public static final String PREDATOR_SPEED = "Введите скорость хищников до " + SPEED_MAX + ": ";
    public static final String DMG = "Введите силу атаки хищников до " + DMG_MAX + ": ";

    //Errors
    public static final String CORRECT_VALUE = "Введите корректное целое число.";
    public static final String NO_PLACE_ON_MAP = "Нет места на карте.";
    public static final String UNKNOWN_CREATURE_CLASS = "Неизвестный класс существа: ";
    public static final String NO_CREATURES_ON_MAP = "Нет существ на карте.";
    public static final String UNKNOWN_INTENT = "Неизвестнное намерение: ";

    //Messages
    public static final String NO_HERBIVORES = "Все существа мертвы. Симуляция закончена";
    public static final String RIGHT_MAP_SIZE = "Введите корректный размер карты: <= " + Constants.MAP_SIZE_MAX;
    public static final String RIGHT_COUNT = "Введите корректное количество: <= ";
    public static final String RIGHT_HP = "Введите корректное здоровье: <= " + Constants.HP_MAX;
    public static final String RIGHT_SPEED = "Введите корректную скорость: <= " + Constants.SPEED_MAX;
    public static final String RIGHT_DAMAGE = "Введите корректный урон: <= " + Constants.DMG_MAX;
    public static final String VALUE_MUST_BE_POSITIVE = "Число должно быть неотрицательным.";

    //Logs
    public static final String LOG_TURN_COUNTER = "Ход номер: %d";
    public static final String LOG_ENTITY_CREATED = "%s создан(а) на координатах %s";
    public static final String LOG_ATTACK = "Хищник атаковал %s, нанеся %d урона";
    public static final String LOG_DEATH = "%s на %s умерл";
    public static final String LOG_EAT = "Травоядное съело траву на %s";
    public static final String LOG_MOVE_TO_TARGET = "%s движется к цели с %s на %s";
    public static final String LOG_WALK = "%s бесцельно прогуливается с %s на %s";
    public static final String LOG_TARGET_FOUND = "%s нашел(а) цель (%s) на %s";


    //HungerSettings
    public static final int HUNGER_DEC = 100;
    public static final boolean HUNGER_DEC_FULL = true;
    public static final int HUNGER_INC = 5;
    public static final int HUNGER_INIT = 0;
    public static final int HUNGER_STG = 50;
    public static final int HUNGER_MAX = 100;

    //NoTarget
    public static final Entity NOTHING = new Nothing(-1, -1);

    //Renderer
    public static final String HERBIVORE = "\uD83D\uDC2E";
    public static final String GRASS = "\uD83C\uDF3F";
    public static final String PREDATOR = "\uD83D\uDC3A";
    public static final String ROCK = "\uD83E\uDEA8";
    public static final String TREE = "\uD83C\uDF33";
    public static final String DEFAULT = "⬛";

    //PredatorSettings
    public static final int PREDATOR_HP = 1;

    //SimulationSettings
    public static final int SIMULATION_DELAY_MS = 1000;

}
