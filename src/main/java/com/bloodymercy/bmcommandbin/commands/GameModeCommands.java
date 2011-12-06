package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommands implements CommandExecutor
{

	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(l.equalsIgnoreCase("creative"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.creative"))
					{
						((Player) s).setGameMode(GameMode.CREATIVE);
						((Player) s).sendMessage(ChatColor.GREEN + "You gamemode is now creative!");
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
				else
				{
					s.sendMessage("/creative [player]");
				}
			}
			else
			{

				Player target = Bukkit.getServer().getPlayer(args[0]);
				
				if(s instanceof Player)
				{
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.creative.others"))
					{
						if(target != null)
						{
							target.setGameMode(GameMode.CREATIVE);
							((Player) s).sendMessage(ChatColor.GREEN + target.getName() + "'s gamemode is now Creative!");
						}
						else
						{
							((Player) s).sendMessage(BMCommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
				else
				{
					if(target != null)
					{
						target.setGameMode(GameMode.CREATIVE);
						s.sendMessage(ChatColor.GREEN + target.getName() + "'s gamemode is now creative!");
					}
					else
					{
						s.sendMessage(BMCommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("survival"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.survival"))
					{
						((Player) s).setGameMode(GameMode.SURVIVAL);
						((Player) s).sendMessage(ChatColor.GREEN + "Your gamemode is now survival!");
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
				else
				{
					s.sendMessage("/survival [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.survival.others"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							target.setGameMode(GameMode.SURVIVAL);
							((Player) s).sendMessage(ChatColor.GREEN + target.getName() + "'s gamemode is now Survival!");
						}
						else
						{
							((Player) s).sendMessage(BMCommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.setGameMode(GameMode.SURVIVAL);
						s.sendMessage(ChatColor.GREEN + target.getName() + "'s gamemode is now survival!");
					}
					else
					{
						s.sendMessage(BMCommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		return false;
	}

}
