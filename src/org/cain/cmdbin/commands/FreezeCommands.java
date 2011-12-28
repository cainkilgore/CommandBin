package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class FreezeCommands extends CommandBin {
	
	public HashMap <String, Boolean> hm = new HashMap<String, Boolean>();
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("freeze")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							cfg.set("players." + target.getName() + ".frozen", true);
							PlayerMessage(p, target.getName() + " has been frozen!");
							PlayerMessage(p, "/unfreeze [" + target.getName() + "] to unfreeze him!");
							CommandBin.cmdbin.saveConfig();
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						cfg.set("players." + target.getName() + ".frozen", true);
						ConsoleMessage(target.getName() + " has been frozen!");
						ConsoleMessage("/unfreeze [" + target.getName() + "] to unfreeze him!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unfreeze")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							cfg.set("players." + target.getName() + ".frozen", false);
							CommandBin.cmdbin.saveConfig();
							PlayerMessage(p, target.getName() + " has been unfrozen!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						cfg.set("players." + target.getName() + ".frozen", false);
						ConsoleMessage(target.getName() + " has been unfrozen!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		return false;
	}

}
