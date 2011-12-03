// Package Declaration
package me.iffa.trashcan.commands.admin;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

/**
 * Represents /createworld. 
 * NOTE: because Cain is lazy, I added environment as an argument too. It's 
 * optional though.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class CreateworldCommand extends TrashCommand {
    /**
     * Constructor of CreateworldCommand.
     * 
     * @param label Command label
     */
    public CreateworldCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.admin.createworld")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        } else {
            if (args.length == 1) {
                if (Bukkit.getWorld(args[0]) != null) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "World '" + args[0] + "' already exists!");
                    return true;
                }
                Bukkit.createWorld(new WorldCreator(args[0]).environment(World.Environment.NORMAL));
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Created world '" + args[0] + "'.");
                return true;
            } else {
                if (Bukkit.getWorld(args[0]) != null) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "World '" + args[0] + "' already exists!");
                    return true;
                }
                World.Environment environment = World.Environment.valueOf(args[1].toUpperCase());
                if (environment == null) {
                    MessageUtil.sendMessage(cs, ChatColor.RED + "Invalid world environment '" + args[1] + "'!");
                    return true;
                }
                Bukkit.createWorld(new WorldCreator(args[0]).environment(environment));
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Created world '" + args[0] + "' with environment '" + args[1] + "'.");
                return true;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /createworld <name> [environment]");
    }
    
}
