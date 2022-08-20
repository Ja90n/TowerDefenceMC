package com.ja90n.towerdefencemc.runnables;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.TowerType;
import com.ja90n.towerdefencemc.instances.Tower;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class TowerFireRunnable extends BukkitRunnable {

    private TowerType type;
    private ArmorStand armorStand;
    private EnemyManager enemyManager;

    private double damage,fireRate,range;

    public TowerFireRunnable(TowerType type, Tower tower, TowerDefenceMC towerDefenceMC){

        this.type = type;
        armorStand = tower.getArmorStand();
        enemyManager = towerDefenceMC.getEnemyManager();

        // Gets all the tower specific settings
        damage = type.getDamage();
        fireRate = type.getFireRate()*20; // The *20 converts the seconds to ticks
        range = type.getRange();

        runTaskTimer(towerDefenceMC,0, (int) fireRate);
    }

    @Override
    public void run() {
        if (!armorStand.getNearbyEntities(range,range,range).isEmpty()) { // Checks if there are entities in range
            if (enemyManager.getFirstEnemy(armorStand.getNearbyEntities(range,range,range)) != null) { // Checks if there is an entity that is an enemy
                enemyManager.getFirstEnemy(armorStand.getNearbyEntities(range,range,range)).damage(damage); // Damages the first enemy on the track
            }
        }
    }
}
