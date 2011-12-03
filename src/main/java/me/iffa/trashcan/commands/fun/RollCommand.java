// Package Declaration
package me.iffa.trashcan.commands.fun;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /roll.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class RollCommand extends TrashCommand {
    /**
     * Constructor of RollCommand.
     * 
     * @param label Command label
     */
    public RollCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.fun.roll")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 2) {
            return false;
        }
        int arg1 = 0;
        int arg2 = 0;
        try {
            arg1 = Integer.parseInt(args[0]);
            arg2 = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "Invalid arguments!");
            return true;
        }
        long result = Math.round(((Math.random() * (arg2) - arg1)) + arg1);
        Bukkit.broadcastMessage(ChatColor.GOLD + (cs instanceof Player ? ((Player)cs).getName() : "Console") + " rolled " + result + "!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /roll <min> <max>");
    }
    
}
