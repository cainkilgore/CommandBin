package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cain.cmdbin.CommandBin;

public class TeleportCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("tp")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							p.teleport(target.getLocation());
							PlayerMessage(p, "Teleported!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage(CONSOLE_SENDER);
				}
			}
		}
		
		if(l.equalsIgnoreCase("tphere")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							target.teleport(p.getLocation());
							PlayerMessage(p, "Teleported!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage(CONSOLE_SENDER);
				}
			}
		}
		
		if(l.equalsIgnoreCase("tp2p")) {
			if(args.length < 2) {
				return false;
			} else {
				Player target = getPlayer(args[0]);
				Player target2 = getPlayer(args[1]);
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						if(target != null || target2 != null) {
							target.teleport(target2.getLocation());
							PlayerMessage(p, "Teleported!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					if(target != null || target2 != null) {
						target.teleport(target2.getLocation());
						ConsoleMessage("Teleported!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("spawn")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(pCheck(p, perm)) {
					p.teleport(p.getWorld().getSpawnLocation());
					PlayerMessage(p, "Teleported to spawn");
				} else {
					PlayerMessage(p, NULL_PERMISSION);
				}
			} else {
				if(args.length < 1) {
					return false;
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.teleport(target.getWorld().getSpawnLocation());
						ConsoleMessage("Teleported player to spawn!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("setspawn")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(pCheck(p, perm)) {
					int x = (int) p.getLocation().getX();
					int y = (int) p.getLocation().getY();
					int z = (int) p.getLocation().getZ();
					p.getWorld().setSpawnLocation(x, y, z);
					PlayerMessage(p, "Spawn set!");
				} else {
					PlayerMessage(p, NULL_PERMISSION);
				}
			} else {
				ConsoleMessage(CONSOLE_SENDER);
			}
		}
		
		if(l.equalsIgnoreCase("tpall")) {
			if(s instanceof Player) {
				Player p = (Player) s;
				if(pCheck(p, perm)) {
					for(Player po : Bukkit.getServer().getOnlinePlayers()) {
						po.teleport(p.getLocation());
					}
					PlayerMessage(p, "Teleported all players to you.");
				}
			} else {
				ConsoleMessage(CONSOLE_SENDER);
			}
		}
		
		if(l.equalsIgnoreCase("put")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							Location loc = p.getTargetBlock(null, 0).getLocation()
									.getBlock().getRelative(BlockFace.UP, 2).getLocation();
							target.teleport(loc);
							PlayerMessage(p, "Teleported player to Target Block");
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					ConsoleMessage(CONSOLE_SENDER);
				}
			}
		}
		
		
		// Do not code after this.
		return false;
	}
}