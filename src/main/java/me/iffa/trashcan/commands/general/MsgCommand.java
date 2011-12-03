// Package Declaration
package me.iffa.trashcan.commands.general;

// Java Imports
import java.util.logging.Level;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.LoggerUtil;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /msg.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class MsgCommand extends TrashCommand {
    /**
     * Constructor of MsgCommand.
     * 
     * @param label Command label
     */
    public MsgCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.general.msg")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 2) {
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null && !args[0].equalsIgnoreCase("console")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
            return true;
        }
        String message = "";
        for (int arg = 1; arg < args.length; arg++) {
            message = message + " " + args[arg];
        }
        if (target == null) {
            LoggerUtil.log(Level.INFO, "From " + cs.getName() + ":" + message);
        } else {
            MessageUtil.sendMessage(target, ChatColor.GOLD + "From " + cs.getName() + ":" + ChatColor.GRAY + message);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /msg <player/console> <message>");
    }
    
}
