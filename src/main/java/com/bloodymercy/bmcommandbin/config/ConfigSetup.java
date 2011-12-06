package com.bloodymercy.bmcommandbin.config;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.Bukkit;


public class ConfigSetup
{

	public static void start()
	{
		BMCommandBin.plugin.getConfig().options().header(
				"###############################\n" + 
				"Default Configuration File for CommandBin \n" +
				"CommandBin was created by CainFoool\n" +
				"All the values generated below are default and are fully editable.\n" +
				"Feel free to edit them as you plead.\n" +
				"Every time this plugin starts up, this text\n" +
				"Will be generated every time (sorry :p)" +
				"\n\n" +
				"CainFoool\n" +
				"###############################\n");
		
		if(BMCommandBin.plugin.getConfig().getString("settings.shutdownmessage", null) == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.shutdownmessage", "The server is going down for shutdown!");
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default shutdown message");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.broadcastkick") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.broadcastkick", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default broadcast on kick");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.broadcastban") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.broadcastban", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default broadcast on ban");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().getString("settings.playerismuted", null) == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.playerismuted", "You are muted, shh!");
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default Player is Muted message");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().getString("settings.playercannotusecommands", null) == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.playercannotusecommands", "You cannot use commands. Stop trying!");
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default player cannot use commands message");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().getString("settings.joinmessage", null) == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.joinmessage", "has joined the game");
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default Join Message");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().getString("settings.leavemessage", null) == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.leavemessage", "has left the game");
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default Leave Message");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.dropxpondeath") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.dropxpondeath", false);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default Drop XP on Death");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().getString("settings.craftbukkitversion", null) == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.craftbukkitversion", Bukkit.getServer().getVersion().toString());
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default Craftbukkit Version");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.spawn-mob-max-amount") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.spawn-mob-max-amount", 100);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default /spawnmob max amount");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.endermangriefing") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.endermangriefing", false);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default Enderman can grief to false");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.block-placing-tnt") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.block-placing-tnt", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default block placing of tnt");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.block-placing-lava") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.block-placing-lava", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default block placing of lava");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.teleportonthrowegg") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.teleportonthrowegg", false);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default teleport on throw egg");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.blockcreeperexplosions") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.blockcreeperexplosions", false);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default block creeper explosions");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.orebroadcast.coalore") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.orebroadcast.coalore", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default ore broadcast coal");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.orebroadcast.ironore") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.orebroadcast.ironore", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default ore broadcast iron");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.orebroadcast.goldore") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.orebroadcast.goldore", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default ore broadcast gold");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.orebroadcast.diamondore") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.orebroadcast.diamondore", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default ore broadcast diamond");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.orebroadcast.redstoneore") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.orebroadcast.redstoneore", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default ore broadcast redstone");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.orebroadcast.lapislazuliore") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.orebroadcast.lapislazuliore", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default ore broadcast lapis lazuli");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.mineablemobspawners") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.mineablemobspawners", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default mineable mob spawners");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.opscanseepms") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.opscanseepms", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default ops can see pms");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().getString("settings.message-of-the-day") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.message-of-the-day", "Please Modify This! [MOTD]");
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default message of the day");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.customchat") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.customchat", true);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default custom chat");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.bowexplosionradius") == null)
		{
			BMCommandBin.plugin.getConfig().set("settings.bowexplosionradius", 3);
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default bow explosion radius");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.spawn-enderdragon-on-hitting-enderegg") == null)
		{
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default Enderegg");
			BMCommandBin.plugin.getConfig().set("settings.spawn-enderdragon-on-hitting-enderegg", true);
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.multihomesupport") == null)
		{
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting Multi Home Support");
			BMCommandBin.plugin.getConfig().set("settings.multihomesupport", true);
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.lightningonjoin") == null)
		{
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default lightning on join");
			BMCommandBin.plugin.getConfig().set("settings.lightningonjoin", true);
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.consolename") == null)
		{
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default Console Name");
			BMCommandBin.plugin.getConfig().set("settings.consolename", "Console");
			BMCommandBin.plugin.saveConfig();
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.debugitems") == null)
		{
			System.out.println(BMCommandBin.plugin.Plugin + "[Config] Setting default debug items");
			BMCommandBin.plugin.getConfig().set("settings.debugitems", false);
			BMCommandBin.plugin.saveConfig();
		}
		
		
		
		
		
	}
	
}
