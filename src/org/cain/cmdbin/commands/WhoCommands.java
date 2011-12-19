package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class WhoCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("who")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							WhoInformation(p, target);
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						WhoInformationConsole(s, target);
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		return false;
	}

	public void WhoInformation(Player to, Player about) {
		double locx = about.getLocation().getX();
		double locy = about.getLocation().getY();
		double locz = about.getLocation().getZ();
		PlayerMessage(to, "Information about: " + about.getName());
		PlayerMessage(to, "Current world: " + about.getWorld().getName());
		PlayerMessage(to, "IP Address: " + about.getAddress().getAddress());
		PlayerMessage(to, "Location: " + locx + " " + locy + " " + locz);
	}
	public void WhoInformationConsole(CommandSender to, Player about) {
		double locx = about.getLocation().getX();
		double locy = about.getLocation().getY();
		double locz = about.getLocation().getZ();
		ConsoleMessage("Information about: " + about.getName());
		ConsoleMessage("Current world: " + about.getWorld().getName());
		ConsoleMessage("IP Address: " + about.getAddress().getAddress());
		ConsoleMessage("Location: " + locx + " " + locy + " " + locz);
	}

}
