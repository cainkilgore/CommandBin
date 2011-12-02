package main.java.me.cain.commandbin.listeners;

import main.java.me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EndermanPickupEvent;
import org.bukkit.event.entity.EndermanPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EListener extends EntityListener {
	
	public void onEntityDamage(EntityDamageEvent e)
	{
		
		if(e.getEntity() instanceof Player)
		{
			Player player = (Player) (Player) e.getEntity();
			if(CommandBin.plugin.getConfig().getBoolean(player.getName() + ".godmode"))
			{
				e.setCancelled(true);				
			}
		}
		return;
	}
	
	public void onEntityDeath(EntityDeathEvent e)
	{
		if(CommandBin.plugin.getConfig().getBoolean("settings.dropxpondeath", false))
		{
			e.setDroppedExp(0);
		}
		return;
	}
	
	public void onEntityExplode(EntityExplodeEvent e)
	{
		if(CommandBin.plugin.getConfig().getBoolean("settings.blockcreeperexplosions", true))
		{
			e.setCancelled(true);
		}
		return;
	}
	
	public void onProjectileHit(final ProjectileHitEvent e)
	{
		//Entity arrow = ((Arrow) e.getEntity()).getShooter();
		//String p = ((Player) arrow).getName();
		
		if(e.getEntity() instanceof Arrow)
		{
			if(((Arrow) e.getEntity()).getShooter() instanceof Player)
			{
				if(CommandBin.plugin.getConfig().getBoolean(((Player)((Arrow) e.getEntity()).getShooter()).getName() + ".torchbow"))
				{
					e.getEntity().getLocation().getBlock().setType(Material.TORCH);
				}
			}
			
			if(((Arrow) e.getEntity()).getShooter() instanceof Player)
			{
				if(CommandBin.plugin.getConfig().getBoolean(((Player)((Arrow) e.getEntity()).getShooter()).getName() + ".explosionbow"))
				{
					int radius = CommandBin.plugin.getConfig().getInt("settings.bowexplosionradius");
					e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), radius);
				}
			}
			
			if(((Arrow) e.getEntity()).getShooter() instanceof Player)
			{
				if(CommandBin.plugin.getConfig().getBoolean(((Player)((Arrow) e.getEntity()).getShooter()).getName() + ".crossbow"))
				{
					final int radius = CommandBin.plugin.getConfig().getInt("settings.bowexplosionradius");

					e.getEntity().getLocation().getBlock().setType(Material.REDSTONE_TORCH_ON);
					Bukkit.getScheduler().scheduleAsyncDelayedTask(CommandBin.plugin, new Runnable() 
					{
						public void run()
						{
							e.getEntity().getLocation().getBlock().setType(Material.REDSTONE_TORCH_OFF);
							Bukkit.getScheduler().scheduleAsyncDelayedTask(CommandBin.plugin, new Runnable() 
							{
								public void run()
								{
									e.getEntity().getLocation().getBlock().setType(Material.REDSTONE_TORCH_ON);
									Bukkit.getScheduler().scheduleAsyncDelayedTask(CommandBin.plugin, new Runnable() 
									{
										public void run()
										{
											e.getEntity().getLocation().getBlock().setType(Material.REDSTONE_TORCH_OFF);
											Bukkit.getScheduler().scheduleAsyncDelayedTask(CommandBin.plugin, new Runnable() 
											{
												public void run()
												{
													e.getEntity().getLocation().getBlock().setType(Material.REDSTONE_TORCH_ON);
													Bukkit.getScheduler().scheduleAsyncDelayedTask(CommandBin.plugin, new Runnable() 
													{
														public void run()
														{
															e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), radius);
														}
													}, 
													5L);
												}
											}, 
											20L);
										}
									}, 
									20L);
								}
							}, 
							20L);
						}
						
					}, 
					20L);
					
					}
				}
			}
		
		return;
	}
	
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
