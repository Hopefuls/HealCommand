package com.growlyx;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.ConsoleCommandSender;


public class heal extends JavaPlugin {


    @Override
    public void onEnable() {
        System.out.println("HealCommand V1.0 Enabled");

        Bukkit.getPluginManager().registerEvents((Listener) new ToggleListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("HealCommand V1.0 Disabled");
    }


    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage("This command can only be used as a player!");
                return true;
            }
            Player player = (Player) sender;
            if (player.isOp() || (player.hasPermission("heal.heal"))) {
            } else {
                player.sendMessage(ChatColor.RED + "Sorry, you do not have permission to use that command.");
                return false;
            }
            if(args.length == 0){
                player.setHealth(20);
                player.sendMessage(ChatColor.YELLOW + "You've just healed yourself.");
            }else if(args.length == 1){
                if(player.getServer().getPlayer(args[0]) != null){
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    targetPlayer.setHealth(20);
                    player.sendMessage(ChatColor.YELLOW + "You've just healed a player.");
                    targetPlayer.sendMessage(ChatColor.YELLOW + "You've been healed.");
                }else{
                    player.sendMessage(ChatColor.GOLD + "Error: " + ChatColor.RED + "Player is not " + ChatColor.UNDERLINE + "ONLINE");
                }
            }
        }

        return false;
    }
}
