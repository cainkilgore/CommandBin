package org.cain.cmdbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class WarpCommands extends CommandBin {
	
	CommandBin cmdbin;
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		String perm = "CommandBin." + l.toLowerCase();
		
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
					if(pCheck(((Player) s), perm))
					{
						double x = ((Player) s).getLocation().getX();
						double y = ((Player) s).getLocation().getY();
						double z = ((Player) s).getLocation().getZ();
						World world = ((Player) s).getWorld();
						
						if(!((CommandBin.cfg.getString("settings.warps." + args[0])) != null))
						{
							CommandBin.cfg.set("settings.warps." + args[0] + ".x", x);
							CommandBin.cfg.set("settings.warps." + args[0] + ".y", y);
							CommandBin.cfg.set("settings.warps." + args[0] + ".z", z);
							CommandBin.cfg.set("settings.warps." + args[0] + ".world", world.getName());
							CommandBin.cmdbin.saveConfig();
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
						((Player) s).sendMessage(NULL_PERMISSION);
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
					if(pCheck(((Player) s), perm))
					{
						if(cfg.get("settings.warps." + args[0]) != null)
						{
							double x = cfg.getDouble("settings.warps." + args[0] + ".x");
							double y = cfg.getDouble("settings.warps." + args[0] + ".y");
							double z = cfg.getDouble("settings.warps." + args[0] + ".z");
							String world = (String) cfg.get("settings.warps." + args[0] + ".world");
							
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
						((Player) s).sendMessage(NULL_PERMISSION);
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
					if(pCheck(((Player) s), "CommandBin.general.delwarp"))
					{
						if(!(cfg.get("settings.warps." + args[0]) == null))
						{
							CommandBin.cfg.set("settings.warps." + args[0], null);
							((Player) s).sendMessage(ChatColor.GREEN + "Warp '" + args[0] + "' removed!");
							saveConfig();
						}
						else
						{
							((Player) s).sendMessage(ChatColor.RED + "This warp does not exist");
						}
					}
					else
					{
						((Player) s).sendMessage(NULL_PERMISSION);
					}
				}
			}
		}
		else
		{
			s.sendMessage(ChatColor.RED + "You can't use Warps in console!");
		}
		
		return true;
	}

	

}
