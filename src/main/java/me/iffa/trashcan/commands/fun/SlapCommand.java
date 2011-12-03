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
import org.bukkit.util.Vector;

/**
 * Represents /slap.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SlapCommand extends TrashCommand {
    /**
     * Constructor of SlapCommand.
     * 
     * @param label Command label
     */
    public SlapCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length < 1) {
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
            return true;
        }
        int hardness = 2;
        try {
            hardness = Integer.parseInt(args[1]);
        } catch (Exception ex) {
            if (ex instanceof NumberFormatException) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "Invalid hardness '" + args[1] + "'!");
                return true;
            }
        }
        target.setVelocity(new Vector(hardness, hardness, 0));
        MessageUtil.sendMessage(cs, ChatColor.GOLD + "You've slapped '" + target.getName() + "'!");
        MessageUtil.sendMessage(target, ChatColor.GOLD + "You've been slapped!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /slap <player> [hardness]");
    }
    
}
