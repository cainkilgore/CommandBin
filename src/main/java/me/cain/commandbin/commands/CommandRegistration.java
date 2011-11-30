package main.java.me.cain.commandbin.commands;

import main.java.me.cain.commandbin.CommandBin;

import org.bukkit.command.CommandExecutor;

public class CommandRegistration
{
	public static void SetupCommands()
	{
		
		// Teleportation Commands
		CommandExecutor TeleporterCommands = new PlayerCommands();
		CommandBin.plugin.getCommand("tp").setExecutor(TeleporterCommands);
		CommandBin.plugin.getCommand("tphere").setExecutor(TeleporterCommands);
		CommandBin.plugin.getCommand("tpall").setExecutor(TeleporterCommands);
		CommandBin.plugin.getCommand("tp2p").setExecutor(TeleporterCommands);
		CommandBin.plugin.getCommand("put").setExecutor(TeleporterCommands);
		CommandBin.plugin.getCommand("tpworld").setExecutor(TeleporterCommands);
		CommandBin.plugin.getCommand("spawn").setExecutor(TeleporterCommands);
		CommandBin.plugin.getCommand("setspawn").setExecutor(TeleporterCommands);
		//
		
		// World Commands
		CommandExecutor WorldCommands = new WorldCommands();
		CommandBin.plugin.getCommand("createworld").setExecutor(WorldCommands);
		CommandBin.plugin.getCommand("unloadworld").setExecutor(WorldCommands);
		//
		
		// Administration Commands
		CommandExecutor AdminCommands = new AdministrationCommands();
		CommandBin.plugin.getCommand("shutdown").setExecutor(AdminCommands);
		CommandBin.plugin.getCommand("cmdbin").setExecutor(AdminCommands);
		//
		
		// Fake Join/Leave Commands
		CommandExecutor FakeJL = new FakeJoinLeaveCommands();
		CommandBin.plugin.getCommand("join").setExecutor(FakeJL);
		CommandBin.plugin.getCommand("leave").setExecutor(FakeJL);
		//
		
		// Debug Commands
		CommandExecutor Debug = new DebugCommands();
		CommandBin.plugin.getCommand("ping").setExecutor(Debug);
		//
		
		// Game Mode Commands
		CommandExecutor GM = new GameModeCommands();
		CommandBin.plugin.getCommand("creative").setExecutor(GM);
		CommandBin.plugin.getCommand("survival").setExecutor(GM);
		//
		
		// Weather Control Commands
		CommandExecutor WeatherControl = new WeatherCommands();
		CommandBin.plugin.getCommand("rain").setExecutor(WeatherControl);
		CommandBin.plugin.getCommand("sun").setExecutor(WeatherControl);
		//
		
		// Player Commands
		CommandExecutor PlayerCommands = new PlayerCommands();
		CommandBin.plugin.getCommand("shoot").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("strike").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("heal").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("feed").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("god").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("facepalm").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("explode").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("light").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("roll").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("smoke").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("explosionstick").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("lightningstick").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("slap").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("msg").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("clear").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("kill").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("i").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("time").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("nick").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("setxp").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("up").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("unlimited").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("delunlimited").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("mytime").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("armour").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("me").setExecutor(PlayerCommands);
		CommandBin.plugin.getCommand("more").setExecutor(PlayerCommands);
		//
		
		// Snowman Commands
		CommandExecutor SnowmanCommands = new SnowmanCommands();
		CommandBin.plugin.getCommand("snowman").setExecutor(SnowmanCommands);
		CommandBin.plugin.getCommand("unsnowman").setExecutor(SnowmanCommands);
		//
		
		// Moderation Commands
		CommandExecutor ModCommands = new ModerationCommands();
		CommandBin.plugin.getCommand("kick").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("ban").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("unban").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("freeze").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("unfreeze").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("handicap").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("unhandicap").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("mute").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("unmute").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("who").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("paid").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("banip").setExecutor(ModCommands);
		CommandBin.plugin.getCommand("unbanip").setExecutor(ModCommands);
		//
		
		// Spawn Mob Commands
		CommandExecutor MobCommands = new MobCommands();
		CommandBin.plugin.getCommand("spawnmob").setExecutor(MobCommands);
		CommandBin.plugin.getCommand("killmobs").setExecutor(MobCommands);
		//
		
		// Home Commands
		CommandExecutor HomeCommands = new HomeCommands();
		CommandBin.plugin.getCommand("home").setExecutor(HomeCommands);
		CommandBin.plugin.getCommand("sethome").setExecutor(HomeCommands);
		//
		
		// Warp Commands
		CommandExecutor WarpCommands = new WarpCommands();
		CommandBin.plugin.getCommand("warp").setExecutor(WarpCommands);
		CommandBin.plugin.getCommand("setwarp").setExecutor(WarpCommands);
		CommandBin.plugin.getCommand("delwarp").setExecutor(WarpCommands);
		//
		
		// Torch Bow Commands
		CommandExecutor tb = new TorchBowCommands();
		CommandBin.plugin.getCommand("torchbow").setExecutor(tb);
		//
		
		// Help Page Commands
		CommandExecutor help = new HelpCommands();
		CommandBin.plugin.getCommand("cbhelp").setExecutor(help);
		//
		
		// Explosion Bow Commands
		CommandExecutor ex = new ExplosionBowCommands();
		CommandBin.plugin.getCommand("explosionbow").setExecutor(ex);
		//
		
		// Crossbow Commands
		CommandExecutor cb = new CrossBowCommands();
		CommandBin.plugin.getCommand("crossbow").setExecutor(cb);
		//
		
		// Jail Commands
		CommandExecutor jail = new JailCommands();
		CommandBin.plugin.getCommand("jail").setExecutor(jail);
		CommandBin.plugin.getCommand("unjail").setExecutor(jail);
		CommandBin.plugin.getCommand("setjail").setExecutor(jail);
		CommandBin.plugin.getCommand("setunjail").setExecutor(jail);
		//
		
	}
}
