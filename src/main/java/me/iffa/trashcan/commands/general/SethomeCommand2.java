// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /sethome. (non multihome)
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SethomeCommand2 extends TrashCommand {
    /**
     * Constructor of SethomeCommand2.
     * @param label 
     */
    public SethomeCommand2(String label) {
        super(label);
    }

    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!(cs instanceof Player)) {
            MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
            return true;
        }
        if (!cs.hasPermission("trashcan.general.sethome")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        TrashCan.getConfigHandler().setHome((Player)cs, ((Player)cs).getLocation());
        MessageUtil.sendMessage(cs, ChatColor.GOLD + "Your home has been set!");
        return true;
    }

    @Override
    public void sendUsage(CommandSender cs) {
    }
    
}
