package main.java.me.cain.commandbin.commands;

import main.java.me.cain.commandbin.CommandBin;

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
				if(CommandBin.plugin.getConfig().getBoolean("settings.multihomesupport"))
				{
					if(args.length < 1)
					{
						((Player) s).sendMessage("/" + l.toString() + " [home-name]");
					}
					else
					{
						if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.home"))
						{
							double x = ((Player) s).getLocation().getX();
							double y = ((Player) s).getLocation().getY();
							double z = ((Player) s).getLocation().getZ();
							World world = ((Player) s).getWorld();
							
							CommandBin.plugin.getConfig().set(((Player) s).getName() + ".home." + args[0] + ".x", x);
							CommandBin.plugin.getConfig().set(((Player) s).getName() + ".home." + args[0] + ".y", y);
							CommandBin.plugin.getConfig().set(((Player) s).getName() + ".home." + args[0] + ".z", z);
							CommandBin.plugin.getConfig().set(((Player) s).getName() + ".home." + args[0] + ".world", world.getName());
							
							((Player) s).sendMessage(ChatColor.GREEN + "Your new home '" + args[0] + "' is set.");
							((Player) s).sendMessage(ChatColor.YELLOW + "Type '/home " + args[0] + "' to teleport");
							
							CommandBin.plugin.saveConfig();
							
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.NoPermission);
						}
					}
				}
				else
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.home"))
					{
						double x = ((Player) s).getLocation().getX();
						double y = ((Player) s).getLocation().getY();
						double z = ((Player) s).getLocation().getZ();
						World world = ((Player) s).getWorld();
						
						CommandBin.plugin.getConfig().set(((Player) s).getName() + ".home.x", x);
						CommandBin.plugin.getConfig().set(((Player) s).getName() + ".home.y", y);
						CommandBin.plugin.getConfig().set(((Player) s).getName() + ".home.z", z);
						CommandBin.plugin.getConfig().set(((Player) s).getName() + ".home.world", world.getName());
						
						((Player) s).sendMessage(ChatColor.GREEN + "Your new home is set!");
						((Player) s).sendMessage(ChatColor.YELLOW + "Type /home to teleport to it!");
						
						CommandBin.plugin.saveConfig();
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
			}
			
			if(l.equalsIgnoreCase("home"))
			{
				if(CommandBin.plugin.getConfig().getBoolean("settings.multihomesupport"))
				{
					if(args.length < 1)
					{
						((Player) s).sendMessage("/" + l.toString() + " [home-name]");
					}
					else
					{
						if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.home"))
						{
							if(CommandBin.plugin.getConfig().get(((Player) s).getName() + ".home") != null)
							{
								if(CommandBin.plugin.getConfig().get(((Player) s).getName() + ".home." + args[0]) != null)
								{
									
									double x = CommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home." + args[0] + ".x");
									double y = CommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home." + args[0] + ".y");
									double z = CommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home." + args[0] + ".z");
									String world = (String) CommandBin.plugin.getConfig().get(((Player) s).getName() + ".home." + args[0] + ".world");
									
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
							((Player) s).sendMessage(CommandBin.plugin.NoPermission);
						}
					}
				}
				else
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.home"))
					{
						if(CommandBin.plugin.getConfig().get(((Player) s).getName() + ".home") != null)
						{
							double x = CommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home.x");
							double y = CommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home.y");
							double z = CommandBin.plugin.getConfig().getDouble(((Player) s).getName() + ".home.z");
							String world = (String) CommandBin.plugin.getConfig().get(((Player) s).getName() + ".home.world");
							
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
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
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
