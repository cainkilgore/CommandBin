package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class HandicapCommands extends CommandBin {
	
	public HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("handicap")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							cfg.set("players." + target.getName() + ".handicapped", true);
							PlayerMessage(p, "You handicapped " + target.getName());
							PlayerMessage(p, "Type /unhandicap [" + target.getName() + "] to unhandicap him!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						cfg.set("players." + target.getName() + ".handicapped", true);
						ConsoleMessage("You handicapped " + target.getName());
						ConsoleMessage("Type /unhandicap [" + target.getName() + "] to unhandicap him!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		if(l.equalsIgnoreCase("unhandicap")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							cfg.set("players." + target.getName() + ".handicapped", false);
							PlayerMessage(p, "You unhandicapped " + target.getName());
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						cfg.set("players." + target.getName() + ".handicapped", false);
						ConsoleMessage("You unhandicapped " + target.getName());
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		return true;
	}
}