// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents /usage.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class UsageCommand extends TrashCommand {
    /**
     * Constructor of UsageCommand.
     * 
     * @param label Command label
     */
    public UsageCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length < 1) {
            return false;
        }
        TrashCommand command = TrashCan.matchCommand(args[0]);
        if (command == null) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "'" + args[0] + "' is not a command!");
            return true;
        }
        command.sendUsage(cs);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /usage <command>");
    }
    
}
