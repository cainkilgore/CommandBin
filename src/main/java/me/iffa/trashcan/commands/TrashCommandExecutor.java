// Package Declaration
package me.iffa.trashcan.commands;

// Java Imports
import java.util.logging.Level;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.utils.LoggerUtil;

// Bukkit Imports
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * CommandExecutor for all commands.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class TrashCommandExecutor implements CommandExecutor {
    /**
     * @see org.bukkit.command.CommandExecutor
     * 
     * @param cs Command sender
     * @param cmnd Command
     * @param label Command label
     * @param args Command arguments
     * 
     * @return True if no usage information should be sent to the Commandsender
     */
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String label, String[] args) {
        TrashCommand command = TrashCan.matchCommand(label);
        if (command != null) {
            if (!command.executeCommand(cs, args)) {
                if (TrashCan.getConfigHandler().getLogCommands()) {
                    LoggerUtil.log(Level.INFO, cs.getName() + " used command '" + label + "'. Result: failure");
                }
                command.sendUsage(cs);
            } else {
                if (TrashCan.getConfigHandler().getLogCommands()) {
                    LoggerUtil.log(Level.INFO, cs.getName() + " used command '" + label + "'. Result: success");
                }
            }
        }
        return true;
    }
    
}
