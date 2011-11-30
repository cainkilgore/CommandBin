package main.java.me.cain.commandbin.commands;

import main.java.me.cain.commandbin.CommandBin;

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
					if(CommandBin.plugin.pCheck(((Player) s), "Commandbin.general.torchbow"))
					{
						if(args[0].equalsIgnoreCase("on"))
						{
							CommandBin.plugin.getConfig().set(((Player) s).getName() + ".torchbow", true);
							CommandBin.plugin.saveConfig();
							((Player) s).sendMessage(ChatColor.GREEN + "Torchbow has been enabled.");
							((Player) s).sendMessage(ChatColor.GREEN + "Shoot with a bow as normal!");
						}
						
						if(args[0].equalsIgnoreCase("off"))
						{
							CommandBin.plugin.getConfig().set(((Player) s).getName() + ".torchbow", false);
							CommandBin.plugin.saveConfig();
							((Player) s).sendMessage(ChatColor.GREEN + "Torchbow has been disabled.");
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
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
