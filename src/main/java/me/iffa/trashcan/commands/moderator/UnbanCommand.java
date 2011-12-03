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
 * Represents /unban.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class UnbanCommand extends TrashCommand {
    /**
     * Constructor of UnbanCommand.
     * 
     * @param label Command label
     */
    public UnbanCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.moderation.unban")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        
        if (args.length >= 1) {
            if (!TrashCan.getConfigHandler().getBanned(args[0])) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player '" + args[0] + "' is not banned!");
                return true;
            }
            TrashCan.getConfigHandler().setBanned(false, args[0], null);
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "Unbanned '" + args[0] + "'.");
            return true;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /unban <player>");
    }
    
}
