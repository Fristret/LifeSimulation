package org.simulation.utils;

import org.simulation.config.Constants;
import org.simulation.config.GameParameters;

import java.util.Scanner;

public class InputHandler {

    public static GameParameters fromConsole() {
        Scanner scanner = new Scanner(System.in);

        int mapSize = readMapSize(scanner);
        int treeCount = readCount(mapSize, scanner, Constants.TREE_COUNT);
        int rockCount = readCount(mapSize, scanner, Constants.ROCK_COUNT);
        int grassCount = readCount(mapSize, scanner, Constants.GRASS_COUNT);
        int herbCount = readCount(mapSize, scanner, Constants.HERBIVORE_COUNT);
        int herbSpeed = readSpeed(scanner, Constants.HERBIVORE_SPEED);
        int herbHp = readHp(scanner);
        int predatorCount = readCount(mapSize, scanner, Constants.PREDATOR_COUNT);
        int predatorSpeed = readSpeed(scanner, Constants.PREDATOR_SPEED);
        int predatorAttack = readDamage(scanner);

        return new GameParameters(treeCount, rockCount, grassCount, herbCount,
                herbSpeed, herbHp, predatorCount, predatorSpeed, predatorAttack, mapSize);
    }

    private static int readMapSize(Scanner scanner) {
        while(true) {
            int mapSize = readInt(scanner, Constants.MAP_SIZE);
            if (mapSize >= Constants.MAX_COUNT_DELIMETER && mapSize <= Constants.MAP_SIZE_MAX) {
                return mapSize;
            }
            else {
                System.out.println(Constants.RIGHT_MAP_SIZE);
            }
        }
    }

    private static int readCount(int mapSize, Scanner scanner, String prompt) {
        while(true) {
            int count = readInt(scanner, prompt);
            int maxCount = mapSize / Constants.MAX_COUNT_DELIMETER;
            if (count <= maxCount) {
                return count;
            }
            else {
                System.out.println(Constants.RIGHT_COUNT + maxCount);
            }
        }
    }

    private static int readHp(Scanner scanner) {
        while(true) {
            int hp = readInt(scanner, Constants.HP);
            if (hp > 0 && hp <= Constants.HP_MAX) {
                return hp;
            }
            else {
                System.out.println(Constants.RIGHT_HP);
            }
        }
    }

    private static int readSpeed(Scanner scanner, String prompt) {
        while(true) {
            int speed = readInt(scanner, prompt);
            if (speed > 0 && speed <= Constants.SPEED_MAX) {
                return speed;
            }
            else {
                System.out.println(Constants.RIGHT_SPEED);
            }
        }
    }

    private static int readDamage(Scanner scanner) {
        while(true) {
            int damage = readInt(scanner, Constants.DMG);
            if (damage > 0 && damage <= Constants.DMG_MAX) {
                return damage;
            }
            else {
                System.out.println(Constants.RIGHT_DAMAGE);
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println(Constants.VALUE_MUST_BE_POSITIVE);
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println(Constants.CORRECT_VALUE);
            }
        }
    }


}
