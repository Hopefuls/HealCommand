package com.growlyx;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class ToggleListener extends JavaPlugin {

    ArrayList<Player> enabled = new ArrayList<>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        Player player = e.getPlayer();

        if (player.getItemInHand().getType().equals(Material.NETHER_STAR))
            if (enabled.contains(player)) {
                player.sendMessage("You Have Disabled Fly!");
                player.setFlying(false);
                enabled.remove(player);
            } else {
                player.sendMessage("You have enabled Fly!");
                player.setFlying(true);
                enabled.add(player);
            }
    }

    @EventHandler
    public  void onChat(AsyncPlayerChatEvent e) {

        if (enabled.contains(e.getPlayer()))
            e.setCancelled(true);
        e.getPlayer().sendMessage("Disabled Fly.");
    }
}
