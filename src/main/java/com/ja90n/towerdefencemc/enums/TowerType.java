package com.ja90n.towerdefencemc.enums;

import org.bukkit.Material;

public enum TowerType {

    ARCHER(2.0,0.5,10.0,Material.WOODEN_HOE,1),
    MAGE(4.0,1.0,6.0,Material.WOODEN_HOE,2),
    CANON(8.0,2.0,5.0,Material.WOODEN_HOE,3);
    private double damage,fireRate,range;
    private Material material;
    private int id;
    TowerType(double damage, double fireRate, double range,Material material,int id) {
        this.damage = damage;
        this.fireRate = fireRate;
        this.range = range;

        this.material = material;
        this.id = id;
    }

    public double getDamage() {
        return damage;
    }

    public double getFireRate() {
        return fireRate;
    }

    public double getRange() {
        return range;
    }

    public Material getMaterial() {
        return material;
    }

    public int getId() {
        return id;
    }
}
