package com.ja90n.towerdefencemc.events;

import com.ja90n.towerdefencemc.TowerDefenceMC;
import com.ja90n.towerdefencemc.enums.TowerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInteract implements Listener {

    private TowerDefenceMC towerDefenceMC;

    private HashMap<UUID,Location> clickedLocation;

    public PlayerInteract(TowerDefenceMC towerDefenceMC){
        this.towerDefenceMC = towerDefenceMC;
        clickedLocation = new HashMap<>();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (towerDefenceMC.getTowerManager().getTower(event.getClickedBlock().getLocation().add(0.5,1,0.5)) == null){

                Inventory inventory = Bukkit.createInventory(event.getPlayer(),9, ChatColor.BLUE + "Place menu");

                inventory.setItem(0,new ItemStack(Material.BOW));
                inventory.setItem(1,new ItemStack(Material.ENCHANTED_BOOK));
                inventory.setItem(2,new ItemStack(Material.TNT));
                event.getPlayer().openInventory(inventory);

                clickedLocation.put(event.getPlayer().getUniqueId(),event.getClickedBlock().getLocation().add(0.5,1,0.5));

            }
        }
    }

    @EventHandler
    public void interactAtEntity(PlayerInteractAtEntityEvent event){
        if (event.getRightClicked() instanceof ArmorStand){
            if (towerDefenceMC.getTowerManager().getTower(event.getRightClicked()) != null){
                event.setCancelled(true);
                towerDefenceMC.getTowerManager().removeTower(towerDefenceMC.getTowerManager().getTower(event.getRightClicked()));
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (event.getCurrentItem() != null){
            if (event.getView().getTitle().equals(ChatColor.BLUE + "Place menu")){
                event.setCancelled(true);
                switch (event.getSlot()){
                    case 0:
                        towerDefenceMC.getTowerManager().newTower(TowerType.ARCHER,clickedLocation.get(event.getWhoClicked().getUniqueId()));
                        break;
                    case 1:
                        towerDefenceMC.getTowerManager().newTower(TowerType.MAGE,clickedLocation.get(event.getWhoClicked().getUniqueId()));
                        break;
                    case 2:
                        towerDefenceMC.getTowerManager().newTower(TowerType.CANON,clickedLocation.get(event.getWhoClicked().getUniqueId()));
                        break;
                }
                event.getWhoClicked().closeInventory();
                clickedLocation.remove(event.getWhoClicked().getUniqueId());
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getView().getTitle().equals(ChatColor.BLUE + "Place menu")){
            clickedLocation.remove(event.getPlayer().getUniqueId());
        }
    }
}
