package com.ja90n.towerdefencemc.instances;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.TowerType;
import com.ja90n.towerdefencemc.runnables.towerfire.ArcherFireRunnable;
import com.ja90n.towerdefencemc.runnables.towerfire.CannonFireRunnable;
import com.ja90n.towerdefencemc.runnables.towerfire.MageFireRunnable;
import com.ja90n.towerdefencemc.utils.ModelItemStackUtil;
import com.ja90n.towerdefencemc.utils.TowerFireRunnableInitiator;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Tower {

    private TowerType type;
    private Location location;

    private TowerFireRunnableInitiator towerFireRunnableInitiator;

    private ArmorStand armorStand;

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

        // Start the fire runnable
        towerFireRunnableInitiator = new TowerFireRunnableInitiator(type,this,towerDefenceMC);

    }

    public void remove(){
        armorStand.remove();

        if (towerFireRunnableInitiator.getRunnable() instanceof CannonFireRunnable){
            CannonFireRunnable cannonFireRunnable = (CannonFireRunnable) towerFireRunnableInitiator.getRunnable();
            cannonFireRunnable.cancel();
        } else if (towerFireRunnableInitiator.getRunnable() instanceof MageFireRunnable){
            MageFireRunnable mageFireRunnable = (MageFireRunnable) towerFireRunnableInitiator.getRunnable();
            mageFireRunnable.cancel();
        } else if (towerFireRunnableInitiator.getRunnable() instanceof ArcherFireRunnable){
            ArcherFireRunnable archerFireRunnable = (ArcherFireRunnable) towerFireRunnableInitiator.getRunnable();
            archerFireRunnable.cancel();
        }
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }
}
