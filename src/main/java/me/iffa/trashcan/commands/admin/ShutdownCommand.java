// Package Declaration
package me.iffa.trashcan.commands.admin;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /shutdown.
 * NOTE: Since Cain was too lazy to implement this, I'm implementing it myself.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class ShutdownCommand extends TrashCommand {
    /**
     * Constructor of ShutdownCommand.
     * 
     * @param label Command label
     */
    public ShutdownCommand(String label) {
        super(label);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.admin.shutdown")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        Bukkit.broadcastMessage(TrashCan.getConfigHandler().getShutdownMessage());
        Bukkit.savePlayers();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(ChatColor.stripColor(TrashCan.getConfigHandler().getShutdownMessage()));
        }
        for (World world : Bukkit.getWorlds()) {
            world.save();
        }
        Bukkit.shutdown();
        return true;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
    }
    
}
