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
 * Represents /kick.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class KickCommand extends TrashCommand {
    /**
     * Constructor of KickCommand.
     * 
     * @param label Command label
     */
    public KickCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.moderation.kick")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        
        if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player '" + args[0] + "' was not found!");
                return true;
            } 
            Player target = Bukkit.getPlayer(args[0]);
            target.kickPlayer("You've been kicked.");
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "The player '" + target.getName() + "' has been kicked.");
            if (TrashCan.getConfigHandler().getBroadcastKick()) {
                Bukkit.broadcastMessage(ChatColor.RED + "'" + target.getName() + "' has been kicked. Reason: Unspecified");
            }
            return true;
        } else {
            if (Bukkit.getPlayer(args[0]) == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player '" + args[0] + "' was not found!");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            String reason = "";
            for (int arg = 1; arg < args.length; arg++) {
                reason = reason + " " + args[arg];
            }
            target.kickPlayer("You've been kicked:" + reason);
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "The player '" + target.getName() + "' has been kicked.");
            if (TrashCan.getConfigHandler().getBroadcastKick()) {
                Bukkit.broadcastMessage(ChatColor.RED + "'" + target.getName() + "' has been kicked. Reason:" + reason);
            }
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /kick <player> [reason]");
    }
    
}
