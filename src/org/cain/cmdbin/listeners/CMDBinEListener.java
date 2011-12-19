package org.cain.cmdbin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.cain.cmdbin.CommandBin;
import org.cain.cmdbin.commands.GodCommands;

public class CMDBinEListener extends EntityListener {
	
	GodCommands gm;
	
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) (Player) e.getEntity();
			if(CommandBin.cfg.getBoolean("players." + player.getName() + ".godmode")) {
				e.setCancelled(true);
			}
		}
		return;
	}

}
