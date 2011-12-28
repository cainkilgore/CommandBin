package org.cain.cmdbin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class PingCommand extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("ping")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				PlayerMessage(p, "Pong!");
			} else {
				ConsoleMessage("Pong!");
			}
		}
		return true;
	}

	// Shortest class? I think so!! :) 
	
}
