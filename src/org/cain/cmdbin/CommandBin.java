package org.cain.cmdbin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.cain.cmdbin.commands.AdminCommands;
import org.cain.cmdbin.commands.BanCommands;
import org.cain.cmdbin.commands.FakeSayCommand;
import org.cain.cmdbin.commands.FreezeCommands;
import org.cain.cmdbin.commands.GameModeCommands;
import org.cain.cmdbin.commands.GodCommands;
import org.cain.cmdbin.commands.HandicapCommands;
import org.cain.cmdbin.commands.HomeCommands;
import org.cain.cmdbin.commands.JoinandLeaveCommands;
import org.cain.cmdbin.commands.KickCommands;
import org.cain.cmdbin.commands.MsgCommands;
import org.cain.cmdbin.commands.MuteCommands;
import org.cain.cmdbin.commands.NickCommands;
import org.cain.cmdbin.commands.PaidCommand;
import org.cain.cmdbin.commands.PingCommand;
import org.cain.cmdbin.commands.PlayerCommands;
import org.cain.cmdbin.commands.SayCommand;
import org.cain.cmdbin.commands.SpawnMobCommands;
import org.cain.cmdbin.commands.SpoutCommands;
import org.cain.cmdbin.commands.TeleportCommands;
import org.cain.cmdbin.commands.TimeCommands;
import org.cain.cmdbin.commands.WarpCommands;
import org.cain.cmdbin.commands.WeatherCommands;
import org.cain.cmdbin.commands.WhoCommands;
import org.cain.cmdbin.commands.WorldCommands;
import org.cain.cmdbin.commands.XPCommands;
import org.cain.cmdbin.listeners.CMDBinEListener;
import org.cain.cmdbin.listeners.CMDBinPListener;
import org.cain.cmdbin.listeners.CMDBinSListener;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class CommandBin extends JavaPlugin{
	
	public String PLAYER_OFFLINE = "This player is currently offline.";
	public String NULL_PERMISSION = "You have no permission to use this command.";
	public String CONSOLE_SENDER = "You must be in-game to use this command.";
	public static PermissionHandler permissionHandler;
	public static CommandBin cmdbin;
	public static Configuration cfg;
	
	public void onEnable() {
		cmdbin = this;
		EventReg();
		setupPermissions();
		ConsoleMessage("Successfully enabled CommandBin!");
		RegisterCommands(true);
		cfg = getConfig();
		cfg.addDefault("settings.landmines", false);
		cfg.options().copyDefaults();
		saveConfig();
	}
	public void onDisable() {
		saveConfig();
		ConsoleMessage("Successfully disabled CommandBin!");
	}
	public boolean pCheck(Player s, String node) {
		if(permissionHandler != null) {
			return permissionHandler.has(s,  node);
		} else {
			return s.hasPermission(node);
		}
	}
	
	public void EventReg() {
		re(Type.PLAYER_MOVE, new CMDBinPListener());
		re(Type.ENTITY_DAMAGE, new CMDBinEListener());
		re(Type.PLAYER_CHAT, new CMDBinPListener());
		re(Type.PLAYER_COMMAND_PREPROCESS, new CMDBinPListener());
		if(getServer().getPluginManager().getPlugin("Spout") != null) {
			re(Type.CUSTOM_EVENT, new CMDBinSListener());
		}
	}

	public void re(Type type, Listener d) {
		Bukkit.getServer().getPluginManager().registerEvent(type, d, Priority.Normal, cmdbin);
	}
	
	public void ConsoleMessage(String message) {
		System.out.println("[CommandBin] " + message);
	}
	
	public void PlayerMessage(Player p, String message) {
		p.sendMessage(ChatColor.AQUA + message);
	}
	
	public void Broadcast(String message) {
		Bukkit.getServer().broadcastMessage(message);
	}
	
	public boolean RegisterCommands(boolean b) {
		
		// All the executors. Yay!
		CommandExecutor spt = new SpoutCommands();
		CommandExecutor god = new GodCommands();
		CommandExecutor adm = new AdminCommands();
		CommandExecutor fsa = new FakeSayCommand();
		CommandExecutor ban = new BanCommands();
		CommandExecutor fre = new FreezeCommands();
		CommandExecutor gmc = new GameModeCommands();
		CommandExecutor hdc = new HandicapCommands();
		CommandExecutor hme = new HomeCommands();
		CommandExecutor jal = new JoinandLeaveCommands();
		CommandExecutor kck = new KickCommands();
		CommandExecutor msg = new MsgCommands();
		CommandExecutor mut = new MuteCommands();
		CommandExecutor nck = new NickCommands();
		CommandExecutor pad = new PaidCommand();
		CommandExecutor png = new PingCommand();
		CommandExecutor pco = new PlayerCommands(); // Biggest class
		CommandExecutor say = new SayCommand();
		CommandExecutor smc = new SpawnMobCommands();
		CommandExecutor tpc = new TeleportCommands();
		CommandExecutor tmc = new TimeCommands();
		CommandExecutor wrp = new WarpCommands();
		CommandExecutor wco = new WeatherCommands();
		CommandExecutor who = new WhoCommands();
		CommandExecutor wld = new WorldCommands();
		CommandExecutor xpc = new XPCommands();
		
		if(b = true) {
			registerCommand("godon", god);
			registerCommand("godoff", god);
			registerCommand("shutdown", adm);
			registerCommand("cmdbin", adm);
			registerCommand("ban", ban);
			registerCommand("unban", ban);
			registerCommand("fsay", fsa);
			registerCommand("freeze", fre);
			registerCommand("unfreeze", fre);
			registerCommand("survival", gmc);
			registerCommand("creative", gmc);
			registerCommand("handicap", hdc);
			registerCommand("unhandicap", hdc);
			registerCommand("home", hme);
			registerCommand("sethome", hme);
			registerCommand("join", jal);
			registerCommand("leave", jal);
			registerCommand("kick", kck);
			registerCommand("msg", msg);
			registerCommand("m", msg);
			registerCommand("mute", mut);
			registerCommand("unmute", mut);
			registerCommand("nick", nck);
			registerCommand("paid", pad);
			registerCommand("ping", png);
			registerCommand("shoot", pco);
			registerCommand("strike", pco);
			registerCommand("heal", pco);
			registerCommand("feed", pco);
			registerCommand("explode", pco);
			registerCommand("light", pco);
			registerCommand("slap", pco);
			registerCommand("clear", pco);
			registerCommand("kill", pco);
			registerCommand("me", pco);
			registerCommand("loc", pco);
			registerCommand("say", say);
			registerCommand("spawnmob", smc);
			registerCommand("tp", tpc);
			registerCommand("tphere", tpc);
			registerCommand("tp2p", tpc);
			registerCommand("spawn", tpc);
			registerCommand("setspawn", tpc);
			registerCommand("tpall", tpc);
			registerCommand("put", tpc);
			registerCommand("time", tmc);
			registerCommand("mytime", tmc);
			registerCommand("warp", wrp);
			registerCommand("setwarp", wrp);
			registerCommand("delwarp", wrp);
			registerCommand("weather", wco);
			registerCommand("who", who);
			registerCommand("createworld", wld);
			registerCommand("tpworld", wld);
			registerCommand("unloadworld", wld);
			registerCommand("xp", xpc);
			if(Bukkit.getServer().getPluginManager().getPlugin("Spout") != null) { // Derr Spout Commands
				registerCommand("sskin", spt);
				registerCommand("scape", spt);
				registerCommand("snick", spt);
			}
		}
		return false;
	}
	
	public void registerCommand(String commandname, CommandExecutor executorclass) {
		getServer().getPluginCommand(commandname).setExecutor(executorclass);
	}
	
	private void setupPermissions() {
		if (permissionHandler != null) {
			return;
		}

		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

		if (permissionsPlugin == null) {
			System.out.println("[CommandBin] I could not detect any Permissions on your server. Defaulting to OP!");
			return;
		}

		permissionHandler = ((Permissions) permissionsPlugin).getHandler();
		System.out.println("[CommandBin] Permissions Plugin Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName() + "!");
	}
	
	
	
	
}