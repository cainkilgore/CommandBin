package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class GameModeCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("survival")) {
			if(args.length < 1) {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						p.setGameMode(GameMode.SURVIVAL);
						PlayerMessage(p, "Game mode changed to survival!");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage(CONSOLE_SENDER);
				}
			} else {
				Player target = getPlayer(args[0]);
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm + ".others")) {
						if(target != null) {
							target.setGameMode(GameMode.SURVIVAL);
							PlayerMessage(p, target.getName() + "'s gamemode set to survival!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					if(target != null) {
						target.setGameMode(GameMode.SURVIVAL);
						ConsoleMessage(target.getName() + "'s gamemode set to survival!");
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("creative")) {
			if(args.length < 1) {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						p.setGameMode(GameMode.CREATIVE);
						PlayerMessage(p, "Game mode changed to creative!");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage(CONSOLE_SENDER);
				}
			} else {
				Player target = getPlayer(args[0]);
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm + ".others")) {
						if(target != null) {
							target.setGameMode(GameMode.CREATIVE);
							PlayerMessage(p, target.getName() + "'s gamemode set to creative!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					if(target != null) {
						target.setGameMode(GameMode.CREATIVE);
						ConsoleMessage(target.getName() + "'s gamemode set to creative!");
					}
				}
			}
		}	
		return false;
	}
}