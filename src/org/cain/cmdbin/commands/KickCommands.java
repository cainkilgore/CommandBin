package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class KickCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("kick")) {
			if(args.length < 2) {
				return false;
			} else {
				StringBuilder x = new StringBuilder();
				for(int i = 1; i < args.length; i++) {
					x.append(args[i] + " ");
				}
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
								target.kickPlayer(x.toString().trim());
								PlayerMessage(p, target.getName() + " has been kicked!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.kickPlayer(x.toString().trim());
						ConsoleMessage(target.getName() + " has been kicked!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		return false;
		
	}

}
