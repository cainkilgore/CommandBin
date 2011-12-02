package main.java.me.cain.commandbin;

import main.java.me.cain.commandbin.commands.CommandRegistration;
import main.java.me.cain.commandbin.config.ConfigSetup;
import main.java.me.cain.commandbin.listeners.EventRegistration;
import main.java.me.cain.commandbin.statistics.CallHome;
import main.java.me.cain.commandbin.statistics.Statistics;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class CommandBin extends JavaPlugin {
	
	public String Plugin = "[CommandBin] ";
	public static CommandBin plugin;
	public static PermissionHandler permissionHandler;
	public String NoPermission = ChatColor.GRAY + "You have no permission to use this command.";
	public String PlayerOffline = ChatColor.RED + "This player is offline";
	
	public void onEnable()
	{
		plugin = this;
		setupCommandBin();
		System.out.println(Plugin + "Enabled successfully.");
		System.out.println(Plugin + "You are running version: v" + this.getDescription().getVersion());
	}
	
	public void setupCommandBin()
	{
		EventRegistration.setup();
		setupPermissions();
		CommandRegistration.SetupCommands();
		ConfigSetup.start();
		// RegisterRecipes();
		Statistics.StartStats();
		CallHome.init(this);
	}
	
	public void onDisable()
	{
		System.out.println(Plugin + "Disabled successfully.");
	}
	
	public boolean pCheck(Player player, String l)
	{
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    if (permissionsPlugin == null) {
	    	return player.hasPermission(l) || player.isOp();
	    }
	    else
	    {
	    	return permissionHandler.has(player, l);
	    }
	}
	
	private void setupPermissions() {
	    if (permissionHandler != null) {
	        return;
	    }
	    
	    Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        System.out.println(Plugin + "I could not detect any Permissions on your server. Defaulting to OP!");
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    System.out.println(Plugin + "Permissions Plugin Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName() + "!");
	}
}