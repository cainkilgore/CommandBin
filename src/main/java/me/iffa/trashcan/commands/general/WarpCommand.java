// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /warp.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class WarpCommand extends TrashCommand {
    /**
     * Constructor of WarpCommand.
     * 
     * @param label Command label
     */
    public WarpCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!(cs instanceof Player)) {
            MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
            return true;
        }
        if (args.length < 1) {
            return false;
        } else {
            Player player = (Player) cs;
            if (!player.hasPermission("trashcan.general.warp")) {
                MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
                return true;
            }
            if (!TrashCan.getConfigHandler().hasWarps()) {
                MessageUtil.sendMessage(player, ChatColor.RED + "No warps are set.");
                return true;
            }
            Location warp = TrashCan.getConfigHandler().getWarp(args[0]);
            if (warp == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "Warp '" + args[0] + "' does not exist!");
                return true;
            }
            player.teleport(warp);
            MessageUtil.sendMessage(player, ChatColor.GOLD + "You've been teleported to warp '" + args[0] + "'!");
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /warp <name>");
    }
}
