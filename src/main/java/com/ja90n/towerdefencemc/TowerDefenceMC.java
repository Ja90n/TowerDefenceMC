package com.ja90n.towerdefencemc;

import com.ja90n.towerdefencemc.events.DropEvent;
import com.ja90n.towerdefencemc.events.PlayerInteract;
import com.ja90n.towerdefencemc.events.PlayerJoin;
import com.ja90n.towerdefencemc.instances.Arena;
import com.ja90n.towerdefencemc.instances.Tower;
import com.ja90n.towerdefencemc.manager.ConfigManager;
import com.ja90n.towerdefencemc.manager.EnemyManager;
import com.ja90n.towerdefencemc.manager.TowerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TowerDefenceMC extends JavaPlugin {

    private ConfigManager configManager;
    private EnemyManager enemyManager;
    private TowerManager towerManager;

    private Arena arena;

    @Override
    public void onEnable() {

        saveDefaultConfig(); // Saving the default config if it is not saved already

        // Initiates the managers
        configManager = new ConfigManager(this);
        enemyManager = new EnemyManager(this);
        towerManager = new TowerManager(this);

        // Initiates the arena
        // arena = new Arena(this);

        // Registers events
        getServer().getPluginManager().registerEvents(new PlayerInteract(this),this);
        getServer().getPluginManager().registerEvents(new DropEvent(this),this);
        //getServer().getPluginManager().registerEvents(new PlayerJoin(),this);

        // Registers commands
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        towerManager.clearTowers();
        enemyManager.clear();
    }

    public ConfigManager getConfigManager() {return configManager;}
    public EnemyManager getEnemyManager() {return enemyManager;}

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public Arena getArena() {
        return arena;
    }
}
