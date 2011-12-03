// Package Declaration
package me.iffa.trashcan.commands.general.tp;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /tphere.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class TeleporthereCommand extends TrashCommand {
    /**
     * Constructor of TeleporthereCommand.
     * 
     * @param label Command label 
     */
    public TeleporthereCommand(String label) {
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
        Player player = (Player) cs;
        if (!player.hasPermission("trashcan.general.tphere")) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            MessageUtil.sendMessage(player, ChatColor.RED + "The player was not found!");
            return true;
        }
        target.teleport(player);
        MessageUtil.sendMessage(player, ChatColor.GOLD + "Teleported!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /tphere <player>");
    }
    
}
