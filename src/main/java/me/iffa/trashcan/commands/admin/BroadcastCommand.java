// Package Declaration
package me.iffa.trashcan.commands.admin;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents /broadcast.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class BroadcastCommand extends TrashCommand {
    /**
     * Constructor of BroadcastCommand.
     * 
     * @param label 
     */
    public BroadcastCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.admin.broadcast")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        String message = "";
        for (int arg = 0; arg < args.length; arg++) {
            message = message + " " + args[arg];
        }
        Bukkit.broadcastMessage(TrashCan.getConfigHandler().getFormattedBroadcast(message));
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /broadcast <message>");
    }
    
}
