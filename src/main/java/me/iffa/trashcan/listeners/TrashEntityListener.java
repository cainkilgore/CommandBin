// Package Declaration
package me.iffa.trashcan.listeners;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EndermanPickupEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * Entity listener for TrashCan features like god mode, creeper explosion protection,
 * explosion bows and anti-Enderman grief.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class TrashEntityListener extends EntityListener {
    // Variables
    private TrashCan plugin;

    /**
     * Constructor of TrashEntityListener.
     * 
     * @param plugin TrashCan instance
     */
    public TrashEntityListener(TrashCan plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when an entity is damaged.
     * 
     * @param e Event data
     */
    @Override
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (TrashCan.getConfigHandler().getGodmode(player)) {
                e.setCancelled(true);
            }
        }
    }

    /**
     * Called when an entity dies.
     * 
     * @param e Event data
     */
    @Override
    public void onEntityDeath(EntityDeathEvent e) {
        if (!TrashCan.getConfigHandler().getDropXPDeath()) {
            e.setDroppedExp(0);
        }
    }

    /**
     * Called when an entity explodes.
     * 
     * @param e Event data
     */
    @Override
    public void onEntityExplode(EntityExplodeEvent e) {
        if (TrashCan.getConfigHandler().getBlockCreeper() && e instanceof Creeper) {
            e.setCancelled(true);
        }
    }
    
    /**
     * Called when an Enderman attempts to pick up a block.
     * 
     * @param e Event data
     */
    @Override
    public void onEndermanPickup(EndermanPickupEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (!TrashCan.getConfigHandler().getEnderGrief()) {
            e.setCancelled(true);;
        }
    }

    /**
     * Called when a projectile hits something solid.
     * 
     * @param e Event data
     */
    @Override
    public void onProjectileHit(final ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            final Arrow arrow = (Arrow) e.getEntity();
            if (!(arrow.getShooter() instanceof Player)) {
                return;
            }
            Player player = (Player) arrow.getShooter();
            if (TrashCan.getConfigHandler().getTorchbow(player)) {
                arrow.getLocation().getBlock().setType(Material.TORCH);
            }
            if (TrashCan.getConfigHandler().getExplosionBow(player)) {
                int radius = TrashCan.getConfigHandler().getBowExplosionRadius();
                e.getEntity().getWorld().createExplosion(arrow.getLocation(), radius);
            }
            
            if (TrashCan.getConfigHandler().getCrossbow(player)) {
                final int radius = TrashCan.getConfigHandler().getBowExplosionRadius();
                arrow.getLocation().getBlock().setType(Material.REDSTONE_TORCH_ON);
                Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                    
                    public void run() {
                        arrow.getLocation().getBlock().setType(Material.REDSTONE_TORCH_OFF);
                        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                            
                            public void run() {
                                arrow.getLocation().getBlock().setType(Material.REDSTONE_TORCH_ON);
                                Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                                    
                                    public void run() {
                                        arrow.getLocation().getBlock().setType(Material.REDSTONE_TORCH_OFF);
                                        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                                            
                                            public void run() {
                                                arrow.getLocation().getBlock().setType(Material.REDSTONE_TORCH_ON);
                                                Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
                                                    
                                                    public void run() {
                                                        arrow.getWorld().createExplosion(e.getEntity().getLocation(), radius);
                                                    }
                                                },
                                                        5L);
                                            }
                                        },
                                                20L);
                                    }
                                },
                                        20L);
                            }
                        },
                                20L);
                    }
                },
                        20L);
                
            }
        }
    }
}
