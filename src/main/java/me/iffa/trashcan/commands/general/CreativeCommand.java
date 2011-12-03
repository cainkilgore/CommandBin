// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /creative.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class CreativeCommand extends TrashCommand {
    /**
     * Constructor of CreativeCommand.
     * 
     * @param label Command label
     */
    public CreativeCommand(String label) {
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
            if (!cs.hasPermission("trashcan.general.creative")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player player = (Player) cs;
            if (player.getGameMode() == GameMode.CREATIVE) {
                MessageUtil.sendMessage(player, ChatColor.RED + "You're already playing creative!");
                return true;
            }
            player.setGameMode(GameMode.CREATIVE);
            MessageUtil.sendMessage(player, ChatColor.GOLD + "You're now playing creative!");
        } else {
            if (!cs.hasPermission("trashcan.general.creative.others")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
                return true;
            }
            if (target.getGameMode() == GameMode.CREATIVE) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player '" + target.getName() + "' is already playing creative!");
                return true;
            }
            target.setGameMode(GameMode.CREATIVE);
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "'" + target.getName() + "' is now playing creative!");
            MessageUtil.sendMessage(target, ChatColor.GOLD + "You're now playing creative!");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /creative [player]");
    }
    
}
