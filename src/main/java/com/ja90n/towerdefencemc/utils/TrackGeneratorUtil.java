package com.ja90n.towerdefencemc.utils;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class TrackGeneratorUtil {

    private TowerDefenceMC towerDefenceMC;

    private ArrayList<Location> fullTrack;

    public TrackGeneratorUtil(TowerDefenceMC towerDefenceMC) {
        this.towerDefenceMC = towerDefenceMC;
        fullTrack = new ArrayList<>();
    }

    public ArrayList<Location> getTrack(double movementSpeed){

        ArrayList<Location> track = new ArrayList<>();
        ArrayList<Location> locations = getConfigLocations();
        int size = locations.size()-1;
        int i = 0;

        double neededPointsInLine = 20.529*Math.pow(movementSpeed,0.967);
        while (size != i){
            if (locations.get(i) != null){
                int pointsInLine = (int) (locations.get(i).distance(locations.get(i+1)) * (int) Math.round(neededPointsInLine));
                track.addAll(createLine(locations.get(i), locations.get(i + 1), pointsInLine));
            }
            i++;
        }

        /*
        switch (String.valueOf(movementSpeed)){
            case "0.5":
                while (size != i){
                    if (locations.get(i) != null){
                        int pointsInLine = (int) (locations.get(i).distance(locations.get(i+1)) * 40);
                        for (Location location1 : createLine(locations.get(i),locations.get(i+1),pointsInLine)){
                            track.add(location1);
                        }
                    }
                    i++;
                }
                break;
            case "1.0":
                while (size != i){
                    if (locations.get(i) != null){
                        int pointsInLine = (int) (locations.get(i).distance(locations.get(i+1)) * 20);
                        for (Location location1 : createLine(locations.get(i),locations.get(i+1),pointsInLine)){
                            track.add(location1);
                        }
                    }
                    i++;
                }
                break;
            case "1.5":
                int neededPointsInLine =
                while (size != i){
                    if (locations.get(i) != null){
                        int pointsInLine = (int) (locations.get(i).distance(locations.get(i+1)) * 15);
                        for (Location location1 : createLine(locations.get(i),locations.get(i+1),pointsInLine)){
                            track.add(location1);
                        }
                    }
                    i++;
                }
                break;
            case "2.0":
                while (size != i){
                    if (locations.get(i) != null){
                        int pointsInLine = (int) (locations.get(i).distance(locations.get(i+1)) * 10);
                        for (Location location1 : createLine(locations.get(i),locations.get(i+1),pointsInLine)){
                            track.add(location1);
                        }
                    }
                    i++;
                }
                break;
        }

         */
        return track;
    }


    public ArrayList<Location> getFullTrack() {
        if (fullTrack.isEmpty()){
            ArrayList<Location> locations = getConfigLocations();
            int size = locations.size()-1;
            int i = 0;
            while (size != i){
                if (locations.get(i) != null){
                    int pointsInLine = (int) (locations.get(i).distance(locations.get(i+1)) * 80);
                    fullTrack.addAll(createLine(locations.get(i), locations.get(i + 1), pointsInLine));
                }
                i++;
            }
        }
        return fullTrack;
    }

    // Returns an Arraylist of all the locations listed in the config files
    public ArrayList<Location> getConfigLocations(){
        ArrayList<Location> locations = new ArrayList<>();
        FileConfiguration config = towerDefenceMC.getConfig();
        for (String str : config.getConfigurationSection("locations.").getKeys(false)){
            locations.add(towerDefenceMC.getConfigManager().getTrackLocation(str));
        }
        return locations;
    }

    // Black magic
    public ArrayList<Location> createLine(Location point1, Location point2, int pointsInLine) {
        double p1X = point1.getX();
        double p1Y = point1.getY();
        double p1Z = point1.getZ();
        double p2X = point2.getX();
        double p2Y = point2.getY();
        double p2Z = point2.getZ();

        double lineAveX = (p2X-p1X)/pointsInLine;
        double lineAveY = (p2Y-p1Y)/pointsInLine;
        double lineAveZ = (p2Z-p1Z)/pointsInLine;

        World world = point1.getWorld();
        ArrayList<org.bukkit.Location> line = new ArrayList<>();
        for(int i = 0; i <= pointsInLine; i++){
            org.bukkit.Location loc = new org.bukkit.Location(world, p1X + lineAveX * i, p1Y + lineAveY * i, p1Z + lineAveZ * i);
            line.add(loc);
        }
        return line;
    }
}
