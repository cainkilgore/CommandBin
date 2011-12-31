package org.cain.cmdbin.listeners;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.ChatColor;
import org.cain.cmdbin.CommandBin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.player.SpoutPlayer;

public class CMDBinSListener extends SpoutListener {
	
	public void onSpoutCraftEnable(SpoutCraftEnableEvent e) {
		if(CapeCheck(e.getPlayer())) {
			e.getPlayer().setCape("http://dl.dropbox.com/u/7186172/cape/Bukkit.png");
		}
		
		if(e.getPlayer().getName().equalsIgnoreCase("CainFool")) {
			SpoutManager.getPlayer(e.getPlayer()).setTitle(ChatColor.RED + "CainFool\n" + ChatColor.WHITE + "CommandBin Developer");
		}
		return;
	}
	
	public boolean CapeCheck (SpoutPlayer p) {
			try {
				URL url = new URL("http://dl.dropbox.com/u/7186172/cape/Capes.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				String strLine;
				while((strLine = br.readLine()) != null) {
					if(strLine.contains(p.getName())) {
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

}
