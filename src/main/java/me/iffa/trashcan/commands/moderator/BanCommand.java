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
 * Represents /ban.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class BanCommand extends TrashCommand {
    /**
     * Constructor of BanCommand.
     * 
     * @param label Command label
     */
    public BanCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.moderation.ban")) {
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
            TrashCan.getConfigHandler().setBanned(true, target, "Unspecified");
            target.kickPlayer("You've been banned.");
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "The player '" + target.getName() + "' has been banned.");
            if (TrashCan.getConfigHandler().getBroadcastBan()) {
                Bukkit.broadcastMessage(ChatColor.RED + "'" + target.getName() + "' has been banned. Reason: Unspecified");
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
            TrashCan.getConfigHandler().setBanned(true, target, reason);
            target.kickPlayer("You've been banned:" + reason);
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "The player '" + target.getName() + "' has been banned.");
            if (TrashCan.getConfigHandler().getBroadcastBan()) {
                Bukkit.broadcastMessage(ChatColor.RED + "'" + target.getName() + "' has been banned. Reason:" + reason);
            }
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /ban <player> [reason]");
    }
    
}
