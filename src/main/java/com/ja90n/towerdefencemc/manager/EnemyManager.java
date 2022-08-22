package com.ja90n.towerdefencemc.manager;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.EnemyType;
import com.ja90n.towerdefencemc.instances.Enemy;
import com.ja90n.towerdefencemc.runnables.BurningEnemyRunnable;
import com.ja90n.towerdefencemc.utils.TrackGeneratorUtil;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class EnemyManager {

    private TowerDefenceMC towerDefenceMC;
    private ArrayList<Enemy> enemies;

    private ArrayList<Enemy> burning;

    public EnemyManager(TowerDefenceMC towerDefenceMC) {
        this.towerDefenceMC = towerDefenceMC;
        burning = new ArrayList<>();
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

    public void burnEnemy(Enemy enemy){
        if (!burning.contains(enemy)){
            burning.add(enemy);
            new BurningEnemyRunnable(enemy,towerDefenceMC,this);
        }
    }

    public Enemy getFirstEnemy(List<Entity> entities){
        Enemy firstEnemy = null;
        for (Entity entity : entities){
            if (entity instanceof ArmorStand){
                ArmorStand armorStand = (ArmorStand) entity;
                if (getEnemy(armorStand) != null){
                    Enemy enemy = getEnemy(armorStand);
                    if (firstEnemy == null){
                        firstEnemy = enemy;
                    } else {
                        ArrayList<Location> fullTrack = new TrackGeneratorUtil(towerDefenceMC).getFullTrack();
                        if (fullTrack.indexOf(firstEnemy.getArmorStand().getLocation()) < fullTrack.indexOf(enemy.getArmorStand().getLocation())) {
                            firstEnemy = enemy;
                        }
                    }
                }
            }
        }
        return firstEnemy;
    }

    public void clear(){
        for (Enemy enemy : enemies){
            enemy.remove();
        }
        enemies.clear();
    }

    public ArrayList<Enemy> getBurning() {
        return burning;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
