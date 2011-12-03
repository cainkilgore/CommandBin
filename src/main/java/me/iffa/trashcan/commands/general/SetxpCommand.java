// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /setxp.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SetxpCommand extends TrashCommand {
    /**
     * Constructor of SetxpCommand.
     * 
     * @param label Command label
     */
    public SetxpCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length < 1) {
            return false;
        } else {
            if (args.length == 1) {
                if (!(cs instanceof Player)) {
                    MessageUtil.sendMessage(cs, "Sorry, only players can use this command!");
                    return true;
                }
                if (!cs.hasPermission("trashcan.general.setxp")) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                    return true;
                }
                Player player = (Player) cs;
                int amount = 0;
                try {
                    amount = Integer.parseInt(args[0]);
                } catch (NumberFormatException ex) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "Invalid amount '" + args[0] + "'!");
                    return true;
                }
                player.setExperience(amount);
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Your experience has been set to '" + amount + "'!");
            } else {
                if (!cs.hasPermission("trashcan.general.setxp.others")) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
                    return true;
                }
                int amount = 0;
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException ex) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "Invalid amount '" + args[1] + "'!");
                    return true;
                }
                target.setExperience(amount);
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Set '" + target.getName() + "'s experience to '" + amount + "'!");
            }
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /setxp <amount> / <player> <amount>");
    }
    
}
