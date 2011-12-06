package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.command.CommandExecutor;

public class CommandRegistration
{
	public static void SetupCommands()
	{
		
		// Teleportation Commands
		CommandExecutor TeleporterCommands = new PlayerCommands();
		BMCommandBin.plugin.getCommand("tp").setExecutor(TeleporterCommands);
		BMCommandBin.plugin.getCommand("tphere").setExecutor(TeleporterCommands);
		BMCommandBin.plugin.getCommand("tpall").setExecutor(TeleporterCommands);
		BMCommandBin.plugin.getCommand("tp2p").setExecutor(TeleporterCommands);
		BMCommandBin.plugin.getCommand("put").setExecutor(TeleporterCommands);
		BMCommandBin.plugin.getCommand("tpworld").setExecutor(TeleporterCommands);
		BMCommandBin.plugin.getCommand("spawn").setExecutor(TeleporterCommands);
		BMCommandBin.plugin.getCommand("setspawn").setExecutor(TeleporterCommands);
		//
		
		// World Commands
		CommandExecutor WorldCommands = new WorldCommands();
		BMCommandBin.plugin.getCommand("createworld").setExecutor(WorldCommands);
		BMCommandBin.plugin.getCommand("unloadworld").setExecutor(WorldCommands);
		//
		
		// Administration Commands
		CommandExecutor AdminCommands = new AdministrationCommands();
		BMCommandBin.plugin.getCommand("shutdown").setExecutor(AdminCommands);
		BMCommandBin.plugin.getCommand("cmdbin").setExecutor(AdminCommands);
		//
		
		// Fake Join/Leave Commands
		CommandExecutor FakeJL = new FakeJoinLeaveCommands();
		BMCommandBin.plugin.getCommand("join").setExecutor(FakeJL);
		BMCommandBin.plugin.getCommand("leave").setExecutor(FakeJL);
		//
		
		// Debug Commands
		CommandExecutor Debug = new DebugCommands();
		BMCommandBin.plugin.getCommand("ping").setExecutor(Debug);
		//
		
		// Game Mode Commands
		CommandExecutor GM = new GameModeCommands();
		BMCommandBin.plugin.getCommand("creative").setExecutor(GM);
		BMCommandBin.plugin.getCommand("survival").setExecutor(GM);
		//
		
		// Weather Control Commands
		CommandExecutor WeatherControl = new WeatherCommands();
		BMCommandBin.plugin.getCommand("rain").setExecutor(WeatherControl);
		BMCommandBin.plugin.getCommand("sun").setExecutor(WeatherControl);
		//
		
		// Player Commands
		CommandExecutor PlayerCommands = new PlayerCommands();
                BMCommandBin.plugin.getCommand("afk").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("register").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("list").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("touch").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("fart").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("makesay").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("puke").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("cut").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("broadcast").setExecutor(PlayerCommands);
                BMCommandBin.plugin.getCommand("slit").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("shoot").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("strike").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("heal").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("feed").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("god").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("facepalm").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("explode").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("light").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("roll").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("smoke").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("explosionstick").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("lightningstick").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("slap").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("msg").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("clear").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("kill").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("i").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("time").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("nick").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("setxp").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("up").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("unlimited").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("delunlimited").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("mytime").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("armour").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("me").setExecutor(PlayerCommands);
		BMCommandBin.plugin.getCommand("more").setExecutor(PlayerCommands);
		//
		
		// Snowman Commands
		CommandExecutor SnowmanCommands = new SnowmanCommands();
		BMCommandBin.plugin.getCommand("snowman").setExecutor(SnowmanCommands);
		BMCommandBin.plugin.getCommand("unsnowman").setExecutor(SnowmanCommands);
		//
		
		// Moderation Commands
		CommandExecutor ModCommands = new ModerationCommands();
		BMCommandBin.plugin.getCommand("kick").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("ban").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("unban").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("freeze").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("unfreeze").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("handicap").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("unhandicap").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("mute").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("unmute").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("who").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("paid").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("banip").setExecutor(ModCommands);
		BMCommandBin.plugin.getCommand("unbanip").setExecutor(ModCommands);
		//
		
		// Spawn Mob Commands
		CommandExecutor MobCommands = new MobCommands();
		BMCommandBin.plugin.getCommand("spawnmob").setExecutor(MobCommands);
		BMCommandBin.plugin.getCommand("killmobs").setExecutor(MobCommands);
		//
		
		// Home Commands
		CommandExecutor HomeCommands = new HomeCommands();
		BMCommandBin.plugin.getCommand("home").setExecutor(HomeCommands);
		BMCommandBin.plugin.getCommand("sethome").setExecutor(HomeCommands);
		//
		
		// Warp Commands
		CommandExecutor WarpCommands = new WarpCommands();
		BMCommandBin.plugin.getCommand("warp").setExecutor(WarpCommands);
		BMCommandBin.plugin.getCommand("setwarp").setExecutor(WarpCommands);
		BMCommandBin.plugin.getCommand("delwarp").setExecutor(WarpCommands);
		//
		
		// Torch Bow Commands
		CommandExecutor tb = new TorchBowCommands();
		BMCommandBin.plugin.getCommand("torchbow").setExecutor(tb);
		//
		
		// Help Page Commands
		CommandExecutor help = new HelpCommands();
		BMCommandBin.plugin.getCommand("cbhelp").setExecutor(help);
		//
		
		// Explosion Bow Commands
		CommandExecutor ex = new ExplosionBowCommands();
		BMCommandBin.plugin.getCommand("explosionbow").setExecutor(ex);
		//
		
		// Crossbow Commands
		CommandExecutor cb = new CrossBowCommands();
		BMCommandBin.plugin.getCommand("crossbow").setExecutor(cb);
		//
		
		// Jail Commands
		CommandExecutor jail = new JailCommands();
		BMCommandBin.plugin.getCommand("jail").setExecutor(jail);
		BMCommandBin.plugin.getCommand("unjail").setExecutor(jail);
		BMCommandBin.plugin.getCommand("setjail").setExecutor(jail);
		BMCommandBin.plugin.getCommand("setunjail").setExecutor(jail);
		//
		
		// Troll Commands
		CommandExecutor Troll = new TrollCommands();
		BMCommandBin.plugin.getCommand("troll").setExecutor(Troll);
		// Before anyone judges, I made this out of entire boredom D: 
		//
		
		// Say Command (works in-console and in-game)
		CommandExecutor say = new SayCommand();
		BMCommandBin.plugin.getCommand("say").setExecutor(say);
		//
	}
}
