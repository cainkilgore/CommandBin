// Package Declaration
package me.iffa.trashcan.commands.moderator;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /kill.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class KillCommand extends TrashCommand {
    /**
     * Constructor of KillCommand.
     * 
     * @param label Command label
     */
    public KillCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.moderation.kill")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
            return true;
        }
        target.setHealth(0);
        MessageUtil.sendMessage(cs, ChatColor.GOLD + "'" + target.getName() + "' was killed!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /kill <player>");
    }
    
}
