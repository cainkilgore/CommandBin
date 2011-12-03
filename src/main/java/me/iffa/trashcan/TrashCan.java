// Package Declaration
package me.iffa.trashcan;

// Java Imports
import java.util.logging.Level;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.listeners.TrashBlockListener;
import me.iffa.trashcan.listeners.TrashEntityListener;
import me.iffa.trashcan.listeners.TrashPlayerListener;
import me.iffa.trashcan.utils.ConfigHandler;
import me.iffa.trashcan.utils.LoggerUtil;

// Bukkit Imports
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * NOTE TO CAIN: I am not breaking your license. Your license is invalid.
 * "[07:54:00] <CainFoool> Not under my license."
 * "
 * 
 * - If this is to be your meaning of "distribution", 
 * - you shall know that as this work is not within strict copyright law, 
 * - iffa owns his modified work.
 * 
 * - If you were to read the license standards more indepth, you would know that 
 * - in order to consider it "his work", it must be documented on EVERY file. 
 * - CainFoool only licensed one of his pieces of work which iffa does not use.
 * 
 * - If CainFoool were to have rights in order to take down Iffa's piece of work 
 * - due to "non-distributed modification", it would go against the licenses 
 * - fair dealing rights as you are restricting his freedom.
 * 
 * - The fact that CainFoool has removed his work states that his license is invalid,
 * - or such thing as "CommandBin license" does not exist.
 * - http://creativecommons.org/licenses/by-sa/1.0/legalcode
 * - http://creativecommons.org/licenses/by-sa/1.0/
 * 
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * Main class of TrashCan, a (Craft)Bukkit plugin originally made by "CainFool", 
 * who does not know any proper Java and will never learn that either. Consider 
 * this plugin a Craftbukkit for CommandBin. Every time CommandBin receives a
 * useful update, TrashCan will update ASAP to include the new features, and 
 * probably some other fixes.
 * 
 * @author iffamies
 * @author Cain (because I feel sorry for you)
 */
public class TrashCan extends JavaPlugin {
    // Variables
    private static ConfigHandler configHandler;
    private static PluginDescriptionFile description;
    private static String prefix = "[TrashCan]";
    private final TrashBlockListener blockListener = new TrashBlockListener(this);
    private final TrashEntityListener entityListener = new TrashEntityListener(this);
    private final TrashPlayerListener playerListener = new TrashPlayerListener();

    /**
     * @see org.bukkit.plugin.Plugin
     */
    @Override
    public void onEnable() {
        // Checking for CommandBin on the server and panic disabling if it's found.
        if (getServer().getPluginManager().getPlugin("CommandBin") != null) {
            LoggerUtil.log(Level.WARNING, "TrashCan does not want CommandBin running on the server.");
        }
        // Continuing normal enabling process.
        initializeVariables();
        // Loading the configuration file(s).
        configHandler.load();
        // Registering events & commands (TrashCommand).
        registerEvents();
        TrashCommand.initializeCommands();
        // We're done here! Wohoo!
        LoggerUtil.log(Level.INFO, "Enabled version " + description.getVersion() + ".");
    }
    
    /**
     * @see org.bukkit.plugin.Plugin
     */
    @Override
    public void onDisable() {
        // Continuing normal disabling process.
        LoggerUtil.log(Level.INFO, "Disabled version " + description.getVersion() + ".");
    }
    
    /**
     * Initializes variables for the plugin.
     */
    private void initializeVariables() {
        description = getDescription();
        configHandler = new ConfigHandler(this);
    }
    
    /**
     * Registers events for the plugin.
     */
    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        
        // Block listener
        pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
        
        // Entity listener
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_EXPLODE, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PROJECTILE_HIT, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENDERMAN_PICKUP, entityListener, Event.Priority.Normal, this);
        
        // Player listener
        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_LOGIN, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_EGG_THROW, playerListener, Event.Priority.Normal, this);
    }
    
    // NOTE: I have agreed to remove registerRecipes() as seems to be too similar
    // to Cain's original implementation.
    
    /* Efficiency methods */
    
    /**
     * Gets a command of TrashCan by looking for it in the Map containing all
     * commands.
     * 
     * @param command Command to look for
     * 
     * @return TrashCommand object or null if the command was not found
     */
    public static TrashCommand matchCommand(String command) {
        if (TrashCommand.getCommands().containsKey(command)) {
            return TrashCommand.getCommands().get(command);
        }
        return null;
    }
    
    /**
     * Gets the plugin's prefix to be used in log messages etc.
     * 
     * @return Plugin's prefix
     */
    public static String getPrefix() {
        return prefix;
    }
    
    /**
     * Gets the ConfigHandler object.
     * 
     * @return Config handler
     */
    public static ConfigHandler getConfigHandler() {
        return configHandler;
    }
    
    /**
     * Gets the description file of TrashCan. Can be used to get things such as
     * version and authors.
     * 
     * @return Plugin description file
     */
    public static PluginDescriptionFile getDescriptionFile() {
        return description;
    }
}