package com.ja90n.towerdefencemc.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().setResourcePack("https://www.dropbox.com/s/jth34r1klj3ua21/Tower%20Defence.zip?dl=1");
    }
}

