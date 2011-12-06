package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.CommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		
		if(l.equalsIgnoreCase("cbhelp"))
		{
			if(s instanceof Player)
			{
				if(args.length < 1)
				{
					((Player) s).sendMessage("/cbhelp [page-number]");
				}
				else
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.help"))
					{
						if(args[0].equalsIgnoreCase("1"))
						{
							((Player) s).sendMessage("/tp [player] - Teleport to a player");
							((Player) s).sendMessage("/tphere [player] - Teleport a player to you");
							((Player) s).sendMessage("/setspawn - Set the world spawn");
							((Player) s).sendMessage("/tpworld [world] - Teleport to a world");
							((Player) s).sendMessage("/spawn - Teleport to the world spawn");
							((Player) s).sendMessage("/tpall - Teleport all players to you");
							((Player) s).sendMessage("/createworld [world] - Create a new world");
							((Player) s).sendMessage("/unloadworld [world] - Unload a world");
						}
						if(args[0].equalsIgnoreCase("2"))
						{
							((Player) s).sendMessage("/shutdown - Shut the server down");
							((Player) s).sendMessage("/commandbin - CommandBin");
							((Player) s).sendMessage("/join - A fake join message");
							((Player) s).sendMessage("/leave - A fake leave message");
							((Player) s).sendMessage("/ping - Pong!");
							((Player) s).sendMessage("/creative - Set your mode to creative");
							((Player) s).sendMessage("/survival - Set your mode to survival");
							((Player) s).sendMessage("/shoot [player] - Shoots a player into the air");
						}
						if(args[0].equalsIgnoreCase("3"))
						{
							((Player) s).sendMessage("/time [day/night] - Set your server time");
							((Player) s).sendMessage("/rain - Make it rain");
							((Player) s).sendMessage("/sun - Make the sun shine");
							((Player) s).sendMessage("/kick [player] [reason] - Self explanotory");
							((Player) s).sendMessage("/ban [player] [reason] - Ban a player");
							((Player) s).sendMessage("/unban [player] - Unban a player");
							((Player) s).sendMessage("/feed - Feed a player to full food bar");
							((Player) s).sendMessage("/heal - Give yourself/others full health");
						}
						if(args[0].equalsIgnoreCase("4"))
						{
							((Player) s).sendMessage("/freeze [player] - Stop a player from moving");
							((Player) s).sendMessage("/unfreeze [player] - Reallow a player to move");
							((Player) s).sendMessage("/facepalm - Nuff said.");
							((Player) s).sendMessage("/god [on/off] - Enable/disable godmode");
							((Player) s).sendMessage("/snowman - Enable Snowman Mode!");
							((Player) s).sendMessage("/unsnowman - Disable Snowman Mode");
							((Player) s).sendMessage("/explode [player] - Explode a player");
							((Player) s).sendMessage("/light [player] - Set a player on fire");
						}
						if(args[0].equalsIgnoreCase("5"))
						{
							((Player) s).sendMessage("/roll [x] [y] - Roll a random number");
							((Player) s).sendMessage("/handicap [player] - Stop a player from using commands");
							((Player) s).sendMessage("/unhandicap [player] - Reallow a player to use command");
							((Player) s).sendMessage("/mute [player] - Mute a plyer");
							((Player) s).sendMessage("/unmute [player] - Unmute a player");
							((Player) s).sendMessage("/smoke [on/off] - Smoke particles around you");
							((Player) s).sendMessage("/explosionstick [on/off] - The explosion stick!");
							((Player) s).sendMessage("/lightningstick [on/off] - The lightning stick!");
						}
						if(args[0].equalsIgnoreCase("6"))
						{
							((Player) s).sendMessage("/slap [player] - Slap a player");
							((Player) s).sendMessage("/who [player] - Information about a player");
							((Player) s).sendMessage("/clear - Clear your inventory");
							((Player) s).sendMessage("/spawnmob [mob] [amount] - Spawn mobs");
							((Player) s).sendMessage("/i [item] [amount] - Give yourself items");
							((Player) s).sendMessage("/msg [player] [message] - Message a player");
							((Player) s).sendMessage("/sethome [homename] - Set your home");
							((Player) s).sendMessage("/home [homename] - Teleport to your home");
						}
						if(args[0].equalsIgnoreCase("7"))
						{
							((Player) s).sendMessage("/setwarp [warp] - Set a warp");
							((Player) s).sendMessage("/warp [name] - Teleport to a warp");
							((Player) s).sendMessage("/delwarp [name] - Remove a warp");
							((Player) s).sendMessage("/nick [player] [nickname] - Set a player's nickname");
							((Player) s).sendMessage("/setxp [player] [xp] - Set your experience points");
							((Player) s).sendMessage("/armour [iron/gold/diamond/leather/chainmail] - Nuff said.");
							((Player) s).sendMessage("/kill [player] - Kill a player");
							((Player) s).sendMessage("/up [amount] - Go up [x] amount of blocks");
						}
						if(args[0].equalsIgnoreCase("8"))
						{
							((Player) s).sendMessage("/unlimited - Place unlimited blocks");
							((Player) s).sendMessage("/delunlimited - Stop placement of unlimited blocks");
							((Player) s).sendMessage("/crossbow - The crossbow");
							((Player) s).sendMessage("/tp2p - Teleport one player to another");
							((Player) s).sendMessage("/put - Put a player in the location your looking at");
							((Player) s).sendMessage("/banip - Ban a player's IP address");
							((Player) s).sendMessage("/unbanip - Unban a player's IP address");
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
			}
			else
			{
				s.sendMessage(ChatColor.RED + "You can only view the commands in-game!");
			}
		}
		return false;
	}

}
