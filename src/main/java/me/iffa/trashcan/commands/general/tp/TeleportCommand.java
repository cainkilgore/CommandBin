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
 * Represents /tp.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class TeleportCommand extends TrashCommand {
    /**
     * Constructor of TeleportCommand.
     * 
     * @param label Command label
     */
    public TeleportCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length < 1) {
            return false;
        } else {
            if (args.length == 1) {
                if (!(cs instanceof Player)) {
                    MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
                    return true;
                }
                Player player = (Player) cs;
                if (!player.hasPermission("trashcan.general.tp")) {
                    MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    MessageUtil.sendMessage(player, ChatColor.RED + "The player was not found!");
                    return true;
                }
                player.teleport(target);
                MessageUtil.sendMessage(player, ChatColor.GOLD + "Teleported!");
                return true;
            } else {
                if (!cs.hasPermission("trashcan.general.tp")) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                    return true;
                }
                Player from = Bukkit.getPlayer(args[0]);
                Player target = Bukkit.getPlayer(args[1]);
                if (from == null) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
                    return true;
                }
                if (target == null) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "The destination player was not found!");
                    return true;
                }
                from.teleport(target);
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Teleported '" + from.getName() + "' to '" + target.getName() + "'!");
                return true;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "USage: /tp <player> / <player> <targetplayer>");
    }
    
}
