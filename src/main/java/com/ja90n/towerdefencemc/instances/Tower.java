package com.ja90n.towerdefencemc.instances;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.TowerType;
import com.ja90n.towerdefencemc.runnables.TowerFireRunnable;
import com.ja90n.towerdefencemc.utils.ModelItemStackUtil;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Tower {

    private TowerType type;
    private Location location;

    private double damage,fireRate,range;
    private ArmorStand armorStand;

    private TowerFireRunnable towerFireRunnable;

    public Tower(TowerType type, Location location, TowerDefenceMC towerDefenceMC) {

        // Save constructor variables
        this.type = type;
        this.location = location;

        // Creating and setting up the Armorstand
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.getEquipment().setHelmet(new ModelItemStackUtil().getItemstack(type.getMaterial(),type.getId()));
        armorStand.setBasePlate(false);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setGravity(false);

        // Initiating the tower fire runnable
        towerFireRunnable = new TowerFireRunnable(type,this,towerDefenceMC);
    }

    public void remove(){
        towerFireRunnable.cancel();
        armorStand.remove();
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }
}
