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
 * Represents /sethome.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SethomeCommand extends TrashCommand {
    /**
     * Constructor of SethomeCommand.
     * 
     * @param label Command label
     */
    public SethomeCommand(String label) {
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
        if (!player.hasPermission("trashcan.general.sethome")) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        } else {
            if (TrashCan.getConfigHandler().getHome(args[0], player) != null) {
                MessageUtil.sendMessage(player, ChatColor.RED + "Home with name '" + args[0] + "' already exists!");
                return true;
            }
            TrashCan.getConfigHandler().setHome(player, player.getLocation(), args[0]);
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Home with name '" + args[0] + "' has been set!");
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /sethome <name>");
    }
    
}
