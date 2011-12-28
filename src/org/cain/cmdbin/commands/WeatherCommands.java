package org.cain.cmdbin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class WeatherCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("weather")) {
			if(args.length < 1) {
				return false;
			} else {
				if(args[0].equalsIgnoreCase("sun")) {
					if(s instanceof Player) {
						Player p = (Player) s;
						if(pCheck(p, perm + ".sun")) {
							p.getWorld().setStorm(false);
							PlayerMessage(p, "The sun has come out!");
						} else {
							PlayerMessage(p, NULL_PERMISSION);
						}
					} else {
						ConsoleMessage("You must be in-game to change the weather.");
					}
				}
				if(args[0].equalsIgnoreCase("rain")) {
					if(s instanceof Player) {
						Player p = (Player) s;
						if(pCheck(p, perm + ".rain")) {
							p.getWorld().setStorm(true);
							PlayerMessage(p, "The rain has started!");
						} else {
							PlayerMessage(p, NULL_PERMISSION);
						}
					} else {
						ConsoleMessage("You must be in-game to change the weather.");
					}
				}
			}
		}
		return true;
	}

}
