package org.cain.cmdbin.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class GodCommands extends CommandBin {
	
	public HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("godon")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(pCheck(p, perm)) {
					cfg.set("players." + p.getName() + ".godmode", true);
					PlayerMessage(p, "God Mode enabled!");
				} else {
					PlayerMessage(p, NULL_PERMISSION);
				}
			} else {
				ConsoleMessage(CONSOLE_SENDER);
			}
		}
		
		if(l.equalsIgnoreCase("godoff")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(pCheck(p, perm)) {
					cfg.set("players." + p.getName() + ".godmode", false);
					PlayerMessage(p, "God Mode disabled!");
				} else {
					PlayerMessage(p, NULL_PERMISSION);
				}
			} else {
				ConsoleMessage(CONSOLE_SENDER);
			}
		}
		return false;
	}
	

}
