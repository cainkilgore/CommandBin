package org.cain.cmdbin.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.cain.cmdbin.CommandBin;
import org.cain.cmdbin.commands.FreezeCommands;
import org.cain.cmdbin.commands.HandicapCommands;
import org.cain.cmdbin.commands.MuteCommands;

public class CMDBinPListener extends PlayerListener {
	
	FreezeCommands fc;
	MuteCommands mc;
	CommandBin cmdbin;
	HandicapCommands hc;
	
	public void onPlayerMove(PlayerMoveEvent e) {
		if(CommandBin.cfg.getBoolean("players." + e.getPlayer().getName() + ".frozen")) {
			e.setCancelled(true);
		}
		
		if(CommandBin.cfg.getBoolean("settings.landmines")) {
			Block bl = e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1);
			if(bl.getType() == Material.LAPIS_BLOCK) {
				e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(), 3);
			}
		}
		return;
	}
	
	public void onPlayerChat(PlayerChatEvent e) {
		if(CommandBin.cfg.getBoolean("players." + e.getPlayer().getName() + ".muted")) {
			e.setCancelled(true);
		}
		return;
	}
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		if(CommandBin.cfg.getBoolean("players." + e.getPlayer().getName() + ".handicapped")) {
			e.setCancelled(true);
		}
		return;
	}

}
