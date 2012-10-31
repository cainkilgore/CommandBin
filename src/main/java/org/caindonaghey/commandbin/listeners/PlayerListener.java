package org.caindonaghey.commandbin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.caindonaghey.commandbin.API;
import org.caindonaghey.commandbin.Utils;

public class PlayerListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(API.afkPlayer.contains(player.getName())) {
			API.afkPlayer.remove(player.getName());
			Utils.broadcastMessage(player.getName() + " is no longer AFK!");
		}
	}

}
