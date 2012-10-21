package org.caindonaghey.commandbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.caindonaghey.commandbin.Utils;

public class TphereCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		
		if(l.equalsIgnoreCase("tphere")) {
			if(!(s instanceof Player)) {
				Utils.consoleSender();
				return true;
			}
			
			Player player = (Player) s;
			
			if(args.length < 1) {
				Utils.noArguments(player);
				return false;
			}
			
			if(!Utils.checkPermission(player, "CommandBin.tphere")) {
				Utils.invalidPermission(player);
				return true;
			}
			
			Player otherPlayer = Bukkit.getServer().getPlayer(args[0]);
			
			if(otherPlayer == null) {
				Utils.errorMessage(player, args[0] + " is currently offline. Try again?");
				return true;
			}
			
			otherPlayer.teleport(player.getLocation());
			Utils.playerMessage(player, "Teleported " + otherPlayer.getName() + " to you.");
		}
		return true;
	}

}
