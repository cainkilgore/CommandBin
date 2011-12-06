package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SayCommand implements CommandExecutor
{
	
	String console = CommandBin.plugin.getConfig().getString("settings.consolename");
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("say"))
		{
			if(args.length < 1)
			{
				s.sendMessage("/say [message]");
			} else {
				// Say stuff.
				StringBuilder x = new StringBuilder();
				int i;
				for(i = 0; i < args.length; i++)
				{
					x.append(args[i] + " ");
				}
				
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.say"))
					{
						Bukkit.getServer().broadcastMessage("<" + ChatColor.RED + console + ChatColor.WHITE + "> " + ChatColor.WHITE + x.toString().trim());
					}
					else
					{
						s.sendMessage(CommandBin.plugin.NoPermission);
					}
				} else {
					Bukkit.getServer().broadcastMessage("<" + ChatColor.RED + console + ChatColor.WHITE + "> " + ChatColor.WHITE + x.toString().trim());
				}
			}
		}
		return false;
	}
}