// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /home. (non multihome)
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class HomeCommand2 extends TrashCommand {
    /**
     * Constructor of HomeCommand2.
     * 
     * @param label Command label
     */
    public HomeCommand2(String label) {
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
        if (!player.hasPermission("trashcan.general.home")) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (!TrashCan.getConfigHandler().hasHomes(player)) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You don't have a home! Set one with /sethome!");
            return true;
        }
        Location home = TrashCan.getConfigHandler().getSingleHome(player);
        player.teleport(home);
        MessageUtil.sendMessage(player, ChatColor.GOLD + "You've been teleported to your home!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /home");
    }
}
