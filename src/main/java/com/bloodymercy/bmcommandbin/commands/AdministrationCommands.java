package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdministrationCommands implements CommandExecutor
{

	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{	
		if(l.equalsIgnoreCase("cmdbin"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage(ChatColor.YELLOW + "+++++++++CommandBin++++++++++");
					((Player) s).sendMessage(ChatColor.GREEN + "+ Over 70 commands in CommandBin!");
					((Player) s).sendMessage(ChatColor.RED + "+ Version: " + BMCommandBin.plugin.getDescription().getVersion());
					((Player) s).sendMessage(ChatColor.LIGHT_PURPLE + "+ Developer: " + BMCommandBin.plugin.getDescription().getAuthors());
					((Player) s).sendMessage(ChatColor.YELLOW + "+++++++++++++++++++++++++++++");
				}
				else
				{
					s.sendMessage(ChatColor.YELLOW + "+++++++++CommandBin++++++++++");
					s.sendMessage(ChatColor.GREEN + "+ Over 70 commands in CommandBin!");
					s.sendMessage(ChatColor.RED + "+ Version: " + BMCommandBin.plugin.getDescription().getVersion());
					s.sendMessage(ChatColor.LIGHT_PURPLE + "+ Developer: " + BMCommandBin.plugin.getDescription().getAuthors());
					s.sendMessage(ChatColor.YELLOW + "+++++++++++++++++++++++++++++");
				}
			}
			else
			{
				if(args[0].equalsIgnoreCase("reload"))
				{
					if(s instanceof Player)
					{
						if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.admin.reload"))
						{
							BMCommandBin.plugin.reloadConfig();
							((Player) s).sendMessage(ChatColor.GREEN + "CommandBin Configuration Reloaded!");
						}
	
						else
						{
							((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
						}
					}
					else
					{
						BMCommandBin.plugin.reloadConfig();
						s.sendMessage(ChatColor.GREEN + "CommandBin Configuration Reloaded!");
					}
				}
				
				if(args[0].equalsIgnoreCase("credits"))
				{
					if(s instanceof Player)
					{
						((Player) s).sendMessage(ChatColor.YELLOW + " - CommandBin Credits");
						((Player) s).sendMessage(ChatColor.GREEN + "Author/Developer/Coder: CainFoool");
						((Player) s).sendMessage(ChatColor.RED + "Tester/Debugger: vicente947");
					}
					else
					{
						s.sendMessage(ChatColor.YELLOW + " - CommandBin Credits");
						s.sendMessage(ChatColor.GREEN + "Author/Developer/Coder: CainFoool");
						s.sendMessage(ChatColor.RED + "Tester/Debugger: vicente947");
					}
				}
				
				if(args[0].equalsIgnoreCase("debug"))
				{
					if(s instanceof Player)
					{
						if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.admin.debug"))
						{
							Runtime rt = Runtime.getRuntime();
							double max = Math.floor(rt.maxMemory() / 1024.0 / 1024.0);
							double free = Math.floor(rt.freeMemory() / 1024.0 / 1024.0);
							((Player) s).sendMessage(ChatColor.RED + "" + free + " / " + max + " memory available"); // Don't ask about the ""
						}
						else
						{
							((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
						}
					}
					else
					{
						Runtime rt = Runtime.getRuntime();
						double max = Math.floor(rt.maxMemory() / 1024.0 / 1024.0);
						double free = Math.floor(rt.freeMemory() / 1024.0 / 1024.0);
						s.sendMessage(ChatColor.RED + "" + free + " / " + max + " memory available");
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("shutdown"))
		{
			if(s instanceof Player)
			{
				if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.admin.shutdown"))
				{
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "Server is going down!");
					Bukkit.getServer().shutdown();
				}
				else
				{
					((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
				}
			}
			else
			{
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "Console initiated shutdown, shutting down server!");
				Bukkit.getServer().shutdown();
			}
		}
		return false;
	}

}
