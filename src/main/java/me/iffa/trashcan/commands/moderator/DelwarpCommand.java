// Package Declaration
package me.iffa.trashcan.commands.moderator;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents /delwarp.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class DelwarpCommand extends TrashCommand {
    /**
     * Constructor of DelwarpCommand.
     * 
     * @param label Command label
     */
    public DelwarpCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.moderation.delwarp")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        } else {
            if (TrashCan.getConfigHandler().getWarp(args[0]) == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "Warp with name '" + args[0] + "' doesn't exist!");
                return true;
            }
            TrashCan.getConfigHandler().removeWarp(args[0]);
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "Warp '" + args[0] + "' has been deleted!");
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /delwarp <name>");
    }
    
}
