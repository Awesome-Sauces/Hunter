package me.alpha.hunter.api;

import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.data.HunterBots;
import me.alpha.hunter.main.hunterUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.LookClose;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class HunterAPI {
    public static NPC createTargetHunter(String hunter_name, Player target, int speed, int jumpTime, int time){

        NPC npc = hunterUtils.createHunterBot(hunter_name, true);
        HunterBots.addHunterBot(npc);

        npc.spawn(target.getLocation());
        npc.teleport(target.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);

        npc.getOrAddTrait(LookClose.class);
        npc.getOrAddTrait(LookClose.class).toggle();

        npc.getNavigator().getDefaultParameters()
                .range(30)
                .attackRange(30)
                .speedModifier(Math.max(2, speed));


        npc.setProtected(false);


        Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
            @Override
            public void run() {
                npc.getNavigator().setTarget(target.getLocation());
                new HunterTarget(npc).run();
            }
        }, 20L);


        BukkitTask runnable = new BukkitRunnable() {

            @Override
            public void run() {

                if(npc.isSpawned()){
                    if(npc.getEntity().isOnGround()){
                        ((Player) npc.getEntity()).setVelocity(new Vector(0, .38, 0));



                        //((target) npc.getEntity()).setSneaking(true);
                    }
                }


            }
        }.runTaskTimer(BoxArea.getPlugin(), jumpTime, jumpTime);


        if (time > 0) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if(npc.isSpawned()){
                        runnable.cancel();
                        npc.despawn();
                        npc.destroy();
                        CitizensAPI.getNPCRegistry().deregister(npc);
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&lA target has been removed from your game for hacking or abuse &bThanks for reporting it!"));
                    }
                }
            }, time * 20L);
        }



        return npc;
    }
}
