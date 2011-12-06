package com.bloodymercy.bmcommandbin.commands;

import com.bloodymercy.bmcommandbin.CommandBin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerCommands implements CommandExecutor {
    static final Logger logger = Logger.getLogger("Minecraft");
    
    public void log(String info) {
        logger.info(info);
    }
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        if (l.equalsIgnoreCase("lick")) {
            if (s instanceof Player) {
                if (args.length > 0) {
                    Player p = (Player) s;
                    StringBuilder sb = new StringBuilder();
                    for (String a : args) {
                        sb.append(a).append(" ");
                    }
                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + p.getDisplayName() + " licks " + sb.toString().trim());
                    return true;
                } else {
                    return false;
                }
            } else {
                log("This command is for users only.");
                return true;
            }
        }
        if (l.equalsIgnoreCase("broadcast")) {
            if (s instanceof Player) {
                if (((Player) s).isOp()) {
                    if (args.length > 1) {
                        StringBuilder sb = new StringBuilder();
                        for (String st : args) {
                            sb.append(st).append(" ");
                        }
                        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "Broadcast"
                                + ChatColor.YELLOW + "] " + ChatColor.AQUA + sb.toString());
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    ((Player) s).sendMessage(ChatColor.RED + "Access denied.");
                    return true;
                }

            } else {
                if (args.length > 1) {
                    StringBuilder sb = new StringBuilder();
                    for (String st : args) {
                        sb.append(st).append(" ");
                    }
                    Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "Broadcast"
                            + ChatColor.YELLOW + "] " + ChatColor.AQUA + sb.toString());
                    return true;
                } else {
                    return false;
                }

            }
        }
        if (l.equalsIgnoreCase("puke")) {
            if (s instanceof Player) {
                Bukkit.getServer().broadcastMessage(ChatColor.DARK_GREEN + ((Player) s).getDisplayName() + " is throwing up!");
                return true;
            } else {
                log("This command is for users only.");
                return true;
            }
        }
        if (l.equalsIgnoreCase("makesay")) {
            if (s instanceof Player) {
                if (args.length > 1) {
                    Player p = Bukkit.getServer().getPlayer(args[0]);
                    Player send = (Player) s;
                    if (p == null) {
                        send.sendMessage(ChatColor.RED + "That user isn't online!");
                        return true;
                    } else {
                        if (p.getDisplayName().equals("SmilingDevil")) {
                            send.sendMessage(ChatColor.RED + "I think not. :)");
                            return true;
                        } else {
                            StringBuilder sb = new StringBuilder();
                            int count = 1;
                            while (count < args.length) {
                                sb.append(args[count]).append(" ");
                                count++;
                            }
                            p.chat(sb.toString());
                            return true;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                if (args.length > 1) {
                    Player p = Bukkit.getServer().getPlayer(args[0]);
                    if (p == null) {
                        log(ChatColor.RED + "That user isn't online!");
                        return true;
                    } else {
                        if (p.getDisplayName().equals("SmilingDevil")) {
                            log(ChatColor.RED + "I think not. :)");
                            return true;
                        } else {
                            StringBuilder sb = new StringBuilder();
                            int count = 1;
                            while (count < args.length) {
                                sb.append(args[count]).append(" ");
                            }
                            p.chat(sb.toString());
                            return true;
                        }
                    }
                } else {
                    return false;
                }
            }

        }
        if (l.equalsIgnoreCase("list")) {
            if (s instanceof Player) {
                Player[] ps = Bukkit.getServer().getOnlinePlayers();
                Player p = (Player) s;
                p.sendMessage(ChatColor.AQUA + "Well " + ChatColor.RED + p.getDisplayName() + ChatColor.AQUA
                        + ", there are currently " + ChatColor.RED + ps.length + ChatColor.AQUA + " of " + ChatColor.RED
                        + Bukkit.getServer().getMaxPlayers() + ChatColor.AQUA + " online");
                StringBuilder sb = new StringBuilder();
                int count = 0;
                while (count < ps.length) {
                    if (ps[count].getDisplayName().equals("SmilingDevil")) {
                        if (count + 1 >= ps.length) {
                            sb.append(ChatColor.GOLD).append(ps[count].getDisplayName());
                        } else {
                            sb.append(ChatColor.GOLD).append(ps[count].getDisplayName()).append(", ").append(ChatColor.AQUA);
                        }
                    } else if (ps[count].getDisplayName().equals("biohazardpanda")) {
                        if (count + 1 >= ps.length) {
                            sb.append(ChatColor.LIGHT_PURPLE).append(ps[count].getDisplayName());
                        } else {
                            sb.append(ChatColor.LIGHT_PURPLE).append(ps[count].getDisplayName()).append(", ").append(ChatColor.AQUA);
                        }
                    } else if (ps[count].getDisplayName().equals("AwakenedSpirit")) {
                        if (count + 1 >= ps.length) {
                            sb.append(ChatColor.GRAY).append(ps[count].getDisplayName());
                        } else {
                            StringBuilder append = sb.append(ChatColor.GRAY).append(ps[count].getDisplayName()).append(", ").append(ChatColor.AQUA);
                        }
                        sb.append(ChatColor.GRAY).append(ps[count].getDisplayName()).append(", ").append(ChatColor.AQUA);
                    } else {
                        if (count + 1 >= ps.length) {
                            sb.append(ChatColor.DARK_AQUA).append(ps[count].getDisplayName());
                        } else {
                            sb.append(ChatColor.DARK_AQUA).append(ps[count].getDisplayName()).append(ChatColor.WHITE).append(", ");
                        }
                    }
                    count++;
                }
                p.sendMessage(ChatColor.GREEN + "Online: " + sb.toString());
                return true;
            } else {
                Player[] ps = Bukkit.getServer().getOnlinePlayers();
                StringBuilder sb = new StringBuilder();
                int count = 0;
                while (count < ps.length) {
                    sb.append(ps[count].getDisplayName()).append(", ");
                    count++;
                }
                sb.append(ChatColor.GOLD).append(ps[count].getDisplayName()).append(ChatColor.AQUA);
                log(ChatColor.GREEN + "Online: " + sb.toString());
                log("Online users: " + sb.toString());
                return true;
            }
        }
        String last_user = null;
        int repeat_times = 0;
        if (l.equalsIgnoreCase("touch")) {
            if (s instanceof Player) {
                if (args.length != 0) {
                    Player p = (Player) s;
                    StringBuilder sb = new StringBuilder();
                    for (String a : args) {
                        sb.append(a).append(" ");
                    }
                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + p.getDisplayName() + " touches " + sb.toString());
                    return true;
                } else {
                    return false;
                }
            } else {
                log("This command is for users only.");
                return true;
            }
        } else if (l.equalsIgnoreCase("cut")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (!last_user.equals(p.getDisplayName())) {
                    last_user = p.getDisplayName();
                    Bukkit.getServer().broadcastMessage(ChatColor.RED + p.getDisplayName() + " is cutting himself!");
                    repeat_times = 1;
                    return true;
                } else {
                    if (repeat_times < 3) {
                        repeat_times++;
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + p.getDisplayName() + " is cutting himself!");
                        return true;
                    } else {
                        p.setHealth(0);
                        Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + p.getDisplayName() + " bled out");
                        return true;
                    }
                }
            } else {
                log("This command is for users only.");
                return true;
            }
        } else if (l.equalsIgnoreCase("slit")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + p.getDisplayName() + " has slit their throat!");
                p.setHealth(0);
                return true;
            } else {
                log("This command is for users only.");
                return true;
            }
        }
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        if (l.equalsIgnoreCase("fart")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (p.isOp()) {
                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + p.getDisplayName() + " has farted!");
                    Random rand = new Random();
                    if (rand.nextInt(101) > 50) {
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + "It was deadly too!");
                        Entity[] ent = p.getLocation().getBlock().getChunk().getEntities();
                        for (Entity e : ent) {
                            if (e instanceof Player) {
                                Player pl = (Player) e;
                                if (!pl.getDisplayName().equals(p.getDisplayName())) {
                                    pl.sendMessage(ChatColor.RED + "You were hurt by the fart!");
                                    pl.damage(5);
                                }
                            } else {
                                e.remove();
                            }
                        }

                    }
                }
                return true;
            } else {
                log("This command is for users only.");
                return true;
            }
        } else if (l.equals("afk")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (map.containsKey(p.getDisplayName())) {
                    if (map.get(p.getDisplayName()) == false) {
                        map.remove(p.getDisplayName());
                        map.put(p.getDisplayName(), true);
                        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + p.getDisplayName() + " is now away from keyboard.");
                    } else {
                        map.remove(p.getDisplayName());
                        map.put(p.getDisplayName(), false);
                        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + p.getDisplayName() + " is no longer away from keyboard.");
                    }
                } else {
                    map.put(p.getDisplayName(), true);
                    Bukkit.getServer().broadcastMessage(ChatColor.GREEN + p.getDisplayName() + " is now away from keyboard.");
                }
                return true;
            } else {
                log("This command is for users only.");
                return true;
            }
        }
        
        if (l.equalsIgnoreCase("register")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (args.length > 0) {
                    if (args[0].length() > 7) {
                        if (args[0].contains("pass")) {
                            p.sendMessage(ChatColor.RED + "Too simple password!");
                        } else {
                            try {
                                // Construct data
                                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(((Player) s).getDisplayName(), "UTF-8");
                                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(args[0], "UTF-8");

                                // Send data
                                URL url = new URL("http://bloodymercy.com/int_register.php");
                                URLConnection conn = url.openConnection();
                                conn.setDoOutput(true);
                                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                                wr.write(data);
                                wr.flush();

                                // Get the response
                                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                String result = rd.readLine();

                                switch (Integer.parseInt(result)) {
                                    case 0:
                                        p.sendMessage(ChatColor.GREEN + "Successfully registered!");
                                        p.sendMessage(ChatColor.GREEN + "You can now login to " + ChatColor.AQUA + "bloodymercy.com"
                                                + ChatColor.GREEN + "!");
                                        return true;
                                    case 1:
                                        p.sendMessage(ChatColor.RED + "You already have an account!");
                                        return true;
                                    case 2:
                                        p.sendMessage(ChatColor.RED + "A fatal error occured. Tell Devil!");
                                        return true;
                                    case 3:
                                        p.sendMessage(ChatColor.RED + "Access was denied to the server!");
                                        return true;
                                }
                                wr.close();
                                rd.close();
                                return false;
                            } catch (Exception e) {
                                p.sendMessage("An error occured. Please try again later.");
                                return true;
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Your password must be at least 8 characters.");
                    }
                } else {
                    return false;
                }

            } else {
                log(ChatColor.RED + "You can't register console! Silly!");
            }
        }
        if (l.equalsIgnoreCase("back")) {
            if (s instanceof Player) {
            } else {
            }
        }
        if (l.equalsIgnoreCase("tp")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    s.sendMessage("/" + l.toString() + " [player]");
                } else {
                    s.sendMessage("/" + l.toString() + " [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.teleport.tp")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            ((Player) s).teleport(target.getLocation());
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Teleported to " + target.getName());
                            ((Player) s).getWorld().playEffect(((Player) s).getLocation(), Effect.SMOKE, 20);
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    if (args.length < 2) {
                        s.sendMessage("/tp [player] to [player]");
                    } else {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        Player target2 = Bukkit.getServer().getPlayer(args[1]);
                        if (target != null && target2 != null) {
                            target.teleport(target2);
                            s.sendMessage(ChatColor.GREEN + "Teleported"
                                    + target.getName() + " to "
                                    + target2.getName());
                        } else {
                            s.sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("tphere")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    s.sendMessage("/" + l.toString() + " [player]");
                } else {
                    s.sendMessage("/" + l.toString() + " [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.teleport.tphere")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.teleport(((Player) s).getLocation());
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Teleported " + target.getName()
                                    + " to you!");
                            target.sendMessage(((Player) s).getName()
                                    + " teleported you!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage(ChatColor.RED
                            + "You can't teleport a player to you, you're console silly!");
                }
            }
        }

        if (l.equalsIgnoreCase("tp2p")) {
            if (args.length < 2) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [player] to [player]");
                } else {
                    s.sendMessage("/tp [player] to [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.teleport.tp2p")) {
                        Player target1 = Bukkit.getServer().getPlayer(args[0]);
                        Player target2 = Bukkit.getServer().getPlayer(args[1]);
                        if (target1 != null && target2 != null) {
                            target1.teleport(target2.getLocation());
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Teleported " + target1.getName()
                                    + " to " + target2.getName());
                            target1.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " teleported you to " + target2.getName());
                            target2.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName() + " teleported "
                                    + target1.getName() + " to you!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target1 = Bukkit.getServer().getPlayer(args[0]);
                    Player target2 = Bukkit.getServer().getPlayer(args[1]);
                    if (target1 != null && target2 != null) {
                        target1.teleport(target2.getLocation());
                        s.sendMessage(ChatColor.GREEN + "Teleported "
                                + target1.getName() + " to "
                                + target2.getName());
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("put")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [player]");
                } else {
                    s.sendMessage("/put [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.teleport.put")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            Location place = ((Player) s).getTargetBlock(null, 0).getRelative(BlockFace.UP, 2).getLocation();
                            target.teleport(place);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Teleported " + target.getName()
                                    + " to where you're looking at!");
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " teleported you to where he/she was looking at!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage(ChatColor.RED
                            + "You are not in-game so you can't put a player where you're looking at!");
                }
            }
        }

        if (l.equalsIgnoreCase("setspawn")) {
            if (s instanceof Player) {
                if (CommandBin.plugin.pCheck(((Player) s),
                        "CommandBin.teleport.setspawn")) {
                    ((Player) s).getWorld().setSpawnLocation(
                            (int) ((Player) s).getLocation().getX(),
                            (int) ((Player) s).getLocation().getY(),
                            (int) ((Player) s).getLocation().getZ());
                    ((Player) s).sendMessage(ChatColor.GREEN
                            + " World spawn set!");
                } else {
                    ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                }
            } else {
                s.sendMessage(ChatColor.RED
                        + "You must be in-game to set the spawn!");
            }
        }

        if (l.equalsIgnoreCase("spawn")) {
            if (s instanceof Player) {
                if (CommandBin.plugin.pCheck(((Player) s),
                        "CommandBin.teleport.spawn")) {
                    ((Player) s).teleport(((Player) s).getWorld().getSpawnLocation());
                    ((Player) s).sendMessage(ChatColor.GREEN
                            + "Teleported to Spawn in '"
                            + ((Player) s).getWorld().getName().toLowerCase()
                            + "'");
                } else {
                    ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                }
            } else {
                if (args.length < 1) {
                    s.sendMessage("/spawn [player]");
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        target.teleport(target.getWorld().getSpawnLocation());
                        s.sendMessage(ChatColor.GREEN + "Teleported "
                                + target.getName() + " to spawn!");
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("tpall")) {
            if (s instanceof Player) {
                if (CommandBin.plugin.pCheck(((Player) s),
                        "CommandBin.teleport.tpall")) {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        if (!p.getDisplayName().equals(((Player) s).getDisplayName())) {
                            p.teleport(((Player) s).getLocation());
                            p.sendMessage(ChatColor.GREEN + ((Player) s).getName()
                                    + " has teleported you!");
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Teleported everyone to you!");
                        }
                    }
                } else {
                    ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                }
            } else {
                s.sendMessage(ChatColor.RED
                        + "You must be in-game to teleport everyone to you!");
            }
        }

        if (l.equalsIgnoreCase("tpworld")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [world]");
                } else {
                    s.sendMessage("/tpworld [player] [world]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.teleport.world")) {
                        if (Bukkit.getServer().getWorld(args[0]) != null) {
                            ((Player) s).teleport(Bukkit.getServer().getWorld(args[0]).getSpawnLocation());
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Teleported to world '"
                                    + Bukkit.getServer().getWorld(args[0]).getName() + "'");
                        } else {
                            ((Player) s).sendMessage(ChatColor.RED + "World '"
                                    + args[0] + "' does not exist.");
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    if (Bukkit.getServer().getWorld(args[1]) != null) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.teleport(Bukkit.getServer().getWorld(args[1]).getSpawnLocation());
                            s.sendMessage(ChatColor.GREEN + "Teleported "
                                    + target.getName() + " to '" + args[1]
                                    + "'");
                        } else {
                            s.sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        s.sendMessage(ChatColor.RED
                                + "This world does not exist.");
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("shoot")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [player]");
                } else {
                    s.sendMessage("/shoot [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.shoot")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setVelocity(new Vector(0, 64, 0));
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You shot " + target.getName()
                                    + " into the air!");
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " shot you into the air!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        target.setVelocity(new Vector(0, 64, 0));
                        s.sendMessage(ChatColor.GREEN + "You shot "
                                + target.getName() + " into the air!");
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("strike")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [player]");
                } else {
                    s.sendMessage("/strike [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.strike")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.getWorld().strikeLightning(
                                    target.getLocation());
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You striked " + target.getName());
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName() + " striked you!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        target.getWorld().strikeLightning(target.getLocation());
                        s.sendMessage(ChatColor.GREEN
                                + "You struck lightning at " + target.getName());
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("time")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [day/night]");
                } else {
                    s.sendMessage("/time [day/night] [world]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.time")) {
                        if (args[0].equalsIgnoreCase("day")) {
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You have set it to day-time!");
                            ((Player) s).getWorld().setTime(0);
                        }

                        if (args[0].equalsIgnoreCase("night")) {
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You have set it to night-time!");
                            ((Player) s).getWorld().setTime(10000000);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    if (args[0].equalsIgnoreCase("day")) {
                        if (Bukkit.getServer().getWorld(args[1]) != null) {
                            Bukkit.getServer().getWorld(args[1]).setTime(0);
                            s.sendMessage(ChatColor.GREEN + "Set " + args[1]
                                    + "'s time to day!");
                        } else {
                            s.sendMessage(ChatColor.RED
                                    + "This world does not exist. Did you type it correctly?");
                        }
                    }

                    if (args[0].equalsIgnoreCase("night")) {
                        if (Bukkit.getServer().getWorld(args[1]) != null) {
                            Bukkit.getServer().getWorld(args[1]).setTime(100000000);
                            s.sendMessage(ChatColor.GREEN + "Set " + args[1]
                                    + "'s time to night!");
                        } else {
                            s.sendMessage(ChatColor.RED
                                    + "This world does not exist. Did you type it correctly?");
                        }
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("facepalm")) {
            if (s instanceof Player) {
                if (CommandBin.plugin.pCheck(((Player) s),
                        "CommandBin.general.facepalm")) {
                    Bukkit.getServer().broadcastMessage(
                            ((Player) s).getName() + " facepalm'd");
                } else {
                    ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                }
            } else {
                Bukkit.getServer().broadcastMessage(
                        ChatColor.RED + "Console /facepalm'd");
            }
        }

        if (l.equalsIgnoreCase("heal")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.heal.others")) {
                        ((Player) s).setHealth(20);
                        ((Player) s).sendMessage(ChatColor.GREEN
                                + "Your health bar is now full!");
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage("/heal [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.heal.others")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setHealth(20);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + target.getName()
                                    + "'s health is now full!");
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " restored your health!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        target.setHealth(20);
                        s.sendMessage(ChatColor.GREEN + target.getName()
                                + "'s health bar is now full!");
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("feed")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.feed")) {
                        ((Player) s).setFoodLevel(20);
                        ((Player) s).sendMessage(ChatColor.GREEN
                                + "Your food bar is now full!");
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage("/feed [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.feed.others")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setFoodLevel(20);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + target.getName()
                                    + "'s food bar is now full!");
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " set your food bar to full!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        target.setFoodLevel(20);
                        s.sendMessage(ChatColor.GREEN + target.getName()
                                + "'s food level is now full!");
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("god")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [on/off]");
                } else {
                    s.sendMessage("/god [on/off]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.god")) {
                        if (args[0].equalsIgnoreCase("on")) {
                            CommandBin.plugin.getConfig().set(
                                    ((Player) s).getName() + ".godmode", true);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Godmode enabled!");
                            CommandBin.plugin.saveConfig();
                        }

                        if (args[0].equalsIgnoreCase("off")) {
                            CommandBin.plugin.getConfig().set(
                                    ((Player) s).getName() + ".godmode", false);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Godmode disabled!");
                            CommandBin.plugin.saveConfig();
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage(ChatColor.RED
                            + "You can't turn godmode on in-console.");
                }
            }
        }

        if (l.equalsIgnoreCase("explode")) {
            if (args.length < 2) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [player] [radius]");
                } else {
                    s.sendMessage("/explode [player] [radius]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.explode")) {

                        float radius = Integer.parseInt(args[1]);

                        Player target = Bukkit.getServer().getPlayer(args[0]);

                        if (target != null) {
                            target.getWorld().createExplosion(
                                    target.getLocation(), radius);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You created a explosion at "
                                    + target.getName() + "'s location!");
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " created a explosion at your location.");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    float radius = Integer.parseInt(args[1]);

                    Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target != null) {
                        target.getWorld().createExplosion(target.getLocation(),
                                radius);
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("light")) {
            if (args.length < 2) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [player] [length in seconds]");
                } else {
                    s.sendMessage("/light [player] [length in seconds]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.light")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);

                        int i = Integer.parseInt(args[1]) * 10; // Always
                        // remember to
                        // multiply by
                        // 10,
                        // milliseconds
                        // ftw.

                        if (target != null) {
                            target.setFireTicks(i);
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    int i = Integer.parseInt(args[1]) * 10;
                    if (target != null) {
                        target.setFireTicks(i);
                        s.sendMessage(ChatColor.GREEN + "You set "
                                + target.getName() + " on fire for " + i
                                + " seconds!");
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("roll")) {
            if (args.length < 2) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [min] [max]");
                } else {
                    s.sendMessage("/roll [min] [max]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.roll")) {
                        Double rand = ((Math.random() * (Integer.parseInt(args[1]) - Integer.parseInt(args[0]))) + Integer.parseInt(args[0]));
                        long rounded = Math.round(rand);
                        Bukkit.getServer().broadcastMessage(
                                ChatColor.GREEN + ((Player) s).getName()
                                + " Rolled " + ChatColor.RED + args[0]
                                + ChatColor.GREEN + " and "
                                + ChatColor.RED + args[1]
                                + ChatColor.GREEN + " and got: "
                                + rounded);
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Double rand = ((Math.random() * (Integer.parseInt(args[1]) - Integer.parseInt(args[0]))) + Integer.parseInt(args[0]));
                    long rounded = Math.round(rand);
                    Bukkit.getServer().broadcastMessage(
                            ChatColor.GREEN + "Console rolled " + ChatColor.RED
                            + args[0] + ChatColor.GREEN + " and "
                            + ChatColor.RED + args[1] + ChatColor.GREEN
                            + " and got: " + rounded);
                }
            }
        }

        if (l.equalsIgnoreCase("smoke")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [on/off]");
                } else {
                    s.sendMessage("/smoke on/off]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.smoke")) {
                        if (args[0].equalsIgnoreCase("on")) {
                            CommandBin.plugin.getConfig().set(
                                    ((Player) s).getName() + ".smoke", true);
                            CommandBin.plugin.saveConfig();
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You are now a walking chimney! *giggles*");
                        }

                        if (args[0].equalsIgnoreCase("off")) {
                            CommandBin.plugin.getConfig().set(
                                    ((Player) s).getName() + ".smoke", false);
                            CommandBin.plugin.saveConfig();
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You are no longer the walking chimney!");
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage(ChatColor.RED
                            + "You can't turn smoke on in-console!");
                }
            }
        }

        if (l.equalsIgnoreCase("explosionstick")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [on/off]");
                } else {
                    s.sendMessage("/explosionstick [on/off]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.explosionstick")) {
                        if (args[0].equalsIgnoreCase("on")) {
                            CommandBin.plugin.getConfig().set(
                                    ((Player) s).getName() + ".explosionstick",
                                    true);
                            CommandBin.plugin.saveConfig();
                            ((Player) s).sendMessage(ChatColor.AQUA
                                    + "Explosion stick enabled");
                            ((Player) s).getInventory().addItem(
                                    new ItemStack(Material.STICK, 1));
                        }

                        if (args[0].equalsIgnoreCase("off")) {
                            CommandBin.plugin.getConfig().set(
                                    ((Player) s).getName() + ".explosionstick",
                                    false);
                            CommandBin.plugin.saveConfig();
                            ((Player) s).sendMessage(ChatColor.AQUA
                                    + "Explosion stick disabled");
                            ((Player) s).getInventory().remove(
                                    new ItemStack(Material.STICK, 1));
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("lightningstick")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [on/off]");
                } else {
                    s.sendMessage("/lightingstick [on/off]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.lightningstick")) {
                        if (args[0].equalsIgnoreCase("on")) {
                            CommandBin.plugin.getConfig().set(
                                    ((Player) s).getName() + ".lightningstick",
                                    true);
                            ((Player) s).sendMessage(ChatColor.AQUA
                                    + "Lightning stick enabled");
                            CommandBin.plugin.saveConfig();
                            ((Player) s).getInventory().addItem(
                                    new ItemStack(Material.STICK, 1));
                        }

                        if (args[0].equalsIgnoreCase("off")) {
                            CommandBin.plugin.getConfig().set(
                                    ((Player) s).getName() + ".lightningstick",
                                    false);
                            ((Player) s).sendMessage(ChatColor.AQUA
                                    + "Lightning stick disabled");
                            CommandBin.plugin.saveConfig();
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage(ChatColor.RED
                            + "Lightningstick can't be used in-console.");
                }
            }
        }

        if (l.equalsIgnoreCase("slap")) {
            if (args.length < 2) {
                if (s instanceof Player) {


                    ((Player) s).sendMessage("/" + l.toString()
                            + " [player] [hardness]");
                } else {
                    return false;
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.slap")) {
                        if (Integer.parseInt(args[1]) > 10000) {
                            Player p = (Player) s;
                            p.sendMessage(ChatColor.RED + "You can't use that high a number!");
                            return true;
                        }
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            double i = Integer.parseInt(args[1]) * 0.4;

                            target.setVelocity(new Vector(i, i, 0));
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName() + " slapped you!");
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You slapped " + target.getName());
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    if (Integer.parseInt(args[1]) > 10000) {
                        Player p = (Player) s;
                        p.sendMessage(ChatColor.RED + "You can't use that high a number!");
                        return true;
                    }
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        double i = Integer.parseInt(args[1]) * 0.4;
                        target.setVelocity(new Vector(i, i, 0));
                        s.sendMessage(ChatColor.GREEN + "You slapped "
                                + target.getName());
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("clear")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.clear")) {
                        ((Player) s).getInventory().clear();
                        ((Player) s).sendMessage(ChatColor.GREEN
                                + "Inventory cleared!");
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage("/clear [player]");
                }
            } else {
                if (s instanceof Player) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.clear.others")) {
                        if (target != null) {
                            target.getInventory().clear();
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " cleared your inventory!");
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Successfully cleared "
                                    + target.getName() + "'s inventory!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        target.getInventory().clear();
                        s.sendMessage(ChatColor.GREEN + "You cleared "
                                + target.getName() + "'s inventory!");
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("i")) // Incredibly messy, must get a decent /i
        // working.
        {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [name] [amount]");
                } else {
                    s.sendMessage("/i [name] [amount]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.item")) {
                        int id = -1;
                        int amount = 1;
                        try {
                            id = Integer.parseInt(args[0]);
                        } catch (NumberFormatException e) {
                            Material mat = Material.getMaterial(args[0].toUpperCase().replace(" ", "_"));
                            id = (mat != null ? mat.getId() : -1);
                        }
                        if (args.length > 1) {
                            try {
                                amount = Integer.parseInt(args[1]);
                            } catch (NumberFormatException e) {
                            }
                        }
                        if (id == -1) {
                            ((Player) s).sendMessage(ChatColor.RED
                                    + "This item does not exist.");
                            return false;
                        }
                        ((Player) s).getInventory().addItem(
                                new ItemStack(id, amount));
                        ((Player) s).sendMessage(ChatColor.GREEN
                                + "You obtained " + id);

                        if (CommandBin.plugin.getConfig().getBoolean("setting.debugitems")) {
                            System.out.println(((Player) s).getName() + " spawned " + args[1] + " of " + args[0]);
                            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                                if (p.isOp()) {
                                    p.sendMessage(((Player) s).getName() + " spawned " + args[1] + " of " + args[0]);
                                }
                            }
                        }
                        return true;
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage(ChatColor.RED
                            + "You can't spawn items in console");
                }
            }
        }

        if (l.equalsIgnoreCase("msg")) {
            if (args.length < 2) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [player] [message]");
                } else {
                    s.sendMessage("/msg [player] [message]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.msg")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            StringBuilder x = new StringBuilder();
                            int x2;
                            for (x2 = 1; x2 < args.length; x2++) {
                                x.append(args[x2]).append(" ");
                            }
                            String from = ChatColor.RED + "[FROM "
                                    + ((Player) s).getName() + "] "
                                    + ChatColor.WHITE + ": "
                                    + x.toString().trim();
                            String to = ChatColor.RED + "[TO "
                                    + target.getName() + "] " + ChatColor.WHITE
                                    + ": " + x.toString().trim();
                            String op = ChatColor.DARK_RED + "["
                                    + ((Player) s).getName() + " > "
                                    + target.getName() + "] "
                                    + x.toString().trim();

                            if (CommandBin.plugin.getConfig().getBoolean(
                                    "settings.opscanseepms")) {
                                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                                    if (p.isOp()) {
                                        p.sendMessage(op);
                                    }
                                }
                            }

                            target.sendMessage(from);
                            ((Player) s).sendMessage(to);
                            System.out.println(op);
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        StringBuilder x = new StringBuilder();
                        int x2;
                        for (x2 = 1; x2 < args.length; x2++) {
                            x.append(args[x2]).append(" ");
                        }
                        String from = ChatColor.RED + "[FROM CONSOLE] "
                                + ChatColor.WHITE + ": " + x.toString().trim();
                        String to = ChatColor.RED + "[TO " + target.getName()
                                + "] " + ChatColor.WHITE + ": "
                                + x.toString().trim();
                        String op = ChatColor.DARK_RED + "[CONSOLE > "
                                + target.getName() + "] " + x.toString().trim();

                        if (CommandBin.plugin.getConfig().getBoolean(
                                "settings.opscanseepms")) {
                            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                                if (p.isOp()) {
                                    p.sendMessage(op);
                                }
                            }
                        }

                        target.sendMessage(from);
                        ((Player) s).sendMessage(to);
                        System.out.println(op);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("nick")) {
            if (args.length < 2) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [player] [nickname]");
                } else {
                    s.sendMessage("/nick [player] [nickname]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.nick")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            if (args[1].length() < 20) {
                                target.setDisplayName(args[1]);
                                ((Player) s).sendMessage(ChatColor.GREEN
                                        + target.getName()
                                        + "'s name changed to " + args[1]);
                                target.sendMessage(ChatColor.GREEN
                                        + ((Player) s).getName()
                                        + " changed your name to " + args[1]);
                                CommandBin.plugin.getConfig().set(target.getName() + ".nickname",
                                        args[1]);
                                CommandBin.plugin.saveConfig();
                            } else {
                                ((Player) s).sendMessage(ChatColor.RED
                                        + "That name is too long. It must be below 20 characters. Sorry :(");
                            }
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        if (args[1].length() < 20) {
                            target.setDisplayName(args[1]);
                            CommandBin.plugin.getConfig().set(
                                    target.getName() + ".nickname", args[1]);
                            s.sendMessage(ChatColor.GREEN + target.getName()
                                    + "'s nickname is now " + args[1]);
                        } else {
                            s.sendMessage(ChatColor.RED
                                    + "The nickname must be below 20 letters!");
                        }
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("setxp")) {
            if (args.length < 2) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [player] [amount]");
                } else {
                    s.sendMessage("/setxp [player] [amount]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.setxp")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.setLevel(Integer.parseInt(args[1]));
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + target.getName()
                                    + "'s experience has been set to "
                                    + args[1]);
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " set your experience points to "
                                    + args[1]);
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        target.setExperience(Integer.parseInt(args[1]));
                        s.sendMessage(ChatColor.GREEN + target.getName()
                                + "'s experience is now " + args[1]);
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("kill")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [player]");
                } else {
                    s.sendMessage("/kill [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.kill")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            target.getWorld().strikeLightning(
                                    target.getLocation());
                            target.setHealth(0);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You killed " + target.getName());
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName() + " killed you!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        target.setHealth(0);
                        s.sendMessage(ChatColor.GREEN + "You killed "
                                + target.getName());
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("up")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [blocks]");
                } else {
                    s.sendMessage("/up [blocks]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.up")) {
                        ((Player) s).getLocation().getBlock().getRelative(0, Integer.parseInt(args[0]), 0).setType(Material.GLASS);
                        Location tpblock = ((Player) s).getLocation().getBlock().getRelative(0, Integer.parseInt(args[0]) + 2,
                                0).getLocation();
                        ((Player) s).teleport(tpblock);
                        ((Player) s).sendMessage(ChatColor.RED + "You went up "
                                + args[0] + " blocks!");
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage(ChatColor.RED + "You can't go up in console!");
                }
            }
        }

        if (l.equalsIgnoreCase("unlimited")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [player]");
                } else {
                    s.sendMessage("/unlimited [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.unlimited")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            CommandBin.plugin.getConfig().set(
                                    target.getName() + ".unlimited", true);
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " has given you unlimited block usage!");
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Successfully gave " + target.getName()
                                    + " unlimited block usage!");
                            CommandBin.plugin.saveConfig();
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        CommandBin.plugin.getConfig().set(
                                target.getName() + ".unlimited", true);
                        s.sendMessage(ChatColor.GREEN + "You gave "
                                + target.getName() + " unlimited block usage!");
                        CommandBin.plugin.saveConfig();
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("delunlimited")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString() + " [player]");
                } else {
                    s.sendMessage("/delunlimited [player]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.unlimited")) {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target != null) {
                            CommandBin.plugin.getConfig().set(
                                    target.getName() + ".unlimited", false);
                            target.sendMessage(ChatColor.GREEN
                                    + ((Player) s).getName()
                                    + " has removed your unlimited block usage!");
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "Successfully removed "
                                    + target.getName()
                                    + "'s unlimited block usage!");
                            CommandBin.plugin.saveConfig();
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.PlayerOffline);
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        CommandBin.plugin.getConfig().set(
                                target.getName() + ".unlimited", false);
                        s.sendMessage(ChatColor.GREEN + "You removed "
                                + target.getName()
                                + "'s unlimited block usage!");
                        CommandBin.plugin.saveConfig();
                    } else {
                        s.sendMessage(CommandBin.plugin.PlayerOffline);
                    }
                }
            }
        }

        if (l.equalsIgnoreCase("mytime")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [day/night/reset]");
                } else {
                    s.sendMessage("/mytime [day,night,reset]");
                }
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.mytime")) {
                        if (args[0].equalsIgnoreCase("day")) {
                            ((Player) s).setPlayerTime(0, false);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You set your time to day!");
                        }

                        if (args[0].equalsIgnoreCase("night")) {
                            ((Player) s).setPlayerTime(100000000, false);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You set your time to night!");
                        }

                        if (args[0].equalsIgnoreCase("reset")) {
                            ((Player) s).setPlayerTime(((Player) s).getWorld().getTime(), true);
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You reset your time to the server time!");
                        }
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    s.sendMessage(ChatColor.RED
                            + "You must be in-game to set your own time.");
                }
            }
        }

        if (l.equalsIgnoreCase("armour")) {
            if (args.length < 1) {
                if (s instanceof Player) {
                    ((Player) s).sendMessage("/" + l.toString()
                            + " [leather, iron, diamond, gold, chainmail");
                } else {
                    s.sendMessage("/armour [leather, iron, diamond, gold, chainmail");
                }
            } else {
                if (s instanceof Player) {
                    if (args[0].equalsIgnoreCase("leather")) {
                        if (CommandBin.plugin.pCheck(((Player) s),
                                "CommandBin.general.armour.leather")) {
                            ((Player) s).getInventory().setHelmet(
                                    new ItemStack(Material.LEATHER_HELMET, 1));
                            ((Player) s).getInventory().setChestplate(
                                    new ItemStack(Material.LEATHER_CHESTPLATE,
                                    1));
                            ((Player) s).getInventory().setLeggings(
                                    new ItemStack(
                                    Material.LEATHER_LEGGINGS,
                                    1));
                            ((Player) s).getInventory().setBoots(
                                    new ItemStack(Material.LEATHER_BOOTS, 1));
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You received " + args[0] + " armour!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                        }
                    }

                    if (args[0].equalsIgnoreCase("iron")) {
                        if (CommandBin.plugin.pCheck(((Player) s),
                                "CommandBin.general.armour.iron")) {
                            ((Player) s).getInventory().setHelmet(
                                    new ItemStack(Material.IRON_HELMET, 1));
                            ((Player) s).getInventory().setChestplate(
                                    new ItemStack(Material.IRON_CHESTPLATE, 1));
                            ((Player) s).getInventory().setLeggings(
                                    new ItemStack(Material.IRON_LEGGINGS, 1));
                            ((Player) s).getInventory().setBoots(
                                    new ItemStack(Material.IRON_BOOTS, 1));
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You received " + args[0] + " armour!");

                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                        }
                    }

                    if (args[0].equalsIgnoreCase("diamond")) {
                        if (CommandBin.plugin.pCheck(((Player) s),
                                "CommandBin.general.armour.diamond")) {
                            ((Player) s).getInventory().setHelmet(
                                    new ItemStack(Material.DIAMOND_HELMET, 1));
                            ((Player) s).getInventory().setChestplate(
                                    new ItemStack(Material.DIAMOND_CHESTPLATE,
                                    1));
                            ((Player) s).getInventory().setLeggings(
                                    new ItemStack(
                                    Material.DIAMOND_LEGGINGS,
                                    1));
                            ((Player) s).getInventory().setBoots(
                                    new ItemStack(Material.DIAMOND_BOOTS, 1));
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You received " + args[0] + " armour!");
                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                        }
                    }

                    if (args[0].equalsIgnoreCase("gold")) {
                        if (CommandBin.plugin.pCheck(((Player) s),
                                "CommandBin.general.armour.gold")) {
                            ((Player) s).getInventory().setHelmet(
                                    new ItemStack(Material.GOLD_HELMET, 1));
                            ((Player) s).getInventory().setChestplate(
                                    new ItemStack(Material.GOLD_CHESTPLATE, 1));
                            ((Player) s).getInventory().setLeggings(
                                    new ItemStack(Material.GOLD_LEGGINGS, 1));
                            ((Player) s).getInventory().setBoots(
                                    new ItemStack(Material.GOLD_BOOTS, 1));
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You received " + args[0] + " armour!");

                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                        }
                    }

                    if (args[0].equalsIgnoreCase("chainmail")) {
                        if (CommandBin.plugin.pCheck(((Player) s),
                                "CommandBin.general.armour.chainmail")) {
                            ((Player) s).getInventory().setHelmet(
                                    new ItemStack(
                                    Material.CHAINMAIL_HELMET,
                                    1));
                            ((Player) s).getInventory().setChestplate(
                                    new ItemStack(
                                    Material.CHAINMAIL_CHESTPLATE, 1));
                            ((Player) s).getInventory().setLeggings(
                                    new ItemStack(Material.CHAINMAIL_LEGGINGS,
                                    1));
                            ((Player) s).getInventory().setBoots(
                                    new ItemStack(Material.CHAINMAIL_BOOTS, 1));
                            ((Player) s).sendMessage(ChatColor.GREEN
                                    + "You received " + args[0] + " armour!");

                        } else {
                            ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                        }
                    }
                } else {
                    s.sendMessage(ChatColor.RED
                            + "It would be awesome if you could wear chainmail in console, but you can't!");
                }
            }
        }

        if (l.equalsIgnoreCase("me")) {
            if (args.length < 1) {
                return false;
            } else {
                if (s instanceof Player) {
                    if (CommandBin.plugin.pCheck(((Player) s),
                            "CommandBin.general.me")) {
                        StringBuilder x = new StringBuilder();

                        for (String a : args) {
                            x.append(a).append(" ");
                        }

                        Bukkit.getServer().broadcastMessage(
                                ChatColor.AQUA + "* " + ((Player) s).getDisplayName() + " "
                                + x.toString().trim());
                    } else {
                        ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                    }
                } else {
                    StringBuilder x = new StringBuilder();
                    int i;
                    for (i = 0; i < args.length; i++) {
                        x.append(args[i]).append(" ");
                    }
                    Bukkit.getServer().broadcastMessage(
                            "* " + ChatColor.RED + "Console: "
                            + ChatColor.WHITE + x.toString().trim());
                }
            }
        }

        if (l.equalsIgnoreCase("more")) {
            if (s instanceof Player) {
                if (CommandBin.plugin.pCheck(((Player) s),
                        "CommandBin.general.more")) {
                    ((Player) s).getInventory().addItem(
                            new ItemStack(((Player) s).getItemInHand().getType(), 64));
                    ((Player) s).sendMessage(ChatColor.GREEN
                            + "Received 64 of what you're holding!");
                } else {
                    ((Player) s).sendMessage(CommandBin.plugin.NoPermission);
                }
            } else {
                s.sendMessage(ChatColor.RED + "You can't do that in-console!");
            }
        }
        return false;
    }
}