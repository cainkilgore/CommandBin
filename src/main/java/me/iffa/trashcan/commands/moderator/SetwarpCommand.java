// Package Declaration
package me.iffa.trashcan.commands.moderator;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /setwarp.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SetwarpCommand extends TrashCommand {
    /**
     * Constructor of SetwarpCommand.
     * 
     * @param label Command label
     */
    public SetwarpCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if(!(cs instanceof Player)) {
            MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
            return true;
        }
        Player player = (Player) cs;
        if (!player.hasPermission("trashcan.moderation.setwarp")) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        } else {
            if (TrashCan.getConfigHandler().getWarp(args[0]) != null) {
                MessageUtil.sendMessage(player, ChatColor.RED + "Warp with name '" + args[0] + "' already exists!");
                return true;
            }
            TrashCan.getConfigHandler().setWarp(player.getLocation(), args[0]);
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Warp with name '" + args[0] + "' has been set!");
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /setwarp <name>");
    }
    
}
