package org.cain.cmdbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class WorldCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("createworld")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						PlayerMessage(p, "New world '" + args[0] + "' being created. Be patient!");
					    Bukkit.getServer().createWorld(new WorldCreator(args[0]));
						PlayerMessage(p, "New world created!");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage("New world creating. Please wait!");
					Bukkit.getServer().createWorld(new WorldCreator(args[0]));
					ConsoleMessage("New world created!");
				}
			}
		}
		
		if(l.equalsIgnoreCase("tpworld")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						if(Bukkit.getServer().getWorld(args[0]) != null) {
							p.teleport(getServer().getWorld(args[0]).getSpawnLocation());
							PlayerMessage(p, "Teleported!");
						} else {
							PlayerMessage(p, "This world does not exist.");
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage(CONSOLE_SENDER);
				}
			}
		}
		
		if(l.equalsIgnoreCase("unloadworld")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						if(Bukkit.getServer().getWorld(args[0]) != null) {
							Bukkit.getServer().unloadWorld(args[0], true);
							PlayerMessage(p, "World unloaded!");
						} else {
							PlayerMessage(p, "This world is already unloaded / does not exist");
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					if(Bukkit.getServer().getWorld(args[0]) != null) {
						Bukkit.getServer().unloadWorld(args[0], true);
						ConsoleMessage("Unloaded world!");
					} else {
						ConsoleMessage("This world is already unloaded / does not exist.");
					}
				}
			}
		}
		
		return false;
	}
}