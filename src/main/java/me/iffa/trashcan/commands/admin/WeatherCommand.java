// Package Declaration
package me.iffa.trashcan.commands.admin;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /weather.
 * NOTE: /sun and /rain are now this command. Cain is obviously too lazy to
 * make one command for weather. Using this command without arguments tells
 * current weather. That requires no permissions.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class WeatherCommand extends TrashCommand {
    /**
     * Constructor of WeatherCommand.
     * 
     * @param label Command label
     */
    public WeatherCommand(String label) {
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
            Player player = (Player) cs;
            World world = player.getWorld();
            if (world.isThundering() || world.hasStorm()) {
                MessageUtil.sendMessage(player, ChatColor.GRAY + "Current weather is: " + ChatColor.GOLD + "rainy");
            } else {
                MessageUtil.sendMessage(player, ChatColor.GRAY + "Current weather is: " + ChatColor.GOLD + "sunny");
            }
            return true;
        } else {
            if (!(cs instanceof Player)) {
                MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
                return true;
            }
            if (!cs.hasPermission("trashcan.admin.weather")) {
                MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
                return true;
            }
            Player player = (Player) cs;
            World world = player.getWorld();
            if (args[0].equalsIgnoreCase("sun")) {
                world.setThundering(false);
                world.setStorm(false);
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "It is now sunny in '" + world.getName() + "'!");
            } else if (args[0].equalsIgnoreCase("rain")) {
                world.setStorm(true);
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "It is now rainy in '" + world.getName() + "'!");
            } else {
                return false;
            }
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /weather [rain/sun]");
    }
    
}
