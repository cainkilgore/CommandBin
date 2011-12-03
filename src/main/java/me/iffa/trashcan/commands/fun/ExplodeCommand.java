// Pacakage Declaration
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
 * Represents /explode.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class ExplodeCommand extends TrashCommand {
    /**
     * Constructor of ExplodeCommand.
     * 
     * @param label Command label
     */
    public ExplodeCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.fun.explode")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        float radius = 4F;
        if (target == null) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "The player was not found!");
            return true;
        }
        try {
            radius = Float.parseFloat(args[1]);
        } catch (Exception ex) {
            if (ex instanceof NumberFormatException) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "Invalid radius '" + args[1] + "'!");
                return true;
            }
        }
        target.getWorld().createExplosion(target.getLocation(), radius);
        MessageUtil.sendMessage(cs, ChatColor.GOLD + "'" + target.getName() + "' was blown to pieces!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /explode <player> [power]");
    }
}
