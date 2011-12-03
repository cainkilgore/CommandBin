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
 * Represents /snowman.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SnowmanCommand extends TrashCommand {
    /**
     * Constructor of SnowmanCommand.
     * 
     * @param label Command label
     */
    public SnowmanCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (cs.hasPermission("trashcan.fun.snowman")) {
            if (!(cs instanceof Player)) {
                MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
                return true;
            }
            TrashCan.getConfigHandler().setSnowman(!TrashCan.getConfigHandler().getSnowman((Player)cs), (Player)cs);
            if (TrashCan.getConfigHandler().getSnowman((Player)cs)) {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "You are now Frosty The Snowman! Start walking!");
            } else {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "You are no longer Frosty The Snowman. :(");
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
