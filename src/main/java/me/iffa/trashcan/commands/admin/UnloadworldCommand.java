// Package Declaration
package me.iffa.trashcan.commands.admin;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

/**
 * Represents /unloadworld.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class UnloadworldCommand extends TrashCommand {
    /**
     * Constructor of UnloadworldCommand.
     * 
     * @param label Command label
     */
    public UnloadworldCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.admin.unloadworld")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        } else {
            World world = Bukkit.getWorld(args[0]);
            if (world == null) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "World '" + args[0] + "' does not exist!");
                return true;
            }
            Bukkit.unloadWorld(world, true);
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "Unloaded world '" + world.getName() + "'.");
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /unloadworld <name>");
    }
}
