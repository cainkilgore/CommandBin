package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class MuteCommands extends CommandBin {
	public HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("mute")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							cfg.set("players." + target.getName() + ".muted", true);
							PlayerMessage(p, "You muted " + target.getName());
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						cfg.set("players." + target.getName() + ".muted", true);
						ConsoleMessage("You muted " + target.getName());
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unmute")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							cfg.set("players." + target.getName() + ".muted", false);
							PlayerMessage(p, "You unmuted " + target.getName());
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						cfg.set("players." + target.getName() + ".muted", false);
						ConsoleMessage("You unmuted " + target.getName());
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}	
		return false;
	}
}