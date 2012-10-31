package org.caindonaghey.commandbin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.caindonaghey.commandbin.API;
import org.caindonaghey.commandbin.Utils;

public class AfkCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("afk")) {
			if(!(s instanceof Player)) {
				Utils.consoleSender();
				return true;
			}
			
			Player player = (Player) s;
			
			if(!API.afkPlayer.contains(player.getName())) {
				API.afkPlayer.add(player.getName());
				Utils.broadcastMessage(player.getName() + " is now AFK!");
			} else {
				API.afkPlayer.remove(player.getName());
				Utils.broadcastMessage(player.getName() + " is no longer AFK!");
			}
		}
		return true;
	}

}
