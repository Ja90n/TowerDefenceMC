package com.ja90n.towerdefencemc.runnables;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.instances.Enemy;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class BurningEnemyRunnable extends BukkitRunnable {

    private Enemy enemy;

    private int timeRun;

    private EnemyManager enemyManager;

    public BurningEnemyRunnable(Enemy enemy, TowerDefenceMC towerDefenceMC, EnemyManager enemyManager){
        this.enemy = enemy;
        this.enemyManager = enemyManager;
        timeRun = 6;
        runTaskTimer(towerDefenceMC,0,20);
    }


    @Override
    public void run() {
        if (timeRun >= 0){
            enemyManager.getBurning().remove(enemy);
            cancel();
        } else {
            if (enemy != null){
                enemy.damage(1);
                ArmorStand armorStand = enemy.getArmorStand();
                armorStand.setFireTicks(20);
            } else {
                enemyManager.getBurning().remove(enemy);
                cancel();
            }
        }
        timeRun--;
    }
}
