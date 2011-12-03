// Package Declaration
package me.iffa.trashcan.utils;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Utility class to make sending messages to players and other "things" as easy
 * as possible.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class MessageUtil {
    /**
     * Sends a message to any CommandSender. If the target is not a player, colors
     * will be stripped to keep the result clean. (since only players can see colors)
     * 
     * @param cs Command sender
     * @param message Message to send
     */
    public static void sendMessage(CommandSender cs, String message) {
        if (cs instanceof Player) {
            cs.sendMessage(message);
            return;
        }
        cs.sendMessage(TrashCan.getPrefix() + " " + ChatColor.stripColor(message));
    }
    
    /**
     * Turns all &-color codes into ChatColor, that Bukkit understands.
     * 
     * @param message Message
     * 
     * @return Message with Bukkit-readable colors
     */
    public static String parseColors(String message) {
        return message.replaceAll("&([0-9a-f])", "\u00A7$1");
    }

    /**
     * Constructor of MessageUtil.
     */
    private MessageUtil() {
    }
}
