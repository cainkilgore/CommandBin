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
 * Represents /tpall.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class TeleportallCommand extends TrashCommand {
    /**
     * Constructor of TeleportallCommand.
     * 
     * @param label Command label 
     */
    public TeleportallCommand(String label) {
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
        if (!player.hasPermission("trashcan.general.tpall")) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
            return true;
        }
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (!target.equals(player)) {
                target.teleport(player);
            }
        }
        MessageUtil.sendMessage(player, ChatColor.GOLD + "Teleported!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /tpall");
    }
    
}
