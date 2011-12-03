// Package Declaration
package me.iffa.trashcan.commands.fun;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /light.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class LightCommand extends TrashCommand {
    /**
     * Constructor of LightCommand.
     * 
     * @param label Command label
     */
    public LightCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.fun.light")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        int time = 4;
        if (target == null) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
            return true;
        }
        try {
            time = Integer.parseInt(args[1]);
        } catch (Exception ex) {
            if (ex instanceof NumberFormatException) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "Invalid time '" + args[1] + "'!");
                return true;
            }
        }
        
        // CommandBin multiplies time by 10, so each "second" is actually half
        // a second. 1 second is 20 ticks.
        target.setFireTicks(time * 20);
        MessageUtil.sendMessage(cs, ChatColor.GOLD + "'" + target.getName() + "' was lit up!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /light <player> [length(sec)]");
    }
}
