package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TorchBowCommands implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(s instanceof Player)
		{
			if(l.equalsIgnoreCase("torchbow"))
			{
				if(args.length < 1)
				{
					((Player) s).sendMessage("/" + l.toString() + " [on/off]");
				}
				else
				{
					if(BMCommandBin.plugin.pCheck(((Player) s), "Commandbin.general.torchbow"))
					{
						if(args[0].equalsIgnoreCase("on"))
						{
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".torchbow", true);
							BMCommandBin.plugin.saveConfig();
							((Player) s).sendMessage(ChatColor.GREEN + "Torchbow has been enabled.");
							((Player) s).sendMessage(ChatColor.GREEN + "Shoot with a bow as normal!");
						}
						
						if(args[0].equalsIgnoreCase("off"))
						{
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".torchbow", false);
							BMCommandBin.plugin.saveConfig();
							((Player) s).sendMessage(ChatColor.GREEN + "Torchbow has been disabled.");
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
			s.sendMessage(ChatColor.RED + "You can't use torchbow commands in-console!");
		}
		return false;
	}

}
