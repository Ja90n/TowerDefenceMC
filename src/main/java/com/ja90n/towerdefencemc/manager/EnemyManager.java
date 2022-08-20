package com.ja90n.towerdefencemc.manager;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.EnemyType;
import com.ja90n.towerdefencemc.instances.Enemy;
import com.ja90n.towerdefencemc.utils.TrackGeneratorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class EnemyManager {

    private TowerDefenceMC towerDefenceMC;
    private ArrayList<Enemy> enemies;

    public EnemyManager(TowerDefenceMC towerDefenceMC) {
        this.towerDefenceMC = towerDefenceMC;
        enemies = new ArrayList<>();
    }

    public void newEnemy(EnemyType type) {
        enemies.add(new Enemy(this, type,towerDefenceMC.getConfigManager().getTrackLocation("0"), towerDefenceMC));
    }

    public void removeEnemy(Enemy enemy){
        enemy.remove();
        enemies.remove(enemy);
    }

    public Enemy getEnemy(ArmorStand armorStand){
        for (Enemy enemy : enemies){
            if (enemy.getArmorStand().equals(armorStand)){
                return enemy;
            }
        }
        return null;
    }

    public Enemy getFirstEnemy(List<Entity> entities){
        Enemy firstEnemy = null;
        ArrayList<ArmorStand> armorStands = new ArrayList<>();
        for (Entity entity : entities){
            if (entity instanceof ArmorStand){
                armorStands.add((ArmorStand) entity);
            }
        }
        for (ArmorStand armorStand : armorStands){
            if (getEnemy(armorStand) != null){
                Enemy enemy = getEnemy(armorStand);
                if (firstEnemy == null){
                    firstEnemy = enemy;
                } else {
                    if (new TrackGeneratorUtil(towerDefenceMC).getFullTrack().indexOf(firstEnemy.getArmorStand().getLocation()) <
                            new TrackGeneratorUtil(towerDefenceMC).getFullTrack().indexOf(enemy.getArmorStand().getLocation())) {
                        firstEnemy = enemy;
                    }
                }
            }
        }
        return firstEnemy;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
