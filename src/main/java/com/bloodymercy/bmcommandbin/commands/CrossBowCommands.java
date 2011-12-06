package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrossBowCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(l.equalsIgnoreCase("crossbow"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [on/off]");
				}
				else
				{
					s.sendMessage("/crossbow [on/off]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.crossbow"))
					{
						if(args[0].equalsIgnoreCase("on"))
						{
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".crossbow", true);
							((Player) s).sendMessage(ChatColor.AQUA + "Crossbow enabled!");
							((Player) s).sendMessage("Shoot with a bow!");
							BMCommandBin.plugin.saveConfig();
						}
						if(args[0].equalsIgnoreCase("off"))
						{
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".crossbow", false);
							((Player) s).sendMessage(ChatColor.RED + "Crossbow disabled!");
							BMCommandBin.plugin.saveConfig();
						}
					}
					else
					{
						((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
					}
				}
				else
				{
					s.sendMessage(ChatColor.RED + "You can't use the crossbow in-console!");
				}
			}
		}
		return false;
	}

}
