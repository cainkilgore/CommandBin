package com.bloodymercy.bmcommandbin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommands implements CommandExecutor
{

	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{	
		if(l.equalsIgnoreCase("ping"))
		{
			if(s instanceof Player)
			{
				((Player) s).sendMessage(ChatColor.GREEN + "Pong!");
			}
			else
			{
				s.sendMessage(ChatColor.GREEN + "PONG!");
			}
		}
		return false;
	}
}
