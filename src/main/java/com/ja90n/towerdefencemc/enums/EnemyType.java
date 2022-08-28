package com.ja90n.towerdefencemc.enums;

import org.bukkit.Material;

public enum EnemyType {

    ZOMBIE(10.0,1.0,Material.WOODEN_SWORD,1),
    IRONZOMBIE(20.0,1.5,Material.WOODEN_SWORD,2),
    GOLDZOMBIE(30.0,2.0,Material.WOODEN_SWORD,3),

    GHAST(100.0,0.5,Material.WOODEN_SWORD,4);

    private double health,movementSpeed;
    private Material material;
    private int id;

    EnemyType(double health, double movementSpeed, Material material, int id) {
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.material = material;
        this.id = id;
    }

    public double getHealth() {
        return health;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public Material getMaterial() {
        return material;
    }

    public int getId() {
        return id;
    }
}
