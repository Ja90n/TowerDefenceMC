package com.ja90n.towerdefencemc.runnables;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.instances.Enemy;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class BurningEnemyRunnable extends BukkitRunnable {

    private Enemy enemy;

    private int timeRun;

    private ArmorStand armorStand;

    private EnemyManager enemyManager;

    public BurningEnemyRunnable(Enemy enemy, TowerDefenceMC towerDefenceMC, EnemyManager enemyManager){
        this.enemy = enemy;
        this.enemyManager = enemyManager;
        armorStand = enemy.getArmorStand();
        timeRun = 6;
        runTaskTimer(towerDefenceMC,0,20);
    }


    @Override
    public void run() {
        if (timeRun <= 0){
            enemyManager.getBurning().remove(enemy);
            armorStand.setCustomNameVisible(false);
            cancel();
        } else {
            if (!enemy.getArmorStand().isDead()){
                enemy.damage(1);
                armorStand.setCustomName(ChatColor.GOLD + "On fire");
                armorStand.setCustomNameVisible(true);
            } else {
                enemyManager.getBurning().remove(enemy);
                armorStand.setCustomNameVisible(false);
                cancel();
            }
        }
        timeRun--;
    }
}
