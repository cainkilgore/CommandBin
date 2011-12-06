package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommands implements CommandExecutor
{

	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(s instanceof Player)
		{
			if(l.equalsIgnoreCase("sethome"))
			{
				if(BMCommandBin.plugin.getConfig().getBoolean("settings.multihomesupport"))
				{
					if(args.length < 1)
					{
						((Player) s).sendMessage("/" + l.toString() + " [home-name]");
					}
					else
					{
						if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.home"))
						{
							double x = ((Player) s).getLocation().getX();
							double y = ((Player) s).getLocation().getY();
							double z = ((Player) s).getLocation().getZ();
							World world = ((Player) s).getWorld();
							
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".home." + args[0] + ".x", x);
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".home." + args[0] + ".y", y);
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".home." + args[0] + ".z", z);
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".home." + args[0] + ".world", world.getName());
							
							((Player) s).sendMessage(ChatColor.GREEN + "Your new home '" + args[0] + "' is set.");
							((Player) s).sendMessage(ChatColor.YELLOW + "Type '/home " + args[0] + "' to teleport");
							
							BMCommandBin.plugin.saveConfig();
							
						}
						else
						{
							((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
						}
					}
				}
				else
				{
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.home"))
					{
						double x = ((Player) s).getLocation().getX();
						double y = ((Player) s).getLocation().getY();
						double z = ((Player) s).getLocation().getZ();
						World world = ((Player) s).getWorld();
						
						BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".home.x", x);
						BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".home.y", y);
						BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".home.z", z);
						BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".home.world", world.getName());
						
						((Player) s).sendMessage(ChatColor.GREEN + "Your new home is set!");
						((Player) s).sendMessage(ChatColor.YELLOW + "Type /home to teleport to it!");
						
						BMCommandBin.plugin.saveConfig();
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
			}
			
			if(l.equalsIgnoreCase("home"))
			{
				if(BMCommandBin.plugin.getConfig().getBoolean("settings.multihomesupport"))
				{
					if(args.length < 1)
					{
						((Player) s).sendMessage("/" + l.toString() + " [home-name]");
					}
					else
					{
						if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.home"))
						{
							if(BMCommandBin.plugin.getConfig().get(((Player) s).getName() + ".home") != null)
							{
								if(BMCommandBin.plugin.getConfig().get(((Player) s).getName() + ".home." + args[0]) != null)
								{
									
									double x = BMCommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home." + args[0] + ".x");
									double y = BMCommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home." + args[0] + ".y");
									double z = BMCommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home." + args[0] + ".z");
									String world = (String) BMCommandBin.plugin.getConfig().get(((Player) s).getName() + ".home." + args[0] + ".world");
									
									((Player) s).teleport(new Location(Bukkit.getServer().getWorld(world), x, y, z));
									((Player) s).sendMessage(ChatColor.GREEN + "Teleported to your home '" + args[0] + "' !");
								}
								else
								{
									((Player) s).sendMessage(ChatColor.RED + "This home '" + args[0] + "' does not exist!");
								}
							}
							else
							{
								((Player) s).sendMessage(ChatColor.RED + "You have no home! Type /sethome [homename] to set it!");
							}
						}
						else
						{
							((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
						}
					}
				}
				else
				{
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.home"))
					{
						if(BMCommandBin.plugin.getConfig().get(((Player) s).getName() + ".home") != null)
						{
							double x = BMCommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home.x");
							double y = BMCommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home.y");
							double z = BMCommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home.z");
							String world = (String) BMCommandBin.plugin.getConfig().get(((Player) s).getName() + ".home.world");
							
							((Player) s).teleport(new Location(Bukkit.getServer().getWorld(world), x, y, z));
							((Player) s).sendMessage(ChatColor.GREEN + "Teleported to your home!");
						}
						else
						{
							((Player) s).sendMessage(ChatColor.RED + "You have no home! Type /sethome to set it!");
						}
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
			}
		}
		else
		{
			s.sendMessage(ChatColor.RED + "You can't use home commands in console!");
		}
		return false;
	}

}
