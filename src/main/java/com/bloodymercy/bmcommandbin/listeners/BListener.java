package com.bloodymercy.bmcommandbin.listeners;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BListener extends BlockListener {
	
	public void onBlockPlace(BlockPlaceEvent e)
	{
		if(BMCommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".unlimited") != null)
		{
			if(BMCommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".unlimited").equals(true))
			{
				e.getPlayer().setItemInHand(new ItemStack(e.getPlayer().getItemInHand().getType(), 64));
			}
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.block-placing-tnt") != null)
		{
			if(BMCommandBin.plugin.getConfig().get("settings.block-placing-tnt").equals(true))
			{
				if(e.getBlock().getType() == Material.TNT)
				{
					if(!BMCommandBin.plugin.pCheck(e.getPlayer(), "CommandBin.general.tntbypass"))
					{
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "Your administrator has disabled the placement of TNT.");
					}
				}
			}
		}
		
		if(BMCommandBin.plugin.getConfig().get("settings.block-placing-lava") != null)
		{
			if(BMCommandBin.plugin.getConfig().get("settings.block-placing-lava").equals(true))
			{
				if(e.getBlock().getType() == Material.LAVA || e.getBlock().getType() == Material.LAVA_BUCKET)
				{
					if(!BMCommandBin.plugin.pCheck(e.getPlayer(), "CommandBin.general.lavabypass"))
					{
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "Your administrator has disabled the placement of LAVA.");
					}
				}
			}
		}
		
		
		return;
	}
	
	public void onBlockBreak(BlockBreakEvent e)
	{
		
		Block brokenblock = e.getBlock();
		
		if(BMCommandBin.plugin.getConfig().getBoolean("settings.mineablemobspawners"))
		{
			if(e.getPlayer().getItemInHand().getType() == Material.DIAMOND_PICKAXE)
			{
				if(brokenblock.getType() == Material.MOB_SPAWNER)
				{
					e.getPlayer().getWorld().dropItemNaturally(brokenblock.getLocation(), new ItemStack(Material.MOB_SPAWNER, 1));
				}
			}
		}
		
		if(BMCommandBin.plugin.getConfig().getBoolean("settings.orebroadcast.coalore"))
		{
			if(e.getBlock().getType() == Material.COAL_ORE)
			{
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + e.getPlayer().getName() + " mined some Coal Ore!");
			}
		}
		
		if(BMCommandBin.plugin.getConfig().getBoolean("settings.orebroadcast.ironore"))
		{
			if(e.getBlock().getType() == Material.IRON_ORE)
			{
				Bukkit.getServer().broadcastMessage(e.getPlayer().getName() + " mined some Iron Ore!");
			}
		}
		
		if(BMCommandBin.plugin.getConfig().getBoolean("settings.orebroadcast.goldore"))
		{
			if(e.getBlock().getType() == Material.GOLD_ORE)
			{
				Bukkit.getServer().broadcastMessage(ChatColor.GOLD + e.getPlayer().getName() + " mined some Gold Ore!");
			}
		}
		
		if(BMCommandBin.plugin.getConfig().getBoolean("settings.orebroadcast.diamondore"))
		{
			if(e.getBlock().getType() == Material.DIAMOND_ORE)
			{
				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + e.getPlayer().getName() + " mined some Diamond Ore!");
			}
		}
		
		if(BMCommandBin.plugin.getConfig().getBoolean("settings.orebroadcast.redstoneore"))
		{
			if(e.getBlock().getType() == Material.REDSTONE_ORE)
			{
				Bukkit.getServer().broadcastMessage(ChatColor.RED + e.getPlayer().getName() + " mined some Redstone Ore!");
			}
		}
		
		if(BMCommandBin.plugin.getConfig().getBoolean("settings.orebroadcast.lapislazuliore"))
		{
			if(e.getBlock().getType() == Material.LAPIS_ORE)
			{
				Bukkit.getServer().broadcastMessage(ChatColor.BLUE + e.getPlayer().getName() + " mined some Lapis Lazuli Ore!");
			}
		}
		return;
	}

}
