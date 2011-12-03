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
 * Represents /survival.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SurvivalCommand extends TrashCommand {
    /**
     * Constructor of SurvivalCommand.
     * 
     * @param label Command label
     */
    public SurvivalCommand(String label) {
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
            if (!cs.hasPermission("trashcan.general.survival")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player player = (Player) cs;
            if (player.getGameMode() == GameMode.SURVIVAL) {
                MessageUtil.sendMessage(player, ChatColor.RED + "You're already playing survival!");
                return true;
            }
            player.setGameMode(GameMode.SURVIVAL);
            MessageUtil.sendMessage(player, ChatColor.GOLD + "You're now playing survival!");
        } else {
            if (!cs.hasPermission("trashcan.general.survival.others")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
                return true;
            }
            if (target.getGameMode() == GameMode.SURVIVAL) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "The player '" + target.getName() + "' is already playing survival!");
                return true;
            }
            target.setGameMode(GameMode.SURVIVAL);
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "'" + target.getName() + "' is now playing survival!");
            MessageUtil.sendMessage(target, ChatColor.GOLD + "You're now playing survival!");
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
