package main.java.me.cain.commandbin.listeners;

import main.java.me.cain.commandbin.CommandBin;

import org.bukkit.event.entity.EndermanPickupEvent;
import org.bukkit.event.entity.EndermanPlaceEvent;
import org.bukkit.event.entity.EntityListener;


public class EnderListener extends EntityListener
{
	public void onEndermanPickup(EndermanPickupEvent e)
	{
		if(CommandBin.plugin.getConfig().getBoolean("settings.endermangriefing", false))
		{
			e.setCancelled(true);
		}
		return;
	}
	
	public void onEndermanPlace(EndermanPlaceEvent e)
	{
		if(CommandBin.plugin.getConfig().getBoolean("settings.endermangriefing", false))
		{
			e.setCancelled(true);
		}
		return;
	}
}
