package org.cain.cmdbin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class TimeCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("time")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						if(args[0].equalsIgnoreCase("day")) {
							p.getWorld().setTime(0);
							PlayerMessage(p, "Time set to day!");
						}
						if(args[0].equalsIgnoreCase("night")) {
							p.getWorld().setTime(100000000);
							PlayerMessage(p, "Time set to night!");
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage(CONSOLE_SENDER);
				}
			}
		}
		if(s instanceof Player) {
			if(l.equalsIgnoreCase("mytime")) {
				Player p = (Player) s;
				if(args.length < 1) {
					return false;
				} else {
					if(pCheck(p, perm)) {
						if(args[0].equalsIgnoreCase("day")) {
							p.setPlayerTime(0, true);
						}
						if(args[0].equalsIgnoreCase("night")) {
							p.setPlayerTime(100000000, true);
						}
						PlayerMessage(p, "Personal time set to " + args[0] + "!");
					}
				}
			}
		} else {
			ConsoleMessage(CONSOLE_SENDER);
		}
		
		
		return false;
	}
}
