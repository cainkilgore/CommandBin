package org.caindonaghey.commandbin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.caindonaghey.commandbin.commands.SpawnCommand;
import org.caindonaghey.commandbin.commands.TpCommand;
import org.caindonaghey.commandbin.commands.TphereCommand;
import org.caindonaghey.commandbin.listeners.EntityListener;
import org.caindonaghey.commandbin.listeners.PlayerListener;

public class CommandBin extends JavaPlugin {
	
	// Begin CommandBin Rewrite - v4
	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public void onEnable() {
		registerEvents();
		registerCommands();
		Utils.printLine("CommandBin has been enabled. Smile and be happy.");
	}
	
	public void onDisable() {
		Utils.printLine("CommandBin has been disabled. Now's the time to be sad.");
	}
	
	public void registerEvents() {
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new EntityListener(), this);
	}
	
	public void registerCommands() {
		Utils.registerCommand("tp", new TpCommand());
		Utils.registerCommand("tphere", new TphereCommand());
		Utils.registerCommand("spawn", new SpawnCommand());
	}

}
