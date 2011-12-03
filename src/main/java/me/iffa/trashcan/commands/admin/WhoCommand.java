// Package Declaration
package me.iffa.trashcan.commands.admin;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /who.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class WhoCommand extends TrashCommand {
    /**
     * Constructor of WhoCommand.
     * 
     * @param label Command label
     */
    public WhoCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length < 1) {
            if (!(cs instanceof Player)) {
                MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
                return true;
            }
            if (!cs.hasPermission("trashcan.admin.who")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player player = (Player) cs;
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Information about you:");
            sendInfo(player, player);
            return true;
        } else {
            if (!cs.hasPermission("trashcan.admin.who.others")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
                return true;
            }
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "Information about '" + target.getName() + "':");
            sendInfo(cs, target);
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /who [player]");
    }
    
    /**
     * Sends /who-information of a player to a command sender.
     * 
     * @param cs Command sender
     * @param player Target
     */
    private void sendInfo(CommandSender cs, Player target) {
        Location loc = target.getLocation();
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "IP address: " + target.getAddress().getAddress().getHostAddress());
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Current world: " + target.getWorld().getName());
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Display name: " + target.getDisplayName());
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Location: " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ());
    }
    
}
