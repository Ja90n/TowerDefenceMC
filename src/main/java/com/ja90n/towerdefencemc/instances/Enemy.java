package com.ja90n.towerdefencemc.instances;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.EnemyType;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import com.ja90n.towerdefencemc.runnables.MoveEnemyRunnable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Enemy {

    private EnemyManager enemyManager;
    private EnemyType type;
    private double movementSpeed;
    private double health;

    private ArmorStand armorStand;

    private MoveEnemyRunnable moveEnemyRunnable;

    public Enemy(EnemyManager enemyManager, EnemyType type, Location spawnLocation, TowerDefenceMC towerDefenceMC) {

        // Saving values for the enemy
        this.enemyManager = enemyManager;
        this.type = type;

        // Creating and setting up the Armorstand
        armorStand = (ArmorStand) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.ARMOR_STAND);
        armorStand.setInvulnerable(true);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setInvisible(true);

        armorStand.getEquipment().setHelmet(new ItemStack(Material.STICK));

        // Putting the correct value for the correct enemy
        movementSpeed = type.getMovementSpeed();
        health = type.getHealth();

        // Creating and saving the move enemy runnable
        moveEnemyRunnable = new MoveEnemyRunnable(towerDefenceMC,this,spawnLocation);

    }

    public void remove(){
        moveEnemyRunnable.cancel();
        armorStand.remove();
    }

    public void damage(double damageAmount){
        if (health - damageAmount < 0){
            enemyManager.removeEnemy(this);
        } else {
            health = health-damageAmount;
        }
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }
}
