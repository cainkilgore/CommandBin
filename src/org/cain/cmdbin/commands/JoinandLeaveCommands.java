package org.cain.cmdbin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class JoinandLeaveCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {	
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("join")) {
			if(args.length < 1) {
				return false;
			} else {
				StringBuilder x = new StringBuilder();
				for(int i = 1; i < args.length; i++) {
					x.append(args[i] + " ");
				}
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Broadcast(ChatColor.YELLOW + x.toString().trim() + " has joined the game");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Broadcast(ChatColor.YELLOW + x.toString().trim() + " has joined the game");
				}
			}
		}
		
		if(l.equalsIgnoreCase("leave")) {
			if(args.length < 1) {
				return false;
			} else {
				StringBuilder x = new StringBuilder();
				for(int i = 1; i < args.length; i++) {
					x.append(args[i] + " ");
				}
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Broadcast(ChatColor.YELLOW + x.toString().trim() + " has left the game");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Broadcast(ChatColor.YELLOW + x.toString().trim() + " has left the game");
				}
			}
		}
		return false;
		
	}

}
