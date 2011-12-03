// Package Declaration
package me.iffa.trashcan.utils;

// Java Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * Utility class to handle loading the configuration file(s) and getting/setting
 * values.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class ConfigHandler {
    // Variables
    private TrashCan plugin;
    private Map<ConfigFile, YamlConfiguration> config = new EnumMap<ConfigFile, YamlConfiguration>(ConfigFile.class);
    private Map<ConfigFile, File> file = new EnumMap<ConfigFile, File>(ConfigFile.class);
    private Map<ConfigFile, Boolean> loaded = new EnumMap<ConfigFile, Boolean>(ConfigFile.class);

    /**
     * Constructor of ConfigHandler.
     * 
     * @param plugin TrashCan instance
     */
    public ConfigHandler(TrashCan plugin) {
        this.plugin = plugin;
    }

    /**
     * Gets a configuration file.
     * 
     * @param configFile Configuration file to get
     * 
     * @return YamlConfiguration object of the config file, or null if the file
     * was not loaded properly. (ie not in Map containing YamlConfigurations)
     */
    public YamlConfiguration getConfig(ConfigFile configFile) {
        if (config.containsKey(configFile)) {
            return config.get(configFile);
        }
        return null;
    }

    /**
     * Loads or creates the configuration file(s). If the process was successful,
     * the configuration file is added to the Map containing all (loaded) config
     * files.
     */
    public void load() {
        for (ConfigFile configfile : ConfigFile.values()) {
            file.put(configfile, new File(plugin.getDataFolder(), configfile.getFileName()));
            if (file.get(configfile).exists()) {
                config.put(configfile, new YamlConfiguration());
                try {
                    config.get(configfile).load(file.get(configfile));
                } catch (FileNotFoundException ex) {
                    LoggerUtil.log(Level.WARNING, ex.toString());
                    loaded.put(configfile, false);
                    return;
                } catch (IOException ex) {
                    LoggerUtil.log(Level.WARNING, ex.toString());
                    loaded.put(configfile, false);
                    return;
                } catch (InvalidConfigurationException ex) {
                    LoggerUtil.log(Level.WARNING, ex.toString());
                    loaded.put(configfile, false);
                    return;
                }
                loaded.put(configfile, true);
            } else {
                try {
                    plugin.getDataFolder().mkdir();
                    InputStream jarURL = ConfigHandler.class.getResourceAsStream("/" + configfile.getFileName());
                    copyFile(jarURL, file.get(configfile));
                    config.put(configfile, new YamlConfiguration());
                    config.get(configfile).load(file.get(configfile));
                    loaded.put(configfile, true);
                    LoggerUtil.log(Level.INFO, "Created default configuration file '" + configfile.name() + "'.");
                } catch (Exception e) {
                    LoggerUtil.log(Level.SEVERE, "Problem while creating default configuration file: " + e.toString());
                }
            }
        }
    }

    /**
     * Reloads a configuration file. NOTE: This is the most hacky solution ever,
     * don't expect this to work 10 times out of 10!
     * 
     * @param configFile Configuration file to reload
     * 
     * @return True if reload was successful, false if something went wrong
     */
    public boolean reload(ConfigFile configFile) {
        InputStream defConfigStream = plugin.getResource(configFile.getFileName());
        if (defConfigStream != null) {
            try {
                config.get(configFile).load(defConfigStream);
                return true;
            } catch (IOException ex) {
                LoggerUtil.log(Level.WARNING, "Problem reloading configuration file: '" + ex.toString());
            } catch (InvalidConfigurationException ex) {
                LoggerUtil.log(Level.WARNING, "Problem reloading configuration file: '" + ex.toString());
            }
        }
        return false;
    }

    /**
     * Copies a file to a new location.
     * 
     * @param in InputStream (resource to copy)
     * @param out File to save as
     * 
     * @throws Exception
     */
    private void copyFile(InputStream in, File out) throws Exception {
        InputStream fis = in;
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * Enum containing all configuration files and their file names. This is used
     * for easy loading of configuration files. All you have to do is add the
     * file to this enum, and add the file itself to the .jar. load() will handle
     * the rest.
     */
    public enum ConfigFile {
        // Enums

        CONFIG("config.yml");
        // Variables
        private String file;

        /**
         * Constructor of ConfigFile.
         * 
         * @param file File name
         */
        ConfigFile(String file) {
            this.file = file;
        }

        /**
         * Gets the file associated with the enum.
         * 
         * @return File associated wiht the enum
         */
        public String getFileName() {
            return this.file;
        }
    }

    /* Getter methods */ // TODO: JavaDoc all of this down below
    public String getShutdownMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.shutdownmessage", "&cThe server is going down for shutdown!"));
    }
    
    public String getFormattedBroadcast(String message) {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.broadcastformat", "&6[Broadcast]&7{message}").replace("{message}", message));
    }

    public boolean getBroadcastKick() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.broadcastkick", true);
    }

    public boolean getBroadcastBan() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.broadcastban", true);
    }

    public String getMutedMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.playerismuted", "&cYou are muted, shh!"));
    }

    public String getNoCommandsMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.playercannotusecommands", "&cYou cannot use commands."));
    }

    public String getJoinMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.joinmessage", "&ehas joined the game"));
    }

    public String getLeaveMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.leavemessage", "&ehas left the game"));
    }

    public boolean getDropXPDeath() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.dropxpondeath", false);
    }

    public int getMaxMobAmount() {
        return config.get(ConfigFile.CONFIG).getInt("settings.spawn-mob-max-amount", 10);
    }

    public boolean getEnderGrief() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.endermangriefing", false);
    }

    public boolean getBlockTNT() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.protection.block-placing-tnt", true);
    }

    public boolean getBlockLava() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.protection.block-placing-lava", true);
    }

    public boolean getBlockCreeper() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.protection.block-creeper-explosions", true);
    }

    public boolean getCoal() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.coalore", false);
    }

    public boolean getIron() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.ironore", false);
    }

    public boolean getGold() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.goldore", false);
    }

    public boolean getDiamond() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.diamondore", false);
    }

    public boolean getRedstone() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.redstoneore", false);
    }

    public boolean getLapis() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.lapislazuliore", false);
    }

    public boolean getMineSpawners() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.mineablemobspawners", true);
    }

    public boolean getAdminPM() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.opscanseepms", true);
    }

    public String getMOTD() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.message-of-the-day", "&7This is the default &6TrashCan&7 MOTD!"));
    }

    public boolean getCustomChat() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.customchat", true);
    }

    public int getBowExplosionRadius() {
        return config.get(ConfigFile.CONFIG).getInt("settings.bowexplosionradius", 3);
    }

    public boolean getEnderSpawnOnEgg() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.spawn-enderdragon-on-hitting-enderegg", true);
    }

    public boolean getTeleportOnThrow() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.teleport-on-egg-throw", true);
    }

    public boolean getExplosionBow(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".explosionbow", false);
    }

    public boolean getCrossbow(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".crossbow", false);
    }

    public boolean getUnlimited(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".unlimited", false);
    }

    public boolean getGodmode(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".godmode", false);
    }

    public boolean getTorchbow(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".torchbow", false);
    }

    public boolean getHandicapped(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".handicapped", false);
    }

    public boolean getMuted(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".muted", false);
    }

    public boolean getBanned(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".banned", false);
    }

    public boolean getBanned(String player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player + ".banned", false);
    }

    public boolean getSnowman(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".snowman", false);
    }

    public boolean getSmoke(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".smoke", false);
    }

    public boolean getFrozen(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".frozen", false);
    }

    public String getBanReason(Player player) {
        return config.get(ConfigFile.CONFIG).getString(player.getName() + ".banreason", "Unspecified");
    }

    public boolean getExplosionStick(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".explosionstick", false);
    }

    public boolean getLightningStick(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".lightningstick", false);
    }

    public String getNick(Player player) {
        return config.get(ConfigFile.CONFIG).getString(player.getName() + ".nickname", null);
    }

    public boolean getIPBanned(Player player) {
        String ipStrip = player.getAddress().getAddress().getHostAddress().replace(".", "");
        return config.get(ConfigFile.CONFIG).getBoolean("bannedips." + ipStrip, false);
    }
    
    public boolean getLogCommands() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.log-commands", true);
    }
    
    public boolean getIPBanned(String address) {
        String ipStrip = address.replace(".", "");
        return config.get(ConfigFile.CONFIG).getBoolean("bannedips." + ipStrip, false);
    }
    
    public boolean getTameSpawnedWolves() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.tame-spawned-wolves", true);
    }
    
    public Location getHome(String home, Player player) {
        if (!config.get(ConfigFile.CONFIG).contains(player.getName() + ".home." + home)) {
            return null;
        }
        double x = config.get(ConfigFile.CONFIG).getDouble(player.getName() + ".home." + home + ".x");
        double y = config.get(ConfigFile.CONFIG).getDouble(player.getName() + ".home." + home + ".y");
        double z = config.get(ConfigFile.CONFIG).getDouble(player.getName() + ".home." + home + ".z");
        String w = config.get(ConfigFile.CONFIG).getString(player.getName() + ".home." + home + ".world");
        World world = Bukkit.getWorld(w);
        return new Location(world, x, y, z);
    }
    
    public Location getSingleHome(Player player) {
        if (!config.get(ConfigFile.CONFIG).contains(player.getName() + ".home")) {
            return null;
        }
        double x = config.get(ConfigFile.CONFIG).getDouble(player.getName() + ".home.x");
        double y = config.get(ConfigFile.CONFIG).getDouble(player.getName() + ".home.y");
        double z = config.get(ConfigFile.CONFIG).getDouble(player.getName() + ".home.z");
        String w = config.get(ConfigFile.CONFIG).getString(player.getName() + ".home.world");
        World world = Bukkit.getWorld(w);
        return new Location(world, x, y, z);
    }
    
    public boolean hasHomes(Player player) {
        return config.get(ConfigFile.CONFIG).getConfigurationSection(player.getName() + ".home") == null ? false : true;
    }
    
    public boolean hasWarps() {
        return config.get(ConfigFile.CONFIG).getConfigurationSection("settings.warps.") == null ? false : true;
    }
    
    public Location getWarp(String warp) {
        if (!config.get(ConfigFile.CONFIG).contains("settings.warps." + warp)) {
            return null;
        }
        double x = config.get(ConfigFile.CONFIG).getDouble("settings.warps." + warp + ".x");
        double y = config.get(ConfigFile.CONFIG).getDouble("settings.warps." + warp + ".y");
        double z = config.get(ConfigFile.CONFIG).getDouble("settings.warps." + warp + ".z");
        String w = config.get(ConfigFile.CONFIG).getString("settings.warps." + warp + ".world");
        World world = Bukkit.getWorld(w);
        return new Location(world, x, y, z);
    }
    
    public boolean getMultiHomes() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.multihomes", true);
    }

    /* Setter methods */
    /**
     * Sets the explosionbow enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setExplosionBow(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".explosionbow", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the banned-state for a player.
     * 
     * @param banned Enabled true/false
     * @param player Player
     * @param reason Reason
     */
    public void setBanned(boolean banned, Player player, String reason) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".banned", banned);
        config.get(ConfigFile.CONFIG).set(player.getName() + ".banreason", reason);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets the banned ip-state for a player.
     * 
     * @param banned Enabled true/false
     * @param player Player
     * @param reason Reason
     */
    public void setIPBanned(boolean banned, Player player, String reason) {
        config.get(ConfigFile.CONFIG).set("bannedips." + player.getAddress().getAddress().getHostAddress().replace(".", ""), banned);
        config.get(ConfigFile.CONFIG).set("bannedips." + player.getAddress().getAddress().getHostAddress().replace(".", "") + ".banreason", reason);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the banned-state for a player.
     * 
     * @param banned Enabled true/false
     * @param player Player
     * @param reason Reason
     */
    public void setBanned(boolean banned, String player, String reason) {
        config.get(ConfigFile.CONFIG).set(player + ".banned", banned);
        config.get(ConfigFile.CONFIG).set(player + ".banreason", reason);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets the banned ip-state for a player.
     * 
     * @param banned Enabled true/false
     * @param address Player IP
     * @param reason Reason
     */
    public void setIPBanned(boolean banned, String address, String reason) {
        config.get(ConfigFile.CONFIG).set("bannedips." + address.replace(".", ""), banned);
        config.get(ConfigFile.CONFIG).set("bannedips." + address.replace(".", "") + ".banreason", reason);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the crossbow enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setCrossbow(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".crossbow", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets the smoke enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setSmoke(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".smoke", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the torchbow enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setTorchbow(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".torchbow", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets the lightningstick enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setLightningstick(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".lightningstick", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets the explosionstick enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setExplosionstick(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".explosionstick", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets the snowman enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setSnowman(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".snowman", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the frozen-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setFrozen(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".frozen", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets the handicapped-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setHandicapped(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".handicapped", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the unlimited drops enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setUnlimited(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".unlimited", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the muted-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setMuted(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".muted", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets a new home for a player.
     * 
     * @param player Player
     * @param location Home location
     * @param home Home name
     */
    public void setHome(Player player, Location location, String home) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".home." + home + ".x", location.getX());
        config.get(ConfigFile.CONFIG).set(player.getName() + ".home." + home + ".y", location.getY());
        config.get(ConfigFile.CONFIG).set(player.getName() + ".home." + home + ".z", location.getZ());
        config.get(ConfigFile.CONFIG).set(player.getName() + ".home." + home + ".world", location.getWorld().getName());
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while setting player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets a new home for a player.
     * 
     * @param player Player
     * @param location Home location
     */
    public void setHome(Player player, Location location) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".home.x", location.getX());
        config.get(ConfigFile.CONFIG).set(player.getName() + ".home.y", location.getY());
        config.get(ConfigFile.CONFIG).set(player.getName() + ".home.z", location.getZ());
        config.get(ConfigFile.CONFIG).set(player.getName() + ".home.world", location.getWorld().getName());
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while setting player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets a new warp.
     * 
     * @param location Warp location
     * @param warp Warp name
     */
    public void setWarp(Location location, String warp) {
        config.get(ConfigFile.CONFIG).set("settings.warps." + warp + ".x", location.getX());
        config.get(ConfigFile.CONFIG).set("settings.warps." + warp + ".y", location.getY());
        config.get(ConfigFile.CONFIG).set("settings.warps." + warp + ".z", location.getZ());
        config.get(ConfigFile.CONFIG).set("settings.warps." + warp + ".world", location.getWorld().getName());
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while setting warp: " + ex.toString());
        }
    }
    
    /**
     * Removes a warp.
     * 
     * @param warp Warp to remove
     */
    public void removeWarp(String warp) {
        config.get(ConfigFile.CONFIG).set("settings.warps." + warp + "", null);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while removing warp: " + ex.toString());
        }
    }
    
    /**
     * Sets a player's nickname.
     * 
     * @param player Player
     * @param nick Nickname
     */
    public void setNick(Player player, String nick) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".nickname", nick);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while setting player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets god mode for a player.
     * 
     * @param player Player
     * @param enabled True if god mode should be on
     */
    public void setGod(Player player, boolean enabled) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".godmode", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while setting player specific setting: " + ex.toString());
        }
    }
}
