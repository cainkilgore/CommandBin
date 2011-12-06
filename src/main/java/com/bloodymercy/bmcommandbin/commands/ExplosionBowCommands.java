package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExplosionBowCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(l.equalsIgnoreCase("explosionbow"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [on/off]");
				}
				else
				{
					s.sendMessage("/explosionbow [on/off]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(BMCommandBin.plugin.pCheck(((Player) s), "CommandBin.general.explosionbow"))
					{
						if(args[0].equalsIgnoreCase("on"))
						{
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".explosionbow", true);
							((Player) s).sendMessage(ChatColor.AQUA + "Explosion Bow Enabled!");
							((Player) s).sendMessage("Shoot with a bow as normal!");
							BMCommandBin.plugin.saveConfig();
						}
						
						if(args[0].equalsIgnoreCase("off"))
						{
							BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".explosionbow", false);
							((Player) s).sendMessage(ChatColor.AQUA + "Explosion Bow Disabled!");
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
					s.sendMessage(ChatColor.RED + "You can't use the explosion bow in-console!");
				}
			}
		}
		return false;
	}

}
