package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class BanCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("ban")) {
			if(args.length < 2) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						StringBuilder x = new StringBuilder();
						for(int i = 1; i < args.length; i++) {
							x.append(args[i] + " ");
						}
						if(target != null) {
							cfg.set("players." + target.getName() + ".banned", true);
							cfg.set("players." + target.getName() + ".banreason", x.toString().trim());
							target.kickPlayer("You were banned. Reason: " + x.toString().trim());
							PlayerMessage(p, target.getName() + " has been banned!");
							PlayerMessage(p, target.getName() + " Ban Reason: " + x.toString().trim());
						} else {
							cfg.set("players." + args[0].toLowerCase() + ".banned", true);
							cfg.set("players." + args[0].toLowerCase() + ".banreason", x.toString().trim());
							PlayerMessage(p, args[0].toLowerCase() + " has been banned!");
							PlayerMessage(p, args[0].toLowerCase() + " Ban Reason: " + x.toString().trim());
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					StringBuilder x = new StringBuilder();
					for(int i = 1; i < args.length; i++) {
						x.append(args[i] + " ");
					}
					if(target != null) {
						cfg.set("players." + target.getName() + ".banned", true);
						cfg.set("players." + target.getName() + ".banreason", x.toString().trim());
						target.kickPlayer("You were banned. Reason: " + x.toString().trim());
						ConsoleMessage(target.getName() + " has been banned!");
						ConsoleMessage(target.getName() + " Ban Reason: " + x.toString().trim());
					} else {
						cfg.set("players." + args[0].toLowerCase() + ".banned", true);
						cfg.set("players." + args[0].toLowerCase() + ".banreason", x.toString().trim());
						ConsoleMessage(args[0].toLowerCase() + " has been banned!");
						ConsoleMessage(args[0].toLowerCase() + " Ban Reason: " + x.toString().trim());
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unban")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						cfg.set("players." + args[0] + ".banned", false);
						PlayerMessage(p, args[0] + " has been unbanned!");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					cfg.set("players." + args[0].toLowerCase() + ".banned", false);
					ConsoleMessage(args[0] + " has been unbanned!");
				}
			}
		}
		return false;
	}
}
