package me.markiscool.armorstandarms;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public  class ArmorStandListener implements Listener {

    private ArmorStandArmsPlugin plugin;
    private Set<Player> players;

    public ArmorStandListener(ArmorStandArmsPlugin plugin) {
        this.plugin = plugin;
        players  = new HashSet<>();
    }

    @EventHandler
    public void onArmorStandSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof ArmorStand) {
            Player playerToRemove = null;
            for (Player p : players) {
                if(entity.getNearbyEntities(6, 6, 6).contains(p)) {
                    ((ArmorStand) entity).setArms(true);
                    playerToRemove = p;
                    p.sendMessage(ChatColor.GREEN + "You placed an armor stand with arms!");
                    break;
                }
            }
            if(playerToRemove != null) players.remove(playerToRemove);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(this.plugin.getPermission())) {
            Action action = event.getAction();
            if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                if(player.isSneaking()) {
                    ItemStack mainHand = player.getInventory().getItemInMainHand();
                    ItemStack offHand = player.getInventory().getItemInOffHand();
                    if (mainHand.getType().equals(XMaterial.ARMOR_STAND.parseMaterial()) || offHand.getType().equals(XMaterial.ARMOR_STAND.parseMaterial())) {
                        players.add(player);
                    }
                }
            }
        }
    }


}
