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
 * Represents /item.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class ItemCommand extends TrashCommand {

    /**
     * Constructor of ItemCommand.
     * 
     * @param label Command label
     */
    public ItemCommand(String label) {
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
        Player player = (Player) cs;
        if (!player.hasPermission("trashcan.general.item")) {
            MessageUtil.sendMessage(player, ChatColor.RED + "You don't have permission!");
            return true;
        }
        if (args.length < 1) {
            return false;
        } else {
            String[] itemAndData = args[0].split(":");
            int item = 0;
            try {
                item = Integer.parseInt(itemAndData[0]);
            } catch (NumberFormatException ex) {
                // Item was not an int, do nothing
            }
            Material itemMat = item == 0 ? Material.matchMaterial(itemAndData[0]) : Material.getMaterial(item);
            int damage = 0;
            if (itemMat == null) {
                MessageUtil.sendMessage(player, ChatColor.RED + "Invalid item '" + itemAndData[0] + "'!");
                return true;
            }
            if (itemAndData.length == 2) {
                try {
                    damage = Integer.parseInt(itemAndData[1]);
                } catch (NumberFormatException ex) {
                    // Data was not an int, complain here
                    MessageUtil.sendMessage(player, ChatColor.RED + "Data must be a valid number!");
                    return true;
                }
            }
            ItemStack itemStack = new ItemStack(itemMat, 1, (short) damage);
            if (args.length == 1) {
                player.getInventory().addItem(itemStack);
            } else {
                int amount = 1;
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException ex) {
                    MessageUtil.sendMessage(player, ChatColor.RED + "Invalid item amount!");
                    return true;
                }
                itemStack.setAmount(amount);
                player.getInventory().addItem(itemStack);
            }
            MessageUtil.sendMessage(player, ChatColor.GOLD + "You've been given " + itemStack.getAmount() + " of " + itemMat.toString() + " (" + itemMat.getId() + ")!");
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /item <item[:damage]> [amount]");
    }
}
