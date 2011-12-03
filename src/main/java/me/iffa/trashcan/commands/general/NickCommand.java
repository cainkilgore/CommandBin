// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /nick.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class NickCommand extends TrashCommand {
    /**
     * Constructor of NickCommand.
     * 
     * @param label Command label
     */
    public NickCommand(String label) {
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
                if (!player.hasPermission("trashcan.genral.nick")) {
                    MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
                    return true;
                }
                TrashCan.getConfigHandler().setNick(player, args[0]);
                player.setDisplayName(args[0]);
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Your nickname is now '" + args[0] + "'!");
                return true;
            } else {
                if (!cs.hasPermission("trashcan.general.nick.others")) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
                    return true;
                }
                TrashCan.getConfigHandler().setNick(target, args[1]);
                target.setDisplayName(args[0]);
                MessageUtil.sendMessage(target, ChatColor.GOLD + "Your nickname is now '" + args[0] + "'!");
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "'" + target.getName() + "'s nickname is now '" + args[0] + "'!");
                return true;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /nick <nick> OR <player> <nick>");
    }
    
}
