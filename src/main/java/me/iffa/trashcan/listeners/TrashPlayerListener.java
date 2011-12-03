// Package Declaration
package me.iffa.trashcan.listeners;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Player listener for features like freezing, smoking, snowman, ban & kick etc.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class TrashPlayerListener extends PlayerListener {
    /**
     * Called when a player moves.
     * 
     * @param e Event data
     */
    @Override
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (TrashCan.getConfigHandler().getFrozen(e.getPlayer())) {
            e.setCancelled(true);
        }
        if (TrashCan.getConfigHandler().getSmoke(e.getPlayer())) {
            e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 5);
        }
        if (TrashCan.getConfigHandler().getSnowman(e.getPlayer())) {
            if (!(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() == Material.AIR)) {
                e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 0).setType(Material.SNOW);
            }
            if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() == Material.SNOW) {
                e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).setType(Material.SNOW_BLOCK);
            }
        }
    }

    /**
     * Called when a command is used.
     * 
     * @param e Event data
     */
    @Override
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        if (TrashCan.getConfigHandler().getHandicapped(e.getPlayer())) {
            e.getPlayer().sendMessage(TrashCan.getConfigHandler().getNoCommandsMessage());
            e.setCancelled(true);
        }
    }

    /**
     * Called when a player chats.
     * 
     * @param e Event data
     */
    @Override
    public void onPlayerChat(PlayerChatEvent e) {
        if (TrashCan.getConfigHandler().getMuted(e.getPlayer())) {
            e.getPlayer().sendMessage(TrashCan.getConfigHandler().getMutedMessage());
            e.setCancelled(true);
        }
        // Removed commented customchat-implementation. Waiting for Cain's 
        // implementation.
    }

    /**
     * Called when a player interacts.
     * 
     * @param e Event data
     */
    @Override
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (TrashCan.getConfigHandler().getExplosionStick(e.getPlayer())) {
                if (e.getPlayer().getItemInHand().getType() == Material.STICK) {
                    Location block = e.getPlayer().getTargetBlock(null, 0).getLocation();
                    e.getPlayer().getWorld().createExplosion(block, 8);
                }
            }

            if (TrashCan.getConfigHandler().getLightningStick(e.getPlayer())) {
                if (e.getPlayer().getItemInHand().getType() == Material.STICK) {
                    Location block = e.getPlayer().getTargetBlock(null, 0).getLocation();
                    e.getPlayer().getWorld().strikeLightning(block);
                }
            }
        }
        
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && TrashCan.getConfigHandler().getFrozen(e.getPlayer())) {
            e.setCancelled(true);
        }

        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (TrashCan.getConfigHandler().getEnderSpawnOnEgg()) {
                if (e.getClickedBlock().getType() == Material.DRAGON_EGG) {
                    Location dragon = e.getClickedBlock().getLocation();
                    e.getPlayer().getWorld().spawnCreature(dragon, CreatureType.ENDER_DRAGON);
                    e.getPlayer().sendMessage(ChatColor.GOLD + "You have made the Enderdragon arise from The End!");
                    e.getClickedBlock().setType(Material.AIR);
                }
            }
        }
    }

    /**
     * Called when a player logins to the server.
     * 
     * @param e Event data
     */
    @Override
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (TrashCan.getConfigHandler().getBanned(e.getPlayer())) {
            e.disallow(Result.KICK_BANNED, "You are banned: " + TrashCan.getConfigHandler().getBanReason(e.getPlayer()));
        }
    }

    /**
     * Called when a player joins the game.
     * 
     * @param e Event data
     */
    @Override
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (TrashCan.getConfigHandler().getIPBanned(e.getPlayer())) {
            e.getPlayer().kickPlayer("You are IP banned!");
            return;
        }
        e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " " + TrashCan.getConfigHandler().getJoinMessage());

        if (TrashCan.getConfigHandler().getNick(e.getPlayer()) != null) {
            e.getPlayer().setDisplayName(TrashCan.getConfigHandler().getNick(e.getPlayer()));
        }
        if (e.getPlayer().hasPermission("trashcan.other.strike-on-join")) { // 1.31
            e.getPlayer().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
        }
        
        // Sending MOTD to player.
        String motd = TrashCan.getConfigHandler().getMOTD();
        for (String str : motd.split("/break")) {
            e.getPlayer().sendMessage(str.replace("[p]", e.getPlayer().getName()));
        }
        
    }

    /**
     * Called when a player quits the game.
     * 
     * @param e Event data
     */
    @Override
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + " " + TrashCan.getConfigHandler().getLeaveMessage());
    }

    /**
     * Called when a player throws an egg.
     * 
     * @param e Event data
     */
    @Override
    public void onPlayerEggThrow(PlayerEggThrowEvent e) {
        if (TrashCan.getConfigHandler().getTeleportOnThrow()) {
            if (e.getPlayer().hasPermission("trashcan.fun.tponeggthrow")) {
                e.getPlayer().teleport(e.getEgg().getLocation());
                e.getPlayer().sendMessage(ChatColor.GOLD + "Teleported to the egg!");
            }
        }
    }
}
