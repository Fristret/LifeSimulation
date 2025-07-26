package org.simulation.config;

public record GameParameters(int treeCount, int rockCount, int grassCount, int herbivoreCount, int herbSpeed,
                             int herbHp, int predatorCount, int predatorSpeed, int predatorDamage, int mapSize) {

}
