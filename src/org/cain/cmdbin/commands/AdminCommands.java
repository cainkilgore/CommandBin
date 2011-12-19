package org.cain.cmdbin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class AdminCommands extends CommandBin {

	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("shutdown")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(pCheck(p, perm)) {
					shutdownServer();
				} else {
					PlayerMessage(p, NULL_PERMISSION);
				}
			} else {
				shutdownServer();
			}
		}
		
		if(l.equalsIgnoreCase("cmdbin")) {
			if(args.length < 1) {
				s.sendMessage(ChatColor.GREEN + "- CommandBin by CainFoool");
				s.sendMessage(ChatColor.RED + "- Version v" + getDescription().getVersion());
			} else {
				if(args[0].equalsIgnoreCase("reload")) {
					if(s instanceof Player) {
						Player p = (Player) s;
						if(pCheck(p, perm + ".reload")) {
							reloadConfig();
							PlayerMessage(p, "Configuration reloaded!");
						} else {
							PlayerMessage(p, NULL_PERMISSION);
						}
					} else {
						reloadConfig();
						ConsoleMessage("Configuration reloaded!");
					}
				}
			}
		}
		return false;
	}
	
	public void shutdownServer() {
			Broadcast("Shutting down server!");
			getServer().shutdown();
	}

}
