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
 * Represents /shoot.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class ShootCommand extends TrashCommand {
    /**
     * Constructor of ShootCommand.
     * 
     * @param label Command label
     */
    public ShootCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.fun.shoot")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
                return true;
            }
            target.setVelocity(new Vector(0, 64, 0));
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "'" + target.getName() + "' has been shot into the air!");
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /shoot <player>");
    }
    
}
