// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /clear.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class ClearCommand extends TrashCommand {
    /**
     * Constructor of ClearCommand.
     * 
     * @param label Command label
     */
    public ClearCommand(String label) {
        super(label);
    }

    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!(cs instanceof Player)) {
            MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
            return true;
        }
        ((Player)cs).getInventory().clear();
        MessageUtil.sendMessage(cs, ChatColor.GOLD + "Your inventory has been cleared!");
        return true;
    }

    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /clear");
    }
    
}
