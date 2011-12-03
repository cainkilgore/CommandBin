// Package Declaration
package me.iffa.trashcan.commands;

// Java Imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.admin.AboutCommand;
import me.iffa.trashcan.commands.admin.BroadcastCommand;
import me.iffa.trashcan.commands.admin.CreateworldCommand;
import me.iffa.trashcan.commands.admin.DebugCommand;
import me.iffa.trashcan.commands.admin.PaidCommand;
import me.iffa.trashcan.commands.admin.SetspawnCommand;
import me.iffa.trashcan.commands.admin.ShutdownCommand;
import me.iffa.trashcan.commands.admin.TimeCommand;
import me.iffa.trashcan.commands.admin.UnloadworldCommand;
import me.iffa.trashcan.commands.admin.WeatherCommand;
import me.iffa.trashcan.commands.admin.WhoCommand;
import me.iffa.trashcan.commands.fun.CrossbowCommand;
import me.iffa.trashcan.commands.fun.ExplodeCommand;
import me.iffa.trashcan.commands.fun.ExplosionBowCommand;
import me.iffa.trashcan.commands.fun.ExplosionstickCommand;
import me.iffa.trashcan.commands.fun.FacepalmCommand;
import me.iffa.trashcan.commands.fun.JoinCommand;
import me.iffa.trashcan.commands.fun.LeaveCommand;
import me.iffa.trashcan.commands.fun.LightCommand;
import me.iffa.trashcan.commands.fun.LightningstickCommand;
import me.iffa.trashcan.commands.fun.RollCommand;
import me.iffa.trashcan.commands.fun.ShootCommand;
import me.iffa.trashcan.commands.fun.SlapCommand;
import me.iffa.trashcan.commands.fun.SmokeCommand;
import me.iffa.trashcan.commands.fun.SnowmanCommand;
import me.iffa.trashcan.commands.fun.StrikeCommand;
import me.iffa.trashcan.commands.fun.TorchbowCommand;
import me.iffa.trashcan.commands.general.ArmorCommand;
import me.iffa.trashcan.commands.general.ClearCommand;
import me.iffa.trashcan.commands.general.CreativeCommand;
import me.iffa.trashcan.commands.general.FeedCommand;
import me.iffa.trashcan.commands.general.HealCommand;
import me.iffa.trashcan.commands.general.HelpCommand;
import me.iffa.trashcan.commands.general.HomeCommand;
import me.iffa.trashcan.commands.general.HomeCommand2;
import me.iffa.trashcan.commands.general.ItemCommand;
import me.iffa.trashcan.commands.general.MOTDCommand;
import me.iffa.trashcan.commands.general.MeCommand;
import me.iffa.trashcan.commands.general.MoreCommand;
import me.iffa.trashcan.commands.general.MsgCommand;
import me.iffa.trashcan.commands.general.MytimeCommand;
import me.iffa.trashcan.commands.general.NickCommand;
import me.iffa.trashcan.commands.general.PutCommand;
import me.iffa.trashcan.commands.general.SethomeCommand;
import me.iffa.trashcan.commands.general.SethomeCommand2;
import me.iffa.trashcan.commands.general.SetxpCommand;
import me.iffa.trashcan.commands.general.SpawnCommand;
import me.iffa.trashcan.commands.general.SpawnmobCommand;
import me.iffa.trashcan.commands.general.SurvivalCommand;
import me.iffa.trashcan.commands.general.UnlimitedCommand;
import me.iffa.trashcan.commands.general.UnstuckCommand;
import me.iffa.trashcan.commands.general.UpCommand;
import me.iffa.trashcan.commands.general.UsageCommand;
import me.iffa.trashcan.commands.general.WarpCommand;
import me.iffa.trashcan.commands.general.tp.TeleportCommand;
import me.iffa.trashcan.commands.general.tp.TeleportallCommand;
import me.iffa.trashcan.commands.general.tp.TeleporthereCommand;
import me.iffa.trashcan.commands.general.tp.TeleportworldCommand;
import me.iffa.trashcan.commands.moderator.BanCommand;
import me.iffa.trashcan.commands.moderator.BanIPCommand;
import me.iffa.trashcan.commands.moderator.DelwarpCommand;
import me.iffa.trashcan.commands.moderator.FreezeCommand;
import me.iffa.trashcan.commands.moderator.HandicapCommand;
import me.iffa.trashcan.commands.moderator.KickCommand;
import me.iffa.trashcan.commands.moderator.KillCommand;
import me.iffa.trashcan.commands.moderator.MuteCommand;
import me.iffa.trashcan.commands.moderator.SetwarpCommand;
import me.iffa.trashcan.commands.moderator.UnbanCommand;
import me.iffa.trashcan.commands.moderator.UnbanIPCommand;

/**
 * Represents a command of TrashCan.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public abstract class TrashCommand {
    // Variables
    protected String label;
    private static Map<String, TrashCommand> commands = new HashMap<String, TrashCommand>();
    private static List<String> commandsList = new ArrayList<String>();
    private static TrashCommandExecutor executor = new TrashCommandExecutor();
    
    /**
     * Constructor of TrashCommand.
     * 
     * @param label Command label
     */
    public TrashCommand(String label) {
        this.label = label;
    }
    
    /**
     * Gets the Map containing all commands of TrashCan.
     * 
     * @return Map containing all commands
     */
    public static Map<String, TrashCommand> getCommands() {
        return commands;
    }
    
    /**
     * Gets the List containing all commands of TrashCan.
     * 
     * @return List containing all commands
     */
    public static List<String> getCommandsList() {
        return commandsList;
    }
    
    /**
     * Initializes all commands and adds them to the Map containing each command.
     * This way no if-elseif-statements are needed in CommandExecutors, as they
     * will simply scan through the Map for a matching command.
     */
    public static void initializeCommands() {
        // Administration commands
        commands.put("debug", new DebugCommand("debug"));
        commands.put("trashcan", new AboutCommand("trashcan"));
        commands.put("paid", new PaidCommand("paid"));
        commands.put("who", new WhoCommand("who"));
        commands.put("shutdown", new ShutdownCommand("shutdown"));
        commands.put("createworld", new CreateworldCommand("createworld"));
        commands.put("unloadworld", new UnloadworldCommand("unloadworld"));
        commands.put("weather", new WeatherCommand("weather"));
        commands.put("time", new TimeCommand("time"));
        commands.put("setspawn", new SetspawnCommand("setspawn"));
        commands.put("broadcast", new BroadcastCommand("broadcast"));
        
        // General commands
        commands.put("motd", new MOTDCommand("motd"));
        commands.put("me", new MeCommand("me"));
        commands.put("creative", new CreativeCommand("creative"));
        commands.put("survival", new SurvivalCommand("survival"));
        commands.put("spawnmob", new SpawnmobCommand("spawnmob"));
        // TODO: Add command for non-multihome /home
        commands.put("home", TrashCan.getConfigHandler().getMultiHomes() ? new HomeCommand("home") : new HomeCommand2("home"));
        // TODO: Add command for non-multihome /sethome
        commands.put("sethome", TrashCan.getConfigHandler().getMultiHomes() ? new SethomeCommand("sethome") : new SethomeCommand2("sethome"));
        commands.put("nick", new NickCommand("nick"));
        commands.put("warp", new WarpCommand("warp"));
        commands.put("usage", new UsageCommand("usage"));
        commands.put("help", new HelpCommand("help"));
        commands.put("item", new ItemCommand("item"));
        commands.put("more", new MoreCommand("more"));
        commands.put("feed", new FeedCommand("feed"));
        commands.put("heal", new HealCommand("heal"));
        commands.put("setxp", new SetxpCommand("setxp"));
        commands.put("mytime", new MytimeCommand("mytime"));
        commands.put("up", new UpCommand("up"));
        commands.put("unlimited", new UnlimitedCommand("unlimited"));
        commands.put("put", new PutCommand("put"));
        commands.put("spawn", new SpawnCommand("spawn"));
        commands.put("tp", new TeleportCommand("tp"));
        commands.put("tpall", new TeleportallCommand("tpall"));
        commands.put("tphere", new TeleporthereCommand("tphere"));
        commands.put("msg", new MsgCommand("msg"));
        commands.put("tpworld", new TeleportworldCommand("tpworld"));
        commands.put("clear", new ClearCommand("clear"));
        commands.put("armor", new ArmorCommand("armor"));
        commands.put("unstuck", new UnstuckCommand("unstuck"));
        
        // Fun commands
        commands.put("crossbow", new CrossbowCommand("crossbow"));
        commands.put("explosionbow", new ExplosionBowCommand("explosionbow"));
        commands.put("torchbow", new TorchbowCommand("torchbow"));
        commands.put("join", new JoinCommand("join"));
        commands.put("leave", new LeaveCommand("leave"));
        commands.put("facepalm", new FacepalmCommand("facepalm"));
        commands.put("snowman", new SnowmanCommand("snowman"));
        commands.put("explode", new ExplodeCommand("explode"));
        commands.put("light", new LightCommand("light"));
        commands.put("roll", new RollCommand("roll"));
        commands.put("lightningstick", new LightningstickCommand("lightningstick"));
        commands.put("explosionstick", new ExplosionstickCommand("explosionstick"));
        commands.put("smoke", new SmokeCommand("smoke"));
        commands.put("strike", new StrikeCommand("strike"));
        commands.put("shoot", new ShootCommand("shoot"));
        commands.put("slap", new SlapCommand("slap"));
        
        // Moderating commands
        commands.put("kick", new KickCommand("kick"));
        commands.put("ban", new BanCommand("ban"));
        commands.put("mute", new MuteCommand("mute"));
        commands.put("unban", new UnbanCommand("unban"));
        commands.put("unbanip", new UnbanIPCommand("unbanip"));
        commands.put("freeze", new FreezeCommand("freeze"));
        commands.put("banip", new BanIPCommand("banip"));
        commands.put("handicap", new HandicapCommand("handicap"));
        commands.put("setwarp", new SetwarpCommand("setwarp"));
        commands.put("delwarp", new DelwarpCommand("delwarp"));
        commands.put("kill", new KillCommand("kill"));
//        
        // Adding each command to the List of all commands.
        for (String cmd : commands.keySet()) {
            commandsList.add(cmd);
        }
        
        // Adding all commands to the CommandExecutor.
        @SuppressWarnings("unchecked")
        Map<String, Map<String, Object>> pluginCommands = (Map<String, Map<String, Object>>) TrashCan.getDescriptionFile().getCommands();
        for (String command : pluginCommands.keySet()) {
            Bukkit.getPluginCommand(command).setExecutor(executor);
        }
        
        
    }
    
    /**
     * Executes the command.
     * 
     * @param cs Command sender
     * @param args Command arguments
     * 
     * @return True if no usage information should be sent to the command sender.
     */
    public abstract boolean executeCommand(CommandSender cs, String[] args);
    
    /**
     * Sends command usage information to the command sender. Called by default if 
     * executeCommand() returns false.
     * 
     * @param cs Command sender
     */
    public abstract void sendUsage(CommandSender cs);
}
