// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /unstuck.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class UnstuckCommand extends TrashCommand {
    public UnstuckCommand(String label) {
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
        if (!cs.hasPermission("trashcan.general.unstuck")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        Player player = (Player) cs;
        if ((player.getLocation().getBlock().isEmpty() || player.getLocation().getBlock().isLiquid()) || (player.getLocation().getBlock().getRelative(BlockFace.UP).isEmpty() || player.getLocation().getBlock().getRelative(BlockFace.UP).isLiquid())) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You're not stuck!");
            return true;
        }
        player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getRelative(BlockFace.UP).getLocation());
        MessageUtil.sendMessage(player, ChatColor.GOLD + "Teleported!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
    }
    
}
