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
 * Represents /home.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class HomeCommand extends TrashCommand {
    /**
     * Constructor of HomeCommand.
     * 
     * @param label Command label
     */
    public HomeCommand(String label) {
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
        if (args.length < 1) {
            return false;
        } else {
            Player player = (Player) cs;
            if (!player.hasPermission("trashcan.general.home")) {
                MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
                return true;
            }
            if (!TrashCan.getConfigHandler().hasHomes(player)) {
                MessageUtil.sendMessage(player, ChatColor.RED + "You don't have any homes! Set one with /sethome <home>!");
                return true;
            }
            Location home = TrashCan.getConfigHandler().getHome(args[0], player);
            if (home == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "Home '" + args[0] + "' does not exist!");
                return true;
            }
            player.teleport(home);
            MessageUtil.sendMessage(player, ChatColor.GOLD + "You've been teleported to home '" + args[0] + "'!");
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /home <name>");
    }
}
