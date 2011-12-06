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

public class WarpCommands implements CommandExecutor
{

	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(s instanceof Player)
		{
			if(l.equalsIgnoreCase("setwarp"))
			{
				if(args.length < 1)
				{
					((Player) s).sendMessage("/" + l.toString() + " [warpname]");
				}
				else
				{
					if(BMCommandBin.plugin.pCheck(((Player) s), "CommandBin.general.setwarp"))
					{
						double x = ((Player) s).getLocation().getX();
						double y = ((Player) s).getLocation().getY();
						double z = ((Player) s).getLocation().getZ();
						World world = ((Player) s).getWorld();
						
						if(!((BMCommandBin.plugin.getConfig().getString("settings.warps." + args[0])) != null))
						{
							BMCommandBin.plugin.getConfig().set("settings.warps." + args[0] + ".x", x);
							BMCommandBin.plugin.getConfig().set("settings.warps." + args[0] + ".y", y);
							BMCommandBin.plugin.getConfig().set("settings.warps." + args[0] + ".z", z);
							BMCommandBin.plugin.getConfig().set("settings.warps." + args[0] + ".world", world.getName());
							BMCommandBin.plugin.saveConfig();
							((Player) s).sendMessage(ChatColor.GREEN + "Warp '" + args[0] + "' created.");
							((Player) s).sendMessage(ChatColor.GREEN + "Use /warp " + args[0] + " to teleport!");
						}
						else
						{
							((Player) s).sendMessage(ChatColor.RED + "Warp '" + args[0] + "' already exists!");
						}
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
			}
			
			if(l.equalsIgnoreCase("warp"))
			{
				if(args.length < 1)
				{
					((Player) s).sendMessage("/" + l.toString() + " [warpname]");
				}
				else
				{
					if(BMCommandBin.plugin.pCheck(((Player) s), "CommandBin.general.warp"))
					{
						if(BMCommandBin.plugin.getConfig().get("settings.warps." + args[0]) != null)
						{
							double x = BMCommandBin.plugin.getConfig().getDouble("settings.warps." + args[0] + ".x");
							double y = BMCommandBin.plugin.getConfig().getDouble("settings.warps." + args[0] + ".y");
							double z = BMCommandBin.plugin.getConfig().getDouble("settings.warps." + args[0] + ".z");
							String world = (String) BMCommandBin.plugin.getConfig().get("settings.warps." + args[0] + ".world");
							
							((Player) s).teleport(new Location(Bukkit.getServer().getWorld(world), x,y,z));
							((Player) s).sendMessage(ChatColor.GREEN + "Teleported to warp '" + args[0] + "' successfully!");
						}
						else
						{
							((Player) s).sendMessage(ChatColor.RED + "This warp does not exist.");
						}
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
			}
			
			if(l.equalsIgnoreCase("delwarp"))
			{
				if(args.length < 1)
				{
					((Player) s).sendMessage("/" + l.toString() + " [warpname]");
				}
				else
				{
					if(BMCommandBin.plugin.pCheck(((Player) s), "CommandBin.general.delwarp"))
					{
						if(!(BMCommandBin.plugin.getConfig().get("settings.warps." + args[0]) == null))
						{
							BMCommandBin.plugin.getConfig().set("settings.warps." + args[0], null);
							((Player) s).sendMessage(ChatColor.GREEN + "Warp '" + args[0] + "' removed!");
							BMCommandBin.plugin.saveConfig();
						}
						else
						{
							((Player) s).sendMessage(ChatColor.RED + "This warp does not exist");
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
			s.sendMessage(ChatColor.RED + "You can't use Warps in console!");
		}
		
		return false;
	}

}
