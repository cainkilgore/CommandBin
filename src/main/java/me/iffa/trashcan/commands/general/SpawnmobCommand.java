// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

/**
 * Represents /spawnmob.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class SpawnmobCommand extends TrashCommand {
    /**
     * Constructor of SpawnmobCommand.
     * 
     * @param label Command label
     */
    public SpawnmobCommand(String label) {
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
        if (!cs.hasPermission("trashcan.general.spawnmob")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission.");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        Player player = (Player) cs;
        Location spawn = player.getLocation();
        spawn.setY(spawn.getBlockY() + 1);
        if (args.length == 1) {
            CreatureType mob = CreatureType.fromName(args[0]);
            if (mob == null) {
                MessageUtil.sendMessage(player, ChatColor.RED + "'" + args[0] + "' is not a mob.");
                return true;
            }
            LivingEntity spawnCreature = player.getWorld().spawnCreature(spawn, mob);
            // "You can't spawn tamed wolves."
            if (spawnCreature instanceof Wolf && TrashCan.getConfigHandler().getTameSpawnedWolves()) {
                Wolf wolf = (Wolf) spawnCreature;
                wolf.setOwner(player);
            }
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Spawned 1 of " + args[0] + ".");
        } else {
            CreatureType mob = CreatureType.fromName(args[0]);
            int amount = 1;
            if (mob == null) {
                MessageUtil.sendMessage(player, ChatColor.RED + "'" + args[0] + "' is not a mob.");
                return true;
            }
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                return false;
            }
            if (amount > TrashCan.getConfigHandler().getMaxMobAmount()) {
                MessageUtil.sendMessage(player, ChatColor.RED + "You can only spawn " + TrashCan.getConfigHandler().getMaxMobAmount() + " mobs at once.");
                return true;
            }
            for (int spawned = 0; spawned < amount; spawned++) {
                LivingEntity spawnCreature = player.getWorld().spawnCreature(spawn, mob);
                // "You can't spawn tamed wolves."
                if (spawnCreature instanceof Wolf && TrashCan.getConfigHandler().getTameSpawnedWolves()) {
                    Wolf wolf = (Wolf) spawnCreature;
                    wolf.setOwner(player);
                }
            }
            MessageUtil.sendMessage(player, ChatColor.GOLD + "Spawned " + amount + " of " + args[0] + ".");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /spawnmob <mob> [amount]");
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Mobs: chicken, cow, pig, sheep, squid, enderman, pigman, wolf, cavespider, creeper, ghast, silverfish, skeleton, slime, spider, zombie, enderdragon, villager");
    }
}
