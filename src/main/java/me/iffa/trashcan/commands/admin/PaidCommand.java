// Package Declaration
package me.iffa.trashcan.commands.admin;

// Java Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /paid.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class PaidCommand extends TrashCommand {

    /**
     * Constructor of PaidCommand.
     * 
     * @param label Command label
     */
    public PaidCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!cs.hasPermission("trashcan.admin.paid")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
            return true;
        }

        if (args.length < 1) {
            return false;
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "The player '" + args[0] + "' was not found!");
            return true;
        }
        
        Player target = Bukkit.getPlayer(args[0]);
        if (hasPaid(cs, target)) {
            MessageUtil.sendMessage(cs, ChatColor.GOLD + target.getName() + " has paid for Minecraft.");
        } else {
            MessageUtil.sendMessage(cs, ChatColor.RED + target.getName() + " has not paid for Minecraft.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /paid <player>");
    }
    
    /**
     * Checks if a player has paid for Minecraft. (ie is a premium user)
     * 
     * @param cs Command sender
     * @param player Player
     * 
     * @return True if the player has paid, false if not (or if an error occured)
     */
    private boolean hasPaid(CommandSender cs, Player player) {
        try {
            URL url = new URL("http://minecraft.net/haspaid.jsp?user=" + player.getName());
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            boolean paid = true;
            while ((str = in.readLine()) != null) {
                paid = Boolean.parseBoolean(str);
            }
            if (in != null) {
                in.close();
            }
            return paid;
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
