package main.java.me.cain.commandbin.commands;

import main.java.me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommands implements CommandExecutor
{

	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(l.equalsIgnoreCase("sun"))
		{
			if(s instanceof Player)
			{
				if(CommandBin.plugin.pCheck(((Player) s), "CommandBin.general.weather"))
				{
					((Player) s).getWorld().setStorm(false);
					Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + ((Player) s).getName() + " has made the sun shine!");
				}
				else
				{
					((Player) s).sendMessage(CommandBin.plugin.NoPermission);
				}
			}
			else
			{
				if(args.length < 1)
				{
					s.sendMessage("/sun [world]");
				}
				else
				{
					if(Bukkit.getServer().getWorld(args[0]) != null)
					{
						Bukkit.getServer().getWorld(args[0]).setStorm(false);
						Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "Console has made the sun shine!");
					}
					else
					{
						s.sendMessage(ChatColor.RED + "This world does not exist.");
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("rain"))
		{
			if(s instanceof Player)
			{
				if(CommandBin.plugin.pCheck(((Player) s), "CommandBin.general.weather"))
				{
					((Player) s).getWorld().setStorm(true);
					((Player) s).getWorld().setThundering(true);
					Bukkit.getServer().broadcastMessage(ChatColor.GRAY + ((Player) s).getName() + " made it begin to rain!");
				}
				else
				{
					((Player) s).sendMessage(CommandBin.plugin.NoPermission);
				}
			}
			else
			{
				if(args.length < 1)
				{
					s.sendMessage("/rain [world]");
				}
				else
				{
					if(Bukkit.getServer().getWorld(args[0]) != null)
					{
						Bukkit.getServer().getWorld(args[0]).setStorm(true);
						Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "Console has made the world begin to rain!");
					}
					else
					{
						s.sendMessage(ChatColor.RED + "This world does not exist.");
					}
				}
			}
		}
		return false;
	}

}
