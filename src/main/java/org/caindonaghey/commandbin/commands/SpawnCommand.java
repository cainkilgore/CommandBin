package org.caindonaghey.commandbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.caindonaghey.commandbin.Utils;

public class SpawnCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("spawn")) {
			if(!(s instanceof Player)) {
				if(args.length < 1) {
					Utils.printLine("Applicable arguments: /spawn <player>");
					return true;
				}
				
				Player player = Bukkit.getServer().getPlayer(args[0]);
				
				if(player == null) {
					Utils.printLine(args[0] + " is currently offline. Try again?");
					return true;
				}
				
				player.teleport(player.getWorld().getSpawnLocation());
				Utils.printLine("Teleported " + player.getName() + " to their world spawn.");
				return true;
			}
			
			Player player = (Player) s;
			
			if(args.length < 1) {
				if(!Utils.checkPermission(player, "CommandBin.spawn.self")) {
					Utils.invalidPermission(player);
					return true;
				}
				
				player.teleport(player.getWorld().getSpawnLocation());
				Utils.playerMessage(player, "Teleported to your world spawn.");
				return true;
			}
			
			if(!Utils.checkPermission(player, "CommandBin.spawn.others")) {
				Utils.invalidPermission(player);
				return true;
			}
			
			Player otherPlayer = Bukkit.getServer().getPlayer(args[0]);
			
			if(otherPlayer == null) {
				Utils.errorMessage(player, args[0] + " is currently offline. Try again?");
				return true;
			}
			
			otherPlayer.teleport(otherPlayer.getWorld().getSpawnLocation());
			Utils.playerMessage(player, "Teleported " + otherPlayer.getName() + " to their world spawn.");
		}
		return true;
	}

}
