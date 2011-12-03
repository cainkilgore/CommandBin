// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /mytime.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class MytimeCommand extends TrashCommand {
    /**
     * Constructor of MytimeCommand.
     * 
     * @param label Command label
     */
    public MytimeCommand(String label) {
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
        }
        Player player = (Player) cs;
        if (!player.hasPermission("trashcan.general.mytime")) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args[0].equalsIgnoreCase("day")) {
            player.setPlayerTime(0, false);
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Changed your time to day!");
        } else if (args[0].equalsIgnoreCase("night")) {
            player.setPlayerTime(13801, false);
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Changed your time to night!");
        } else if (args[0].equalsIgnoreCase("reset")) {
            player.resetPlayerTime();
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Your time has been reset!");
        } else {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /mytime <day/night/reset>");
    }
    
}
