package me.alpha.hunter.api;

import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.data.HunterBots;
import me.alpha.hunter.items.hunterArmor;
import me.alpha.hunter.main.hunterUtils;
import me.alpha.hunter.main.math.findLocation;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.TargetType;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.UUID;

public class HunterAPI {
    public static NPC createHunterNon(Location loc){
        return HunterAPI.createTargetHunter("Bob", loc, 2, 5,
                0, 8, hunterArmor.IronSword, null,
                hunterArmor.ChainChestplate, hunterArmor.ChainLeggings, hunterArmor.ChainBoots);
    }

    public static NPC createTargetHunter(String hunter_name, Player target, int speed, int jumpTime, int time, double damage){

        NPC npc = hunterUtils.createHunterBot(hunter_name, true);
        HunterBots.addHunterBot(npc);

        if (!npc.isSpawned()){
            npc.spawn(target.getLocation());
        }
        npc.teleport(target.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);

        npc.faceLocation(target.getLocation());

        npc.getNavigator().getDefaultParameters()
                .attackRange(30)
                .speedModifier(Math.max(2, speed));


        npc.setProtected(false);


        Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
            @Override
            public void run() {
                npc.getNavigator().setTarget(target.getLocation());
                new HunterTarget(npc, damage).run();
            }
        }, 20L);


        BukkitTask runnable = new BukkitRunnable() {

            @Override
            public void run() {

                if(npc.isSpawned()){
                    npc.faceLocation(target.getLocation());

/*

                    if(npc.getEntity().getLocation().getY() - 82 >= 10){
                        npc.teleport(new Location(Bukkit.getWorld("world"), 0, 82, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);

                        Location location = findLocation.getLocation();

                        if(npc.getNavigator().isNavigating() && npc.getNavigator().getTargetType().equals(TargetType.ENTITY) && npc.getEntity().getLocation().distance(npc.getNavigator().getEntityTarget().getTarget().getLocation()) >= 4.5){
                            npc.getNavigator().cancelNavigation();
                            npc.getNavigator().setTarget(location);
                        }
                    }

                     */

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

    public static NPC createTargetHunter(String hunter_name, Location target, int speed, int jumpTime, int time, double damage, ItemStack sword, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots){

        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, hunter_name);

        //String uuid = String.valueOf(target.getUniqueId());

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, boots);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, leggings);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, chestplate);
        if(helmet != null) npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, helmet);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, sword);
        npc.setBukkitEntityType(EntityType.PLAYER);
        npc.getOrAddTrait(hunterTrait.class);

        if (npc.hasTrait(hunterTrait.class)) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Successfully created a Hunter!");
        }

        HunterBots.addHunterBot(npc);

        if (!npc.isSpawned()){
            npc.spawn(target);
        }

        npc.teleport(target, PlayerTeleportEvent.TeleportCause.PLUGIN);


        npc.getNavigator().getDefaultParameters()
                .attackRange(30)
                .speedModifier(Math.max(2, speed));


        npc.setProtected(false);


        Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
            @Override
            public void run() {
                new HunterTarget(npc, damage).run();

            }
        }, 20L);


        BukkitTask runnable = new BukkitRunnable() {

            @Override
            public void run() {
                if(npc.isSpawned()){

                    npc.faceLocation(npc.getNavigator().getTargetAsLocation());

                    if (npc.getEntity() != null && npc.getEntity().getLocation().getY() >= 150){
                        npc.teleport(findLocation.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                        npc.getNavigator().setTarget(findLocation.getLocation());
                    }

                    /*

                    if(npc.getEntity().getLocation().getY() - 82 >= 10){
                        npc.teleport(new Location(Bukkit.getWorld("world"), 0, 82, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);

                        Location location = findLocation.getLocation();

                        if(npc.getNavigator().isNavigating() && npc.getNavigator().getTargetType().equals(TargetType.ENTITY) && npc.getEntity().getLocation().distance(npc.getNavigator().getEntityTarget().getTarget().getLocation()) >= 4.5){
                            npc.getNavigator().cancelNavigation();
                            npc.getNavigator().setTarget(location);
                        }
                    }

                     */

                    if(npc.getEntity().isOnGround()) ((Player) npc.getEntity()).setVelocity(new Vector(0, .38, 0));
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
