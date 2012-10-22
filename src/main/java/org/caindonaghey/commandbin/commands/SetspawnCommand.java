package org.caindonaghey.commandbin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.caindonaghey.commandbin.Utils;

public class SetspawnCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("setspawn")) {
			if(!(s instanceof Player)) {
				Utils.consoleSender();
				return true;
			}
			
			Player player = (Player) s;
			
			if(!Utils.checkPermission(player, "CommandBin.setspawn")) {
				Utils.invalidPermission(player);
				return true;
			}
			
			player.getWorld().setSpawnLocation((int) player.getLocation().getX(), (int) player.getLocation().getY(), (int) player.getLocation().getZ());
			Utils.playerMessage(player, "World spawn location has been updated to " + player.getLocation().getX() + ", " + player.getLocation().getY() + ", " + player.getLocation().getZ());
		}
		return true;
	}
}