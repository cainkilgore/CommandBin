// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /up.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class UpCommand extends TrashCommand {
    /**
     * Constructor of UpCommand.
     * 
     * @param label Command label
     */
    public UpCommand(String label) {
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
        if (!cs.hasPermission("trashcan.general.up")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        int amount = 0;
        try {
            amount = Integer.parseInt(args[0]);
        } catch(NumberFormatException ex) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "Invalid amount '" + args[0] + "'!");
            return true;
        }
        Player player = (Player) cs;
        Location to = player.getLocation();
        to.setY(to.getY() + amount);
        player.teleport(to);
        MessageUtil.sendMessage(player, ChatColor.GOLD + "Teleported!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /up [amount]");
    }
    
}
