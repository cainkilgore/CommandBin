// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /unlimited.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class UnlimitedCommand extends TrashCommand {
    /**
     * Constructor of UnlimitedCommand.
     * 
     * @param label Command label
     */
    public UnlimitedCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length < 1) {
            if (!(cs instanceof Player)) {
                MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
                return true;
            }
            if (!cs.hasPermission("trashcan.general.unlimited")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            TrashCan.getConfigHandler().setUnlimited(!TrashCan.getConfigHandler().getUnlimited((Player)cs), (Player)cs);
            if (TrashCan.getConfigHandler().getUnlimited((Player)cs)) {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Unlimited has been enabled for you!");
            } else {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Unlimited has been disabled for you!");
            }
            return true;
        } else {
            if (!cs.hasPermission("trashcan.general.unlimited.others")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
                return true;
            }
            TrashCan.getConfigHandler().setUnlimited(!TrashCan.getConfigHandler().getUnlimited(target), target);
            if (TrashCan.getConfigHandler().getUnlimited(target)) {
                MessageUtil.sendMessage(target, ChatColor.GOLD + "Unlimited drops has been enabled for you!");
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Unlimited drops has been enabled for '" + target.getName() + "'!");
            } else {
                MessageUtil.sendMessage(target, ChatColor.GOLD + "Unlimited drops has been disabled for you!");
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Unlimited drops has been disabled for '" + target.getName() + "'!");
            }
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /unlimited [player]");
    }
    
}
