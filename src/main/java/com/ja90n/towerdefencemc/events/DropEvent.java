package com.ja90n.towerdefencemc.events;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.EnemyType;
import com.ja90n.towerdefencemc.enums.TowerType;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropEvent implements Listener {

    private TowerDefenceMC towerDefenceMC;

    public DropEvent(TowerDefenceMC towerDefenceMC){
        this.towerDefenceMC = towerDefenceMC;
    }

    @EventHandler
    public void ondrop(PlayerDropItemEvent event){
        if (event.getItemDrop().getItemStack().getType().equals(Material.STICK)){
            towerDefenceMC.getEnemyManager().newEnemy(EnemyType.ZOMBIE);
        } else if (event.getItemDrop().getItemStack().getType().equals(Material.IRON_INGOT)){
            towerDefenceMC.getEnemyManager().newEnemy(EnemyType.IRONZOMBIE);
        } else if (event.getItemDrop().getItemStack().getType().equals(Material.GOLD_INGOT)){
            towerDefenceMC.getEnemyManager().newEnemy(EnemyType.GOLDZOMBIE);
        } else if (event.getItemDrop().getItemStack().getType().equals(Material.GHAST_TEAR)){
            towerDefenceMC.getEnemyManager().newEnemy(EnemyType.GHAST);
        }
    }
}
