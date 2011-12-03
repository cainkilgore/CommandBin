// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /spawn.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SpawnCommand extends TrashCommand {
    /**
     * Constructor of SpawnCommand.
     * 
     * @param label Command label
     */
    public SpawnCommand(String label) {
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
        if (!cs.hasPermission("trashcan.general.spawn")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        Player player = (Player) cs;
        if (args.length < 1) {
            player.teleport(player.getWorld().getSpawnLocation());
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Teleported!");
        } else {
            World world = Bukkit.getWorld(args[0]);
            if (world == null) {
                MessageUtil.sendMessage(player, ChatColor.RED + "The world was not found!");
                return true;
            }
            player.teleport(world.getSpawnLocation());
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Teleported!");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /spawn [world]");
    }
    
}
