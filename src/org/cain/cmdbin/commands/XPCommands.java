package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class XPCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("xp")) {
			if(args.length < 2) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							try {
								target.setTotalExperience(Integer.parseInt(args[1]));
								PlayerMessage(p, "Experience set!");
							} catch (NumberFormatException e) {
								PlayerMessage(p, "You never entered a valid number!");
							}
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						try {
							target.setTotalExperience(Integer.parseInt(args[1]));
							ConsoleMessage("Experience set!");
						} catch (NumberFormatException e) {
							ConsoleMessage("You never entered a valid number!");
						}
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		return true;
	}

}
