package com.ja90n.towerdefencemc.manager;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private FileConfiguration config;

    public ConfigManager(TowerDefenceMC towerDefenceMC){

        config = towerDefenceMC.getConfig(); // Getting the config from the main class

    }

    public Location getTrackLocation(String id){
        return new Location(Bukkit.getWorld(config.getString("locations." + id + ".world")),
                config.getDouble("locations." + id + ".x"),
                config.getDouble("locations." + id + ".y"),
                config.getDouble("locations." + id + ".z")
        );
    }

    public String getName(){
        return config.getString("name");
    }

}
