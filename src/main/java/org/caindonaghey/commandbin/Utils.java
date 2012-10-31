package org.caindonaghey.commandbin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class Utils {
	
	public static void printLine(String message) {
		System.out.println("[CommandBin] " + message);
	}
	
	public static void registerCommand(String command, CommandExecutor commandexecutor) {
		Bukkit.getServer().getPluginCommand(command).setExecutor(commandexecutor);
	}
	
	public static void consoleSender() {
		printLine("You must be a player to issue this command.");
	}
	
	public static void playerMessage(Player player, String message) {
		player.sendMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "CMDBin" + ChatColor.YELLOW + "] " + ChatColor.DARK_AQUA + message);
	}
	
	public static void noArguments(Player player) {
		playerMessage(player, "Invalid number of arguments.");
	}
	
	public static boolean checkPermission(Player player, String permission) {
		if(player.hasPermission(permission)) {
			return true;
		}
		return false;
	}
	
	public static void invalidPermission(Player player) {
		playerMessage(player, ChatColor.RED + "You have no permission to run this command.");
	}
	
	public static void errorMessage(Player player, String message) {
		playerMessage(player, ChatColor.RED + message);
	}
	
	public static void broadcastMessage(String message) {
		Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + message);
	}

}
