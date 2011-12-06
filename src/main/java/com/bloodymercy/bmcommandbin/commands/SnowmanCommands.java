package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SnowmanCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		if(s instanceof Player)
		{
			if(l.equalsIgnoreCase("snowman"))
			{
				if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.snowman"))
				{
					BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".snowman", true);
					((Player) s).sendMessage(ChatColor.GREEN + "You are now Frosty The Snowman! Start walking!");
					BMCommandBin.plugin.saveConfig();
				}
				else
				{
					((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
				}
			}
			
			if(l.equalsIgnoreCase("unsnowman"))
			{
				if(BMCommandBin.plugin.pCheck((Player) s, "CommandBin.general.snowman"))
				{
					BMCommandBin.plugin.getConfig().set(((Player) s).getName() + ".snowman", false);
					((Player) s).sendMessage(ChatColor.GREEN + "You are no longer Frosty The Snowman. :(");
					BMCommandBin.plugin.saveConfig();
				}
				else
				{
					((Player) s).sendMessage(BMCommandBin.plugin.NoPermission);
				}
			}
		}
		else
		{
			s.sendMessage(ChatColor.RED + "You can't use snowman commands in-console!");
		}
		return false;
		
		
	}

}
