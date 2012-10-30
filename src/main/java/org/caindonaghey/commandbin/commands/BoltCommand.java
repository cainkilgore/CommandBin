package org.caindonaghey.commandbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.caindonaghey.commandbin.Utils;

public class BoltCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("bolt")) {
			if(!(s instanceof Player)) {
				if(args.length < 1) {
					Utils.printLine("Applicable Arguments: /bolt player");
					return true;
				}
				
				Player player = Bukkit.getServer().getPlayer(args[0]);
				if(player == null) {
					Utils.printLine(args[0] + " is currently offline. Try again?");
					return true;
				}
				
				player.getWorld().strikeLightning(player.getLocation());
				Utils.printLine("You struck " + player.getName() + " with lightning!");
				return true;
			}
			
			Player player = (Player) s;
			
			if(args.length < 1) {
				Utils.noArguments(player);
				return false;
			}
			
			if(!Utils.checkPermission(player, "CommandBin.bolt")) {
				Utils.invalidPermission(player);
				return true;
			}
			
			Player otherPlayer = Bukkit.getServer().getPlayer(args[0]);
			
			if(otherPlayer == null) {
				Utils.playerMessage(player, args[0] + " is currently offline. Try again?");
				return true;
			}
			
			otherPlayer.getWorld().strikeLightning(player.getLocation());
			Utils.playerMessage(player, "You struck " + otherPlayer.getName() + " with lightning!");
		}
		return true;
	}

}
