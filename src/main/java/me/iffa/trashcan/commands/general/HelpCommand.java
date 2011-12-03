// Package Declaration
package me.iffa.trashcan.commands.general;

// Java Imports
import java.util.List;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.HelpPage;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents /help.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class HelpCommand extends TrashCommand {

    /**
     * Constructor of HelpCommand.
     * 
     * @param label 
     */
    public HelpCommand(String label) {
        super(label);
    }
    /**
     * Sends and handles help pages.
     */
    private static final int PER_PAGE = 5;

    @SuppressWarnings("unchecked")
    public void sendHelpPage(int page, String[] args, CommandSender cs) {
        // TODO: Set up help pages here
        HelpPage send = new HelpPage(page);
        List<String> commands = TrashCommand.getCommandsList();
        int maxPages = commands.size() / PER_PAGE;
        if (page < 0 || page > maxPages) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "The page you were looking for was not found!");
            return;
        }
        for (int i = PER_PAGE * page; i < PER_PAGE * page + PER_PAGE && i < commands.size(); i++) {
            String description = "No description";
            try {
                description = Bukkit.getPluginCommand(commands.get(i)).getDescription();
            } catch (NullPointerException ex) {
            }
            send.getCommands().put(commands.get(i), description);
        }
        send.sendHelpPage(cs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
//        if (true) {
//            MessageUtil.sendMessage(cs, ChatColor.GRAY + "Sorry, this command is temporarily disabled.");
//            return true;
//        }
        if (args.length < 1) {
            sendHelpPage(0, args, cs);
        } else {
            int page = 0;
            try {
                page = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                return false;
            }
            sendHelpPage(page, args, cs);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /help [page (0-" + TrashCommand.getCommands().size() / PER_PAGE + ")]");
    }
}
