package org.cain.cmdbin.commands;

import static org.bukkit.Bukkit.getPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.cain.cmdbin.CommandBin;

public class PlayerCommands extends CommandBin {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		String perm = "CommandBin." + l.toLowerCase();
		
		if(l.equalsIgnoreCase("shoot")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							target.setVelocity(new Vector(target.getVelocity().getX(), 64, target.getVelocity().getZ()));
							PlayerMessage(p, "Shot " + target.getName() + " into the air!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.setVelocity(new Vector(target.getVelocity().getX(), 64, target.getVelocity().getZ()));
						ConsoleMessage("Shot " + target.getName() + " into the air!");
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("strike")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							target.getWorld().strikeLightning(target.getLocation());
							PlayerMessage(p, "Struck " + target.getName() + " with lightning!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.getWorld().strikeLightning(target.getLocation());
						ConsoleMessage("Stuck " + target.getName() + " with lightning!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("heal")) {
			if(args.length < 1) {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						p.setHealth(20);
						PlayerMessage(p, "Health bar set to 20!");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					return false;
				}
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							target.setHealth(20);
							PlayerMessage(p, target.getName() + "'s health bar set to 20!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.setHealth(20);
						ConsoleMessage(target.getName() + "'s health bar set to 20!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("feed")) {
			if(args.length < 1) {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						 p.setFoodLevel(20);
						 PlayerMessage(p, "Food bar set to 20!");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					return false;
				}
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							target.setFoodLevel(20);
							PlayerMessage(p, target.getName() + "'s food bar set to 20!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.setFoodLevel(20);
						ConsoleMessage(target.getName() + "'s food bar set to 20!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("explode")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							target.getWorld().createExplosion(target.getLocation(), 5);
							PlayerMessage(p, "You made " + target.getName() + " explode!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.getWorld().createExplosion(target.getLocation(), 5);
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("light")) {
			if(args.length < 2) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							try {
							int i = 1000 * Integer.parseInt(args[1]);
							target.setFireTicks(i);
							} catch (NumberFormatException e) {
								PlayerMessage(p, "Invalid number entered!");
							}
					} else {
						PlayerMessage(p, PLAYER_OFFLINE);
					}
				} else {
					PlayerMessage(p, NULL_PERMISSION);
				}
			} else {
				Player target = getPlayer(args[0]);
				if(target != null) {
					try {
						int i = 1000 * Integer.parseInt(args[1]);
						target.setFireTicks(i);
					} catch (NumberFormatException e) {
						ConsoleMessage("Invalud number entered!");
						}
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("slap")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							target.setVelocity(new Vector(0, 2, 0));
							PlayerMessage(p, "You slapped " + target.getName());
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.setVelocity(new Vector(0, 2, 0));
						ConsoleMessage("You slapped " + target.getName());
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("clear")) {
			if(args.length < 1) {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						p.getInventory().clear();
						PlayerMessage(p, "Inventory cleared!");
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					return false;
				}
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm + ".others")) {
						Player target = getPlayer(args[0]);
						if(target != null) {
							target.getInventory().clear();
							PlayerMessage(p, target.getName() + "'s inventory cleared!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.getInventory().clear();
						ConsoleMessage(target.getName() + "'s inventory cleared!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("kill")) {
			if(args.length < 1) {
				return false;
			} else {
				if(s instanceof Player) {
					Player p = (Player) s;
					Player target = getPlayer(args[0]);
					if(pCheck(p, perm)) {
						if(target != null) {
							target.getWorld().strikeLightning(target.getLocation());
							target.setHealth(0);
							PlayerMessage(p, target.getName() + " killed!");
						} else {
							PlayerMessage(p, PLAYER_OFFLINE);
						}
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Player target = getPlayer(args[0]);
					if(target != null) {
						target.getWorld().strikeLightning(target.getLocation());
						target.setHealth(0);
						ConsoleMessage(target.getName() + " killed!");
					} else {
						ConsoleMessage(PLAYER_OFFLINE);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("me")) {
			if(args.length < 1) {
				return false;
			} else {
				StringBuilder x = new StringBuilder();
				for (int i = 0; i < args.length; i++) {
					x.append(args[i] + " ");
				}
				if(s instanceof Player) {
					Player p = (Player) s;
					if(pCheck(p, perm)) {
						Broadcast(ChatColor.AQUA + "* " + p.getName() + ChatColor.WHITE + ": " + x.toString().trim());
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
				} else {
					Broadcast(ChatColor.AQUA + "* Console" + ChatColor.WHITE + ": " + x.toString().trim());
				}
			}
		}
		
		if(s instanceof Player) {
			Player p = (Player) s;
			if(l.equalsIgnoreCase("loc")) {
					double x = p.getLocation().getX();
					double y = p.getLocation().getY();
					double z = p.getLocation().getZ();
					if(pCheck(p, perm)) {
						PlayerMessage(p, "CURRENT LOCATION");
						PlayerMessage(p, "X: " + x);
						PlayerMessage(p, "Y: " + y);
						PlayerMessage(p, "Z: " + z);
					} else {
						PlayerMessage(p, NULL_PERMISSION);
					}
			}
		} else {
			ConsoleMessage("You can't check your location in-game!");
		}
		
		return true;
		
		
	}

}
