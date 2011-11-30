package main.java.me.cain.commandbin.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import main.java.me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModerationCommands implements CommandExecutor
{
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		// if(legohouse) <- .. where I typed it. 
		
		if(l.equalsIgnoreCase("kick"))
		{
			if(args.length < 2)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/kick [player] [reason]");
				}
			}
			else
			{

				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.kick"))
					{
						if(target != null)
						{
							target.kickPlayer(args[1].toString());
							if(CommandBin.plugin.getConfig().get("settings.broadcastkick").equals(true))
							{
								Bukkit.getServer().broadcastMessage(ChatColor.RED + ((Player) s).getName() + " has kicked " + target.getName());
							}
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					if(target != null)
					{
						target.kickPlayer(args[1]);
						if(CommandBin.plugin.getConfig().get("settings.broadcastkick").equals(true))
						{
							Bukkit.getServer().broadcastMessage(ChatColor.RED + ((Player) s).getName() + " has kicked " + target.getName());
						}
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("ban"))
		{
			if(args.length < 2)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player] [reason]");
				}
				else
				{
					s.sendMessage("/ban [player] [reason]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.ban"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							((Player) s).sendMessage("You banned " + target.getName());
							target.kickPlayer(args[1]);
							CommandBin.plugin.getConfig().set(target.getName() + ".banned", true);
							CommandBin.plugin.getConfig().set(target.getName() + ".banreason", args[1]);
							CommandBin.plugin.saveConfig();
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						s.sendMessage("You banned " + target.getName());
						target.kickPlayer(args[1]);
						CommandBin.plugin.getConfig().set(target.getName() + ".banned", true);
						CommandBin.plugin.getConfig().set(target.getName() + ".banreason", args[1]);
						CommandBin.plugin.saveConfig();
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unban"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/unban [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.unban"))
					{
						((Player) s).sendMessage(ChatColor.GREEN + ((Player) s).getName() + " successfully unbanned " + args[0] + "!");
						CommandBin.plugin.getConfig().set(args[0] + ".banned", false);
						CommandBin.plugin.getConfig().set(args[0] + ".banreason", null);
						CommandBin.plugin.saveConfig();
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					s.sendMessage(ChatColor.GREEN + "Unbanned " + args[0] + "!");
					CommandBin.plugin.getConfig().set(args[0] + ".banned", false);
					CommandBin.plugin.saveConfig();
				}
			}
		}
		
		if(l.equalsIgnoreCase("freeze"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/freeze [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.freeze"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							CommandBin.plugin.getConfig().set(target.getName() + ".frozen", true);
							((Player) s).sendMessage(ChatColor.GREEN + target.getName() + " has been frozen!");
							target.sendMessage(ChatColor.GREEN + "You have been frozen by " + ((Player) s).getName());
							CommandBin.plugin.saveConfig();
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".frozen", true);
						s.sendMessage(ChatColor.GREEN + "You froze " + target.getName());
						CommandBin.plugin.saveConfig();
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unfreeze"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/unfreeze [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.freeze"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							CommandBin.plugin.getConfig().set(target.getName() + ".frozen", false);
							((Player) s).sendMessage(ChatColor.GREEN + "You have unfrozen " + target.getName());
							target.sendMessage(ChatColor.GREEN + ((Player) s).getName() + " has unfrozen you.");
							CommandBin.plugin.saveConfig();
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".frozen", false);
						CommandBin.plugin.saveConfig();
						s.sendMessage(ChatColor.GREEN + "You have unfrozen " + target.getName());
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("handicap"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/handicap [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.handicap"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							CommandBin.plugin.getConfig().set(target.getName() + ".handicapped", true);
							CommandBin.plugin.saveConfig();
							((Player) s).sendMessage(ChatColor.GREEN + target.getName() + " has been handicapped!");
							target.sendMessage(ChatColor.GREEN + ((Player) s).getName() + " handicapped you!");
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".handicapped", true);
						CommandBin.plugin.saveConfig();
						s.sendMessage(ChatColor.GREEN + "You have disabled " + target.getName() + "'s usage of commands!");
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unhandicap"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/unhandicap [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.handicap"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							CommandBin.plugin.getConfig().set(target.getName() + ".handicapped", false);
							CommandBin.plugin.saveConfig();
							((Player) s).sendMessage(ChatColor.GREEN + target.getName() + " has been unhandicapped!");
							target.sendMessage(ChatColor.GREEN + ((Player) s).getName() + " unhanddicapped you!");
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".handicapped", false);
						CommandBin.plugin.saveConfig();
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("mute"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/mute [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.mute"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							CommandBin.plugin.getConfig().set(target.getName() + ".muted", true);
							((Player) s).sendMessage(ChatColor.GREEN + "You have muted " + target.getName());
							target.sendMessage(ChatColor.GREEN + ((Player) s).getName() + " has muted you!");
							CommandBin.plugin.saveConfig();
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".muted", true);
						s.sendMessage(ChatColor.GREEN + "You muted " + target.getName() + "!");
						CommandBin.plugin.saveConfig();
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unmute"))
		{
			
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/unmute [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.unmute"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							CommandBin.plugin.getConfig().set(target.getName() + ".muted", false);
							CommandBin.plugin.saveConfig();
							((Player) s).sendMessage(ChatColor.GREEN + "You have unmuted " + target.getName());
							target.sendMessage(ChatColor.GREEN + ((Player) s).getName() + " has unmuted you!");
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".muted", false);
						CommandBin.plugin.saveConfig();
						s.sendMessage(ChatColor.GREEN + "You unmuted " + target.getName());
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("who"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/who [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.who"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							((Player) s).sendMessage(ChatColor.AQUA + "Information on: " + target.getDisplayName());
							((Player) s).sendMessage(ChatColor.GREEN + "Real Name: " + ChatColor.WHITE + target.getName());
							((Player) s).sendMessage(ChatColor.GREEN + "Current World: " + ChatColor.WHITE + target.getWorld().getName());
							((Player) s).sendMessage(ChatColor.GREEN + "IP Address: " + ChatColor.WHITE + target.getAddress());
							((Player) s).sendMessage(ChatColor.GREEN + "Hostname: " + ChatColor.WHITE + target.getAddress().getHostName());
							((Player) s).sendMessage(ChatColor.GREEN + "Co-Ordinates: " + ChatColor.RED + target.getLocation().getX() + ", " + target.getLocation().getY() + ", " + target.getLocation().getZ());
							if(!CommandBin.plugin.getServer().getVersion().contains("1337"))
							{
								((Player) s).sendMessage("This server is not using the RB version of CraftBukkit. (1337)");
							}
							else
							{
								((Player) s).sendMessage("This server is using the RB version of CraftBukkit (1337)");
							}
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						s.sendMessage(ChatColor.AQUA + "Information on: " + target.getDisplayName());
						s.sendMessage(ChatColor.GREEN + "Real Name: " + ChatColor.WHITE + target.getName());
						s.sendMessage(ChatColor.GREEN + "Current World: " + ChatColor.WHITE + target.getWorld().getName());
						s.sendMessage(ChatColor.GREEN + "IP Address: " + ChatColor.WHITE + target.getAddress());
						s.sendMessage(ChatColor.GREEN + "Hostname: " + ChatColor.WHITE + target.getAddress().getHostName());
						s.sendMessage(ChatColor.GREEN + "Co-Ordinates: " + ChatColor.RED + target.getLocation().getX() + ", " + target.getLocation().getY() + ", " + target.getLocation().getZ());
						if(!CommandBin.plugin.getServer().getVersion().contains("1337"))
						{
							s.sendMessage("This server is not using the RB version of CraftBukkit. (1337)");
						}
						else
						{
							s.sendMessage("This server is using the RB version of CraftBukkit (1337)");
						}
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("paid"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/paid [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.paid"))
					{
						((Player) s).sendMessage(ChatColor.GREEN + args[0] + " has paid: ");
						hasPaid(s, args[0].toString());
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					s.sendMessage(ChatColor.GREEN + args[0] + " has paid: ");
					hasPaid((CommandSender) s, args[0].toString());
				}
			}
		}
		
		if(l.equalsIgnoreCase("banip"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/banip [player]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.banip"))
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if(target != null)
						{
							String ip2 = target.getAddress().getAddress().getHostAddress().toString();
							String ipworking = ip2.replace(".", "");
							
							CommandBin.plugin.getConfig().set("bannedips." + ipworking, true);
							CommandBin.plugin.saveConfig();
							target.kickPlayer("Your IP address has been banned!");
							((Player) s).sendMessage(ChatColor.GREEN + "IP " + target.getAddress() + " has been banned!");
						}
						else
						{
							((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
						}
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						String ip2 = target.getAddress().getAddress().getHostAddress().toString();
						String ipworking = ip2.replace(".", "");
						
						CommandBin.plugin.getConfig().set("bannedips." + ipworking, true);
						CommandBin.plugin.saveConfig();
						target.kickPlayer("Your IP address has been banned!");
						s.sendMessage(ChatColor.GREEN + "IP " + target.getAddress() + " has been banned!");
					}
					else
					{
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("unbanip"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					((Player) s).sendMessage("/" + l.toString() + " [ip]");
				}
				else
				{
					s.sendMessage("/unbanip [ip]");
				}
			}
			else
			{
				if(s instanceof Player)
				{
					if(CommandBin.plugin.pCheck((Player) s, "CommandBin.general.unbanip"))
					{
						CommandBin.plugin.getConfig().set("bannedips." + args[0], false);
						((Player) s).sendMessage(ChatColor.GREEN + "IP " + args[0] + " unbanned!");
						CommandBin.plugin.saveConfig();
					}
					else
					{
						((Player) s).sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				else
				{
					CommandBin.plugin.getConfig().set("bannedips." + args[0], false);
					s.sendMessage(ChatColor.GREEN + "IP \"" + args[0] + "\" unbanned!");
					CommandBin.plugin.saveConfig();
				}
			}
		}
		
		return false;
	}
	
	public void hasPaid(Player p, String user)
	{
		try {
		    URL url = new URL("http://minecraft.net/haspaid.jsp?user=" + user);

		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String str;
		    while ((str = in.readLine()) != null) {
		      p.sendMessage(str);
		    }
		    in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}
	
	public void hasPaid(CommandSender p, String user)
	{
		try {
		    URL url = new URL("http://minecraft.net/haspaid.jsp?user=" + user);

		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String str;
		    while ((str = in.readLine()) != null) {
		      p.sendMessage(str);
		    }
		    in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}
	

}
