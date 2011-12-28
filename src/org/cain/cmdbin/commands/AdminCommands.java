package org.cain.cmdbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class AdminCommands extends CommandBin {
	
	CommandBin cmdbin;

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
				s.sendMessage(ChatColor.RED + "- Version v" + CommandBin.cmdbin.getDescription().getVersion());
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
				
				if(args[0].equalsIgnoreCase("ram")) {
					if(s instanceof Player) {
						Player p = (Player) s;
						if(pCheck(p, perm + ".ram")) {
							Runtime rt = Runtime.getRuntime();
							double max = Math.floor(rt.maxMemory() / 1024.0 / 1024.0);
							double free = Math.floor(rt.freeMemory() / 1024.0 / 1024.0);
							((Player) s).sendMessage(ChatColor.RED + "" + free + " / " + max + " memory available");
						} else {
							PlayerMessage(p, NULL_PERMISSION);
						}
					} else {
						Runtime rt = Runtime.getRuntime();
						double max = Math.floor(rt.maxMemory() / 1024.0 / 1024.0);
						double free = Math.floor(rt.freeMemory() / 1024.0 / 1024.0);
						ConsoleMessage(ChatColor.RED + "" + free + " / " + max + " memory available");
					}
				}
			}
		}
		return true;
	}
	
	public void shutdownServer() {
			Broadcast("Shutting down server!");
			Bukkit.getServer().shutdown();
	}

}
