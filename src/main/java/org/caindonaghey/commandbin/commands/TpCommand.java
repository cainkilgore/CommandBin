package org.caindonaghey.commandbin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.caindonaghey.commandbin.Utils;

public class TpCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		
		if(l.equalsIgnoreCase("tp")) {
			if(!(s instanceof Player)) {
				if(args.length < 1) {
					Utils.printLine("Applicable Arguments: /tp player player | /tp player x y z");
					return true;
				}
				
				if(args.length == 1) {
					Utils.printLine("You can't teleport to other players.");
					return true;
				}
				
				if(args.length == 2) {
					Player onePlayer = Bukkit.getServer().getPlayer(args[0]);
					Player otherPlayer = Bukkit.getServer().getPlayer(args[1]);
					
					if(onePlayer == null) {
						Utils.printLine(args[0] + " is currently offline. Try again?");
						return true;
					}
					
					if(otherPlayer == null) {
						Utils.printLine(args[1] + " is currently offline. Try again?");
						return true;
					}
					
					onePlayer.teleport(otherPlayer.getLocation());
					Utils.printLine("Teleported " + onePlayer.getName() + " to " + otherPlayer.getName());
				}
				
				if(args.length == 3) {
					Utils.printLine("You can't teleport to co-ordinates.");
					return true;
				}
				
				if(args.length == 4) {
					Player thePlayer = Bukkit.getServer().getPlayer(args[0]);
					
					try {
					int coOrdX = Integer.parseInt(args[1]);
					int coOrdY = Integer.parseInt(args[2]);
					int coOrdZ = Integer.parseInt(args[3]);
					
					if(thePlayer == null) {
						Utils.printLine(args[0] + " is currently offline. Try again?");
						return true;
					}
					
						thePlayer.teleport(new Location(thePlayer.getWorld(), coOrdX, coOrdY, coOrdZ));
						Utils.printLine("Teleported " + thePlayer.getName() + " to co-ordinates " + coOrdX + ", " + coOrdY + ", " + coOrdZ);
					} catch (NumberFormatException e) {
						Utils.printLine("Error teleporting to co-ordinates. Please check you used real numbers.");
					}
					return true;
				}
				return true;
			}
		//  cannot cast. lol wat	
			Player player = (Player) s; 
			
			if(args.length < 1) {
				Utils.noArguments(player);
				return false;
			}
			
			if(args.length == 1) {
				if(!Utils.checkPermission(player, "CommandBin.tp")) {
					Utils.invalidPermission(player);
					return true;
				}
				
				Player otherPlayer = Bukkit.getServer().getPlayer(args[0]);
				
				if(otherPlayer == null) {
					Utils.errorMessage(player, args[0] + " is currently offline. Try again?");
					return true;
				}
				
				player.teleport(otherPlayer.getLocation());
				Utils.playerMessage(player, "Teleported to " + otherPlayer.getName());
			}
			
			if(args.length == 2) {
				if(!Utils.checkPermission(player, "CommandBin.tp.others")) {
					Utils.invalidPermission(player);
					return true;
				}
				
				Player onePlayer = Bukkit.getServer().getPlayer(args[0]);
				Player otherPlayer = Bukkit.getServer().getPlayer(args[1]);
				
				if(onePlayer == null) {
					Utils.errorMessage(player, args[0] + " is currently offline. Try again?");
					return true;
				}
				
				if(otherPlayer == null) {
					Utils.errorMessage(player, args[1] + " is currently offline. Try again?");
					return true;
				}
				
				onePlayer.teleport(otherPlayer.getLocation());
				Utils.playerMessage(player, "Teleported " + onePlayer.getName() + " to " + otherPlayer.getName());
			}
			
			if(args.length == 3) {
				if(!Utils.checkPermission(player, "CommandBin.tp.coords")) {
					Utils.invalidPermission(player);
					return true;
				}
				try {
					int coOrdX = Integer.parseInt(args[0]);
					int coOrdY = Integer.parseInt(args[1]);
					int coOrdZ = Integer.parseInt(args[2]);
					player.teleport(new Location(player.getWorld(), coOrdX, coOrdY, coOrdZ));
					Utils.playerMessage(player, "Teleported to coordinates: " + coOrdX + ", " + coOrdY + ", " + coOrdZ);
				} catch (NumberFormatException e) {
					Utils.errorMessage(player, "Error teleporting " + player.getName() + " to co-ordinates specified. Please check you used real numbers.");
				}
			}
			
			if(args.length == 4) {
				if(!Utils.checkPermission(player, "CommandBin.tp.otherstocoords")) {
					Utils.invalidPermission(player);
					return true;
				}
				
				Player thePlayer = Bukkit.getServer().getPlayer(args[0]);
				int coOrdX = Integer.parseInt(args[1]);
				int coOrdY = Integer.parseInt(args[2]);
				int coOrdZ = Integer.parseInt(args[3]);
				
				if(thePlayer == null) {
					Utils.errorMessage(player, args[0] + " is currently offline. Try again?");
					return true;
				}
				
				try {
					thePlayer.teleport(new Location(thePlayer.getWorld(), coOrdX, coOrdY, coOrdZ));
					Utils.playerMessage(player, "Teleported " + thePlayer.getName() + " to co-ordinates " + coOrdX + ", " + coOrdY + ", " + coOrdZ);
				} catch (NumberFormatException e) {
					Utils.errorMessage(player, "Error teleporting to co-ordinates. Please check you used real numbers.");
				}
				
			}
		}
		
		return true;
	}

}