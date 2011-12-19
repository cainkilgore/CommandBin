package org.cain.cmdbin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;
import static org.bukkit.Bukkit.*;

public class NickCommands extends CommandBin{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
				if(l.equalsIgnoreCase("nick")) {
					if(args.length < 2) {
						return false;
					} else {
						if(s instanceof Player) {
							Player p = (Player) s;
							if(pCheck(p, perm)) {
								Player target = getPlayer(args[0]);
								if(target != null) {
									target.setDisplayName(args[1]);
									PlayerMessage(p, "Display name set!");
								} else {
									PlayerMessage(p, PLAYER_OFFLINE);
								}
							} else {
								PlayerMessage(p, NULL_PERMISSION);
							}
						} else {
							Player target = getPlayer(args[0]);
							if(target != null) {
								target.setDisplayName(args[1]);
								ConsoleMessage("Display name set!");
							} else {
								ConsoleMessage(PLAYER_OFFLINE);
							}
					}
				}
			}
			return false;
	}

}
