package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JailCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(l.equalsIgnoreCase("jail"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/jail [player]");
				}
				else
				{
					s.sendMessage("/jail [player]");
				}
			}
			else
			{
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.jail"))
					{
						if(target != null)
						{
							if(CommandBin.plugin.getConfig().get("jail.jail.location") != null)
							{
								int x = CommandBin.plugin.getConfig().getInt("jail.jail.location.x");
								int y = CommandBin.plugin.getConfig().getInt("jail.jail.location.y");
								int z = CommandBin.plugin.getConfig().getInt("jail.jail.location.z");
								World world = Bukkit.getServer().getWorld(CommandBin.plugin.getConfig().getString("jail.jail.location.world"));
								
								target.teleport(new Location(world, x, y, z));
								target.sendMessage(ChatColor.GREEN + "You have been JAILED!");
								((Player) s).sendMessage(ChatColor.GREEN + "You jailed " + target.getName());
								CommandBin.plugin.getConfig().set("jail.players." + target.getName(), true);
								CommandBin.plugin.saveConfig();
							}
							else
							{
								((Player) s).sendMessage(ChatColor.RED + "You have not created a jail!");
								((Player) s).sendMessage(ChatColor.RED + "Type /setjail to set a jail location!");
							}
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					if(target != null)
					{
						if(CommandBin.plugin.getConfig().get("jail.jail.location") != null)
						{
							int x = CommandBin.plugin.getConfig().getInt("jail.jail.location.x");
							int y = CommandBin.plugin.getConfig().getInt("jail.jail.location.y");
							int z = CommandBin.plugin.getConfig().getInt("jail.jail.location.z");
							World world = Bukkit.getServer().getWorld(CommandBin.plugin.getConfig().getString("jail.jail.location.world"));
							
							target.teleport(new Location(world, x, y, z));
							target.sendMessage(ChatColor.GREEN + "You have been JAILED!");
							s.sendMessage(ChatColor.GREEN + "You jailed " + target.getName());
							CommandBin.plugin.getConfig().set("jail.players." + target.getName(), true);
							CommandBin.plugin.saveConfig();
						}
						else
						{
							s.sendMessage(ChatColor.RED + "You have not created a jail!");
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unjail"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/unjail [player]");
				}
				else
				{
					s.sendMessage("/unjail [player]");
				}
			}
			else
			{
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.unjail"))
					{
						if(target != null)
						{
							if(CommandBin.plugin.getConfig().get("jail.unjail") != null)
							{
								if(CommandBin.plugin.getConfig().getBoolean("jail.players." + target.getName()))
								{
									int x = CommandBin.plugin.getConfig().getInt("jail.unjail.location.x");
									int y = CommandBin.plugin.getConfig().getInt("jail.unjail.location.y");
									int z = CommandBin.plugin.getConfig().getInt("jail.unjail.location.z");
									World world = Bukkit.getServer().getWorld(CommandBin.plugin.getConfig().getString("jail.unjail.location.world"));
									
									CommandBin.plugin.getConfig().set("jail.players." + target.getName(), null);
									((Player) s).sendMessage(ChatColor.GREEN + "Unjailed " + target.getName());
									target.sendMessage(ChatColor.GREEN + "You have been unjailed by " + ((Player) s).getName());
									CommandBin.plugin.saveConfig();
									target.teleport(new Location(world, x, y, z));
								}
								else
								{
									((Player) s).sendMessage(ChatColor.GREEN + "This player is not jailed!");
								}
							}
							else
							{
								((Player) s).sendMessage(ChatColor.RED + "You have not set a unjail location!");
								((Player) s).sendMessage(ChatColor.RED + "Type /setunjail to set the unjail location!");
							}
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					if(target != null)
					{
						if(CommandBin.plugin.getConfig().get("jail.unjail") != null)
						{
							if(CommandBin.plugin.getConfig().getBoolean("jail.players." + target.getName()))
							{
								int x = CommandBin.plugin.getConfig().getInt("jail.unjail.location.x");
								int y = CommandBin.plugin.getConfig().getInt("jail.unjail.location.y");
								int z = CommandBin.plugin.getConfig().getInt("jail.unjail.location.z");
								World world = Bukkit.getServer().getWorld(CommandBin.plugin.getConfig().getString("jail.unjail.location.world"));
								
								CommandBin.plugin.getConfig().set("jail.players." + target.getName(), null);
								s.sendMessage(ChatColor.GREEN + "Unjailed " + target.getName());
								target.sendMessage(ChatColor.GREEN + "You have been unjailed by " + ((Player) s).getName());
								CommandBin.plugin.saveConfig();
								target.teleport(new Location(world, x, y, z));
							}
							else
							{
								s.sendMessage(ChatColor.GREEN + "This player is not jailed!");
							}
						}
						else
						{
							s.sendMessage(ChatColor.RED + "You have not set a unjail location!");
						}
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
					
				}
			}
		}
		
		if(s instanceof Player)
		{
			if(l.equalsIgnoreCase("setjail"))
			{
				if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.setjail"))
				{
					double bx = ((Player) s).getLocation().getBlock().getX();
					double by = ((Player) s).getLocation().getBlock().getY();
					double bz = ((Player) s).getLocation().getBlock().getZ();
					String world = ((Player) s).getWorld().getName();
					CommandBin.plugin.getConfig().set("jail.jail.location.x", bx);
					CommandBin.plugin.getConfig().set("jail.jail.location.y", by);
					CommandBin.plugin.getConfig().set("jail.jail.location.z", bz);
					//Location block = ((Player) s).getLocation().getBlock().getX().getLocation();
					CommandBin.plugin.getConfig().set("jail.jail.location.world", world);
					((Player) s).sendMessage(ChatColor.GREEN + "Jail set!");
				}
				else
				{
					((Player) s).sendMessage(CommandBin.plugin.NoPermission);
				}
			}
			
			if(l.equalsIgnoreCase("setunjail"))
			{
				if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.setunjail"))
				{
					double bx = ((Player) s).getLocation().getBlock().getX();
					double by = ((Player) s).getLocation().getBlock().getY();
					double bz = ((Player) s).getLocation().getBlock().getZ();
					String world = ((Player) s).getWorld().getName();
					CommandBin.plugin.getConfig().set("jail.unjail.location.x", bx);
					CommandBin.plugin.getConfig().set("jail.unjail.location.y", by);
					CommandBin.plugin.getConfig().set("jail.unjail.location.z", bz);
					CommandBin.plugin.getConfig().set("jail.unjail.location.world", world);
					((Player) s).sendMessage(ChatColor.GREEN + "Unjail set!");
				}
				else
				{
					((Player) s).sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		else
		{
			s.sendMessage(ChatColor.RED + "You can't set a jail in console!");
		}
		return false;
	}

}
