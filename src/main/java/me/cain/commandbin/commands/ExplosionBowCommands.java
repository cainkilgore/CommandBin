package main.java.me.cain.commandbin.commands;

import main.java.me.cain.commandbin.CommandBin;

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
					if(CommandBin.plugin.pCheck(((Player) s), "CommandBin.general.explosionbow"))
					{
						if(args[0].equalsIgnoreCase("on"))
						{
							CommandBin.plugin.getConfig().set(((Player) s).getName() + ".explosionbow", true);
							((Player) s).sendMessage(ChatColor.AQUA + "Explosion Bow Enabled!");
							((Player) s).sendMessage("Shoot with a bow as normal!");
							CommandBin.plugin.saveConfig();
						}
						
						if(args[0].equalsIgnoreCase("off"))
						{
							CommandBin.plugin.getConfig().set(((Player) s).getName() + ".explosionbow", false);
							((Player) s).sendMessage(ChatColor.AQUA + "Explosion Bow Disabled!");
							CommandBin.plugin.saveConfig();
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
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
