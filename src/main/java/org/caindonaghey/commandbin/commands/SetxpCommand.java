package org.caindonaghey.commandbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.caindonaghey.commandbin.Utils;

public class SetxpCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("setxp")) {
			if(!(s instanceof Player)) {
				if(args.length < 1) {
					Utils.printLine("You must be in-game to give yourself experience.");
					Utils.printLine("Applicable Arguments: /setxp [player] <experience>");
					return true;
				}
				
				Player player = Bukkit.getServer().getPlayer(args[0]);
				if(player == null) {
					Utils.printLine(args[0] + " is offline. Try again?");
					return true;
				}
				
				try {
					player.setLevel(Integer.parseInt(args[1]));
					Utils.printLine(player.getName() + "'s level has been set to " + args[1]);
				} catch (NumberFormatException e) {
					Utils.printLine("You can't give " + player.getName() + " " + args[1] + " experience.");
				}
				return true;
			}
			
			Player player = (Player) s;
			
			if(!Utils.checkPermission(player, "CommandBin.setxp")) {
				Utils.invalidPermission(player);
				return true;
			}
			
			if(args.length < 1) {
				Utils.noArguments(player);
				return false;
			}
			
			if(args.length == 1) {
				try {
					player.setLevel(Integer.parseInt(args[0]));
					Utils.playerMessage(player, "Your experience level has been set to " + args[0]);
				} catch (NumberFormatException e) {
					Utils.playerMessage(player, "You can't set your experience level to " + args[0]);
				}
				return true;
			}
			
			Player otherPlayer = Bukkit.getServer().getPlayer(args[0]);
			
			if(otherPlayer == null) {
				Utils.playerMessage(player, args[0] + " is offline. Try again?");
				return true;
			}
			
			try {
				otherPlayer.setLevel(Integer.parseInt(args[1]));
				Utils.playerMessage(player, otherPlayer.getName() + "'s experience level has been set to " + args[1]);
			} catch (NumberFormatException e) {
				Utils.playerMessage(player, "You can't set " + otherPlayer.getName() + "'s experience level to " + args[1]);
			}
		}
		return true;
	}

}
