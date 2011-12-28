package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class MsgCommands extends CommandBin {
	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("msg") || l.equalsIgnoreCase("m")) { 
			if(args.length < 2) {
				return false;
			} else {
				StringBuilder x = new StringBuilder();
				for(int i = 1; i < args.length; i++) {
					x.append(args[i] + " ");
				}
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							String MSGFRM = ChatColor.GREEN + "[MSG >- " + p.getName() + "] " + ChatColor.DARK_GREEN + x.toString().trim();
							String MSGTO = ChatColor.GREEN + "[MSG -< " + target.getName() + "] " + ChatColor.DARK_GREEN + x.toString().trim();
							PlayerMessage(target, MSGFRM);
							PlayerMessage(p, MSGTO);
							ConsoleMessage(p.getName() + " > " + target.getName() + ": " + x.toString().trim());
							return true;
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						String MSGFRM = ChatColor.GREEN + "[MSG >- CONSOLE] " + ChatColor.DARK_GREEN + x.toString().trim();
						String MSGTO = ChatColor.GREEN + "[MSG -< " + target.getName() + "] " + ChatColor.DARK_GREEN + x.toString().trim();
						ConsoleMessage(MSGTO);
						PlayerMessage(target, MSGFRM);
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		return true;
	}

}
