package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeJoinLeaveCommands implements CommandExecutor
{

	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(l.equalsIgnoreCase("join"))
		{
			if(s instanceof Player)
			{
				if(args.length > 0) {
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.join"))
					{
						Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + args[0] + " has joined the game");
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				} else {
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.join"))
					{
						Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + ((Player) s).getName() + " has joined the game");
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
				
			}
			else
			{
				s.sendMessage(ChatColor.RED + "You can't use fake join/leave in-console!");
			}
		}
		
		if(l.equalsIgnoreCase("leave"))
		{
			if(s instanceof Player)
			{
				if(args.length > 0) {
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.leave"))
					{
						Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + args[0] + " has left the game");
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				} else {
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.leave"))
					{
						Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + ((Player) s).getName() + " has left the game");
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
			}
			else
			{
				s.sendMessage(ChatColor.RED + "You can't use fake join/leave in-console!");
			}
		}
		return false;
	}

}
