package com.ja90n.towerdefencemc.instances;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.manager.ConfigManager;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

public class Arena {

    private TowerDefenceMC towerDefenceMC;
    private ConfigManager configManager;
    private EnemyManager enemyManager;
    private String name;

    private Game game;


    private ArrayList<UUID> players;

    public Arena(TowerDefenceMC towerDefenceMC) {

        this.towerDefenceMC = towerDefenceMC; // Save the main class

        // Gets the managers from the main class
        configManager = towerDefenceMC.getConfigManager();
        enemyManager = towerDefenceMC.getEnemyManager();

        name = configManager.getName(); // Gets the name from the config

        players = new ArrayList<>(); // Creates a new arraylist for the players

        game = new Game(this);

    }

    public Game getGame() {
        return game;
    }
}
