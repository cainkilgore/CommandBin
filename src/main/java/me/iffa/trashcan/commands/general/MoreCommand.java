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
 * Represents /more.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class MoreCommand extends TrashCommand {
    /**
     * Constructor of MoreCommand.
     * 
     * @param label Command label
     */
    public MoreCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!(cs instanceof Player)) {
            MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
            return true;
        }
        if (!cs.hasPermission("trashcan.general.more")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        Player player = (Player) cs;
        if (player.getItemInHand() == null) {
            MessageUtil.sendMessage(player, ChatColor.RED + "Can't give you more of air!");
            return true;
        }
        player.getItemInHand().setAmount(64);
        MessageUtil.sendMessage(player, ChatColor.GOLD + "You've been given more of " + player.getItemInHand().getType().toString() + "!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /more");
    }
    
}
