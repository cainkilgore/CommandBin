// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Represents /armor.
 * 
 * @author iffamies
 */
public class ArmorCommand extends TrashCommand {
    /**
     * Constructor of ArmorCommand.
     * 
     * @param label Command label
     */
    public ArmorCommand(String label) {
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
        if (!cs.hasPermission("trashcan.general.armor")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        if (!giveArmor(args[0], (Player)cs)) {
            return true;
        }
        MessageUtil.sendMessage(cs, ChatColor.GOLD + "You received armor of type '" + args[0] + "'!");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /armor <type>");
    }
    
    /**
     * Gives armor to a player of the given type.
     * 
     * @param type Armor type
     * @param player Player
     * 
     * @return True if successful
     */
    private boolean giveArmor(String type, Player player) {
        Material chest = Material.getMaterial(type.toUpperCase() + "_CHESTPLATE");
        Material head = Material.getMaterial(type.toUpperCase() + "_HELMET");
        Material pants = Material.getMaterial(type.toUpperCase() + "_LEGGINGS");
        Material boots = Material.getMaterial(type.toUpperCase() + "_BOOTS");
        if (chest == null || head == null || pants == null || boots == null) {
            MessageUtil.sendMessage(player, ChatColor.RED + "Invalid armortype '" + type + "'!");
            return false;
        }
        player.getInventory().setHelmet(new ItemStack(head));
        player.getInventory().setChestplate(new ItemStack(chest));
        player.getInventory().setLeggings(new ItemStack(pants));
        player.getInventory().setBoots(new ItemStack(boots));
        return true;
    }
    
}
