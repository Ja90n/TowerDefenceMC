package com.ja90n.towerdefencemc.utils;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.TowerType;
import com.ja90n.towerdefencemc.instances.Tower;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import com.ja90n.towerdefencemc.runnables.towerfire.ArcherFireRunnable;
import com.ja90n.towerdefencemc.runnables.towerfire.CannonFireRunnable;
import com.ja90n.towerdefencemc.runnables.towerfire.MageFireRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Type;

public class TowerFireRunnableInitiator {

    private TowerType type;
    private ArmorStand armorStand;
    private EnemyManager enemyManager;

    private double damage,fireRate,range;

    private Object runnable;


    public TowerFireRunnableInitiator(TowerType type, Tower tower, TowerDefenceMC towerDefenceMC){

        this.type = type;
        armorStand = tower.getArmorStand();
        enemyManager = towerDefenceMC.getEnemyManager();

        // Gets all the tower specific settings
        damage = type.getDamage();
        fireRate = type.getFireRate()*20; // The *20 converts the seconds to ticks
        range = type.getRange();

        switch (type){
            case CANON:
                runnable = new CannonFireRunnable(type,tower,towerDefenceMC);
                break;
            case ARCHER:
                runnable = new ArcherFireRunnable(type,tower,towerDefenceMC);
                break;
            case MAGE:
                runnable = new MageFireRunnable(type,tower,towerDefenceMC);
                break;
            default:
                Bukkit.broadcastMessage("oei");
                break;
        }
    }

    public Object getRunnable() { return runnable; }
}
