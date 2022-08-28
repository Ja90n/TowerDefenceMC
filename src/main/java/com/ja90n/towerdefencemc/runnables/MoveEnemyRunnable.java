package com.ja90n.towerdefencemc.runnables;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.instances.Enemy;
import com.ja90n.towerdefencemc.utils.TrackGeneratorUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class MoveEnemyRunnable extends BukkitRunnable {

    private Enemy enemy;
    private Location destination;

    private ArmorStand armorStand;

    private ArrayList<Location> track;

    private double movementSpeed;

    public MoveEnemyRunnable(TowerDefenceMC towerDefenceMC, Enemy enemy, Location destination) {

        // Save values
        this.enemy = enemy;
        this.destination = destination;
        armorStand = enemy.getArmorStand();

        movementSpeed = enemy.getMovementSpeed();

        track = new TrackGeneratorUtil(towerDefenceMC).getTrack(movementSpeed);

        // Starts the runnable
        runTaskTimer(towerDefenceMC,0,1);
    }

    @Override
    public void run() {
        if (!track.isEmpty()){
            // Save the destination
            Location destination = track.get(0);

            // Check if there is another point in the line besides the current destination
            if (track.size() != 1){
                // Telport the armorstand to the destination and making it look to the next point
                armorStand.teleport(destination.setDirection(track.get(1).toVector().subtract(destination.toVector())));
            } else {
                // Telport the armorstand to the destination
                armorStand.teleport(destination);
            }
            // Remove the current destination from the line
            track.remove(0);
        } else {
            // If the line is empty then it will cancel the movement of the settler
            Bukkit.broadcastMessage(ChatColor.RED + "Enemy " + ChatColor.WHITE + enemy.getType() +
                    ChatColor.RED + " made it with " + ChatColor.WHITE + enemy.getHealth() + ChatColor.RED + " remaining");
            enemy.remove();
        }
    }
}
