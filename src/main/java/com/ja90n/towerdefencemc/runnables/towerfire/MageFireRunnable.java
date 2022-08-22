package com.ja90n.towerdefencemc.runnables.towerfire;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.TowerType;
import com.ja90n.towerdefencemc.instances.Enemy;
import com.ja90n.towerdefencemc.instances.Tower;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class MageFireRunnable extends BukkitRunnable {

    private TowerType type;
    private ArmorStand armorStand;
    private EnemyManager enemyManager;

    private double damage,fireRate,range;

    public MageFireRunnable(TowerType type, Tower tower, TowerDefenceMC towerDefenceMC){

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
            Enemy enemy = enemyManager.getFirstEnemy(armorStand.getNearbyEntities(range,range,range));
            if (enemy != null) { // Checks if there is an entity that is an enemy
                enemy.damage(damage); // Damages the first enemy on the track
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(186, 9, 9), 1.0F);
                enemy.getArmorStand().getWorld().spawnParticle(Particle.REDSTONE, enemy.getArmorStand().getLocation().add(0,0.5,0), 10, dustOptions);
                armorStand.teleport(armorStand.getLocation().setDirection(enemy.getArmorStand().getLocation().toVector()
                        .subtract(armorStand.getLocation().toVector())));
                enemyManager.burnEnemy(enemy);
            }
        }
    }
}
