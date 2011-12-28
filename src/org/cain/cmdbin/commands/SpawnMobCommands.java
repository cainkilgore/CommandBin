package org.cain.cmdbin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class SpawnMobCommands extends CommandBin {
	
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		if(l.equalsIgnoreCase("mob")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					CreatureType mob = CreatureType.valueOf(args[0].toUpperCase());
					if(pCheck(p, perm)) {
						if(mob != null) {
							p.getWorld().spawnCreature(p.getTargetBlock(null, 0).getLocation(), mob);
							PlayerMessage(p, mob + " spawned!");
						} else {
							PlayerMessage(p, "This mob does not exist!");
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage("You can't spawn mobs in console!");
				}
			}
		}
		
		return true;
	}

}
