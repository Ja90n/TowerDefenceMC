package com.ja90n.towerdefencemc.enums;

import org.bukkit.Material;

public enum EnemyType {

    ZOMBIE(10.0,1.0),
    IRONZOMBIE(20.0,1.5),
    GOLDZOMBIE(30.0,2.0);

    private double health;
    private double movementSpeed;

    EnemyType(double health, double movementSpeed) {
        this.health = health;
        this.movementSpeed = movementSpeed;
    }

    public double getHealth() {
        return health;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }
}
