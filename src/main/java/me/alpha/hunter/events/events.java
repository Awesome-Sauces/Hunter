package me.alpha.hunter.events;

import me.alpha.hunter.api.hunterTrait;
import me.alpha.hunter.main.math.findLocation;
import net.citizensnpcs.api.ai.TargetType;
import net.citizensnpcs.api.ai.event.NavigationCompleteEvent;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static me.alpha.hunter.main.hunterUtils.gearNearby;


public class events implements Listener {

    /*
    @EventHandler
    public void handleDeath(NPCDeathEvent event){
        NPC npc = event.getNPC();

        npc.getNavigator().cancelNavigation();
        npc.destroy();
        npc.despawn(DespawnReason.DEATH);
        npc.spawn(new Location(Bukkit.getWorld("world"), 0, 90, 0), SpawnReason.RESPAWN);
        npc.teleport(new Location(Bukkit.getWorld("world"), 0, 90, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

     */

/*
    @EventHandler
    public void handleNewLocation(NavigationCompleteEvent event) {
        if (event.getNPC() == null) {
            return;
        }

        if(event.getNPC().hasTrait(hunterTrait.class)){
            NPC npc = event.getNPC();
            Location location = findLocation.getLocation();
            npc.getNavigator().setTarget(location);

        }

    }

 */


    @EventHandler
    public void handleDeath(NPCDamageByEntityEvent event){
        NPC npc = event.getNPC();
        Player player = (Player) event.getNPC().getEntity();
        if(npc.getEntity() != null){
            if(player.getHealth() - event.getDamage() <= 4){
                event.setCancelled(true);
                player.setHealth(player.getMaxHealth());
                npc.teleport(new Location(Bukkit.getWorld("world"), 0.5, 75, 0.5), PlayerTeleportEvent.TeleportCause.PLUGIN);
                PlayerDeathEvent events = new PlayerDeathEvent(player, null, 1, 1, "fold harder");
                Bukkit.getServer().getPluginManager().callEvent(events);
            }
        }
    }

}
