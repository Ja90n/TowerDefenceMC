package com.ja90n.towerdefencemc.instances;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import com.ja90n.towerdefencemc.utils.TrackGeneratorUtil;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;

public class Game {

    private TowerDefenceMC towerDefenceMC;
    private Arena arena;
    private EnemyManager enemyManager;

    private ArrayList<Location> fullTrack;

    public Game(Arena arena) {
        fullTrack = new TrackGeneratorUtil(towerDefenceMC).getFullTrack();
    }

    public ArrayList<Location> getFullTrack() {
        return fullTrack;
    }
}
