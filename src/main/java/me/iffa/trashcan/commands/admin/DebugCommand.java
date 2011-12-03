// Package Declaration
package me.iffa.trashcan.commands.admin;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents /debug.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class DebugCommand extends TrashCommand {
    /**
     * Constructor of DebugCommand.
     * 
     * @param label Command label
     */
    public DebugCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length < 1) {
            if (cs.hasPermission("trashcan.admin.debug")) {
                Runtime rt = Runtime.getRuntime();
                double max = Math.floor(rt.maxMemory() / 1024.0 / 1024.0);
                double free = Math.floor(rt.freeMemory() / 1024.0 / 1024.0);
                MessageUtil.sendMessage(cs, ChatColor.GRAY + "" + ChatColor.GOLD + free + ChatColor.GRAY + " out of " + ChatColor.GOLD + max + ChatColor.GRAY + " memory available."); // "" because of Java stupidness
            } else {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            }
            return true;
        } else {
            if (args[0].equalsIgnoreCase("ping")) {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Here is your pong.");
                return true;
            }
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /debug [ping]");
    }
}
