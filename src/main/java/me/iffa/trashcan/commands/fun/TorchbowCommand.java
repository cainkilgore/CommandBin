// Package Declaration
package me.iffa.trashcan.commands.fun;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /torchbow.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class TorchbowCommand extends TrashCommand {
    /**
     * Constructor of TorchbowCommand.
     * 
     * @param label Command label
     */
    public TorchbowCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (cs.hasPermission("trashcan.fun.torchbow")) {
            if (!(cs instanceof Player)) {
                MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
                return true;
            }
            TrashCan.getConfigHandler().setTorchbow(!TrashCan.getConfigHandler().getTorchbow((Player)cs), (Player)cs);
            if (TrashCan.getConfigHandler().getTorchbow((Player)cs)) {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Torchbow has been enabled for you!");
            } else {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Torchbow has been disabled for you!");
            }
            return true;
        } else {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
    }
    
}
