package org.cain.cmdbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;
import org.getspout.spoutapi.SpoutManager;

public class SpoutCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("snick")) {
			if(args.length < 2) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							SpoutManager.getPlayer(target).setTitle(args[1]);
							PlayerMessage(p, "Title name set for " + target.getName());
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null) {
							SpoutManager.getPlayer(target).setTitle(args[1]);
							ConsoleMessage("Title name set for " + target.getName());
						} else {
							ConsoleMessage(PLAYER_OFFLINE);
						}
					}
				}	
			}
		
		if(l.equalsIgnoreCase("scape")) {
			if(args.length < 2) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							SpoutManager.getPlayer(target).setCape(args[1]);
							PlayerMessage(p, "Cape set for " + target.getName());
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null) {
						SpoutManager.getPlayer(target).setCape(args[1]);
						ConsoleMessage("Cape set for " + target.getName());
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("sskin")) {
			if(args.length < 2) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							SpoutManager.getPlayer(target).setSkin(args[1]);
							PlayerMessage(p, "Skin set for " + target.getName());
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null) {
						SpoutManager.getPlayer(target).setSkin(args[1]);
						ConsoleMessage("Skin set for " + target.getName());
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		return true;
	}
}
