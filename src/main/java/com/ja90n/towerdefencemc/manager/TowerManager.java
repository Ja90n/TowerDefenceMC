package com.ja90n.towerdefencemc.manager;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.TowerType;
import com.ja90n.towerdefencemc.instances.Tower;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;

public class TowerManager {

    private ArrayList<Tower> towers;
    private TowerDefenceMC towerDefenceMC;

    public TowerManager(TowerDefenceMC towerDefenceMC){
        this.towerDefenceMC = towerDefenceMC;
        towers = new ArrayList<>();
    }

    public void newTower(TowerType type, Location location){
        towers.add(new Tower(type,location,towerDefenceMC));
    }

    public void removeTower(ArmorStand armorStand){
        Tower tower = null;
        for (Tower target : towers){
            if (target.getArmorStand().equals(armorStand)){
                tower = target;
            }
        }
        if (tower != null){
            tower.remove();
            towers.remove(tower);
        }
    }

    public void removeTower(Tower tower){
        tower.remove();
        towers.remove(tower);
    }

    public void clearTowers(){
        for (Tower tower : towers){
            tower.remove();
        }
        towers.clear();
    }
}
