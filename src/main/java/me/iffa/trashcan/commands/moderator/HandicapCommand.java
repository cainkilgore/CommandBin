// Package Declaration
package me.iffa.trashcan.commands.moderator;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /handicap.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class HandicapCommand extends TrashCommand {
    /**
     * Constructor of HandicapCommand.
     * 
     * @param label Command label
     */
    public HandicapCommand(String label) {
        super(label);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.moderation.handicap")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
            return true;
        }
        TrashCan.getConfigHandler().setHandicapped(!TrashCan.getConfigHandler().getHandicapped(target), target);
        if (TrashCan.getConfigHandler().getHandicapped(target)) {
            MessageUtil.sendMessage(target, ChatColor.RED + "You have been handicapped!");
        } else {
            MessageUtil.sendMessage(target, ChatColor.GOLD + "You have been unhandicapped!");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /handicap <player>");
    }
    
}
