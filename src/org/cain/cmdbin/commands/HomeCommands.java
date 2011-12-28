package org.cain.cmdbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class HomeCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(s instanceof Player) {
			if(l.equalsIgnoreCase("sethome")) {
				if(cfg.getBoolean("settings.multihomesupport")) {
					if(args.length < 1) {
						return false;
					} else {
						if(pCheck((Player) s, perm)) {
							double x = ((Player) s).getLocation().getX();
							double y = ((Player) s).getLocation().getY();
							double z = ((Player) s).getLocation().getZ();
							World world = ((Player) s).getWorld();
							cfg.set(((Player) s).getName() + ".home." + args[0] + ".x", x);
							cfg.set(((Player) s).getName() + ".home." + args[0] + ".y", y);
							cfg.set(((Player) s).getName() + ".home." + args[0] + ".z", z);
							cfg.set(((Player) s).getName() + ".home." + args[0] + ".world", world.getName());
							
							((Player) s).sendMessage(ChatColor.GREEN + "Your new home '" + args[0] + "' is set.");
							((Player) s).sendMessage(ChatColor.YELLOW + "Type '/home " + args[0] + "' to teleport");
						}else{
							((Player) s).sendMessage(NULL_PERMISSION);
						}
					}
				} else {
					if(pCheck((Player) s, perm)) {
						double x = ((Player) s).getLocation().getX();
						double y = ((Player) s).getLocation().getY();
						double z = ((Player) s).getLocation().getZ();
						World world = ((Player) s).getWorld();
						cfg.set(((Player) s).getName() + ".home.x", x);
						cfg.set(((Player) s).getName() + ".home.y", y);
						cfg.set(((Player) s).getName() + ".home.z", z);
						cfg.set(((Player) s).getName() + ".home.world", world.getName());
						((Player) s).sendMessage(ChatColor.GREEN + "Your new home is set!");
						((Player) s).sendMessage(ChatColor.YELLOW + "Type /home to teleport to it!");
						CommandBin.cmdbin.saveConfig();
					}else{
						((Player) s).sendMessage(NULL_PERMISSION);
					}
				}
			}
			
			if(l.equalsIgnoreCase("home")) {
				if(cfg.getBoolean("settings.multihomesupport")) {
					if(args.length < 1) {
						return false;
					} else {
						if(pCheck((Player) s, perm)) {
							if(cfg.get(((Player) s).getName() + ".home") != null) {
								if(cfg.get(((Player) s).getName() + ".home." + args[0]) != null) {
									double x = cfg.getDouble(((Player) s).getName() + ".home." + args[0] + ".x");
									double y = cfg.getDouble(((Player) s).getName() + ".home." + args[0] + ".y");
									double z = cfg.getDouble(((Player) s).getName() + ".home." + args[0] + ".z");
									String world = (String) cfg.get(((Player) s).getName() + ".home." + args[0] + ".world");
									((Player) s).teleport(new Location(Bukkit.getServer().getWorld(world), x, y, z));
									((Player) s).sendMessage(ChatColor.GREEN + "Teleported to your home '" + args[0] + "' !");
								} else {
									((Player) s).sendMessage(ChatColor.RED + "This home '" + args[0] + "' does not exist!");
								}
							} else {
								((Player) s).sendMessage(ChatColor.RED + "You have no home! Type /sethome [homename] to set it!");
							}
						} else {
							((Player) s).sendMessage(NULL_PERMISSION);
						}
					}
				} else {
					if(pCheck((Player) s, perm)) {
						if(cfg.get(((Player) s).getName() + ".home") != null) {
							double x = cfg.getDouble(((Player) s).getName() + ".home.x");
							double y = cfg.getDouble(((Player) s).getName() + ".home.y");
							double z = cfg.getDouble(((Player) s).getName() + ".home.z");
							String world = (String) cfg.get(((Player) s).getName() + ".home.world");
							((Player) s).teleport(new Location(Bukkit.getServer().getWorld(world), x, y, z));
							((Player) s).sendMessage(ChatColor.GREEN + "Teleported to your home!");
						} else {
							((Player) s).sendMessage(ChatColor.RED + "You have no home! Type /sethome to set it!");
						}
					} else {
						((Player) s).sendMessage(NULL_PERMISSION);
					}
				}
			}
		} else {
			s.sendMessage(ChatColor.RED + "You can't use home commands in console!");
		}
		return true;
	}

}
