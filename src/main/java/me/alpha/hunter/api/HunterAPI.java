package me.alpha.hunter.api;

import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.data.HunterBots;
import me.alpha.hunter.data.SpawnedBots;
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

import java.util.*;

public class HunterAPI {

    public static String getRandomName(){
        String bot = "MrShabadoo30000\n" +
                "Axe2Grind\n" +
                "EpicCat345\n" +
                "ItzWiqu\n" +
                "Enen07\n" +
                "Knack7596\n" +
                "StarFallAva\n" +
                "Trintynt\n" +
                "SilencedVoice90\n" +
                "BeKinderToMe\n" +
                "aBruno\n" +
                "Skadey\n" +
                "matscoboy\n" +
                "TW9User\n" +
                "Latshi\n" +
                "BulkStraw\n" +
                "3cpscombo\n" +
                "Keruto_\n" +
                "StarFallAva\n" +
                "SilencedVoice90\n" +
                "Ferd69\n" +
                "JustSomeLoaf\n" +
                "ItsZERD\n" +
                "Hurricane13579\n" +
                "HasjEnjoyer\n" +
                "drenjamin\n" +
                "PapaBold\n" +
                "Chobblesome\n" +
                "ICEASM\n" +
                "bizbirikos21\n" +
                "Pit_Hy\n" +
                "Psui666\n" +
                "Hangry1221\n" +
                "carriedbyluck\n" +
                "ASAjagt";

        List<String> bots = Arrays.asList(bot.split("\n"));

        Collections.shuffle(bots);
        return bots.get(5);
    }

    public static NPC createHunterNon(Location loc, int time, boolean outskirt){

        if(!outskirt) return HunterAPI.createTargetHunter(null, getRandomName(), loc, 1, 7,
                time, 5, hunterArmor.IronSword, null,
                hunterArmor.ChainChestplate, hunterArmor.ChainLeggings, hunterArmor.ChainBoots);
        return HunterAPI.createOutSkirtHunter(null, getRandomName(), loc, 1, 7,
                time, 5, hunterArmor.IronSword, null,
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

        HunterTarget targeter = new HunterTarget(npc,damage);

        Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
            @Override
            public void run() {
                npc.getNavigator().setTarget(target.getLocation());
                targeter.run();
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
                        ((Player) npc.getEntity()).setVelocity(new Vector(0, .36, 0));



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

    public static NPC createTargetHunter(UUID OWNER, String hunter_name, Location target, int speed, int jumpTime, int time, double damage, ItemStack sword, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots){

        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, hunter_name);

        //String uuid = String.valueOf(target.getUniqueId());

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, boots);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, leggings);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, chestplate);
        if(helmet != null) npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, helmet);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, sword);
        npc.setBukkitEntityType(EntityType.PLAYER);
        npc.getOrAddTrait(hunterTrait.class);

        /*if (npc.hasTrait(hunterTrait.class)) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Successfully created a Hunter!");
        }

         */

        HunterBots.addHunterBot(npc);

        if (!npc.isSpawned()){
            npc.spawn(target);
        }

        npc.teleport(target, PlayerTeleportEvent.TeleportCause.PLUGIN);


        npc.getNavigator().getDefaultParameters()
                .attackRange(30)
                .speedModifier(Math.max(2, speed));


        npc.setProtected(false);

        HunterTarget targeter = new HunterTarget(npc,damage);

        Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
            @Override
            public void run() {
                targeter.run();

            }
        }, 20L);


        BukkitTask runnable = new BukkitRunnable() {

            @Override
            public void run() {
                if(npc.isSpawned()){

                    npc.faceLocation(npc.getNavigator().getTargetAsLocation());

                    if(npc.getEntity().getLocation().distance(new Location(npc.getEntity().getWorld(), 0, 82, 0))>=10){
                        if(npc.getEntity().isOnGround()){
                            npc.getNavigator().cancelNavigation();
                            npc.getNavigator().setTarget(findLocation.getLocation(npc));
                        }
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

                    if(npc.getEntity().isOnGround()) ((Player) npc.getEntity()).setVelocity(new Vector(0, .36/*Previously 38*/, 0));
                }
            }
        }.runTaskTimer(BoxArea.getPlugin(), jumpTime, jumpTime);


        if (time > 0) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if(npc.isSpawned()){
                        runnable.cancel();
                        if(OWNER!=null) SpawnedBots.removeRegisteredBot(OWNER);
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

    public static NPC createOutSkirtHunter(UUID OWNER, String hunter_name, Location target, int speed, int jumpTime, int time, double damage, ItemStack sword, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots){

        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, hunter_name);

        //String uuid = String.valueOf(target.getUniqueId());

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, boots);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, leggings);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, chestplate);
        if(helmet != null) npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, helmet);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, sword);
        npc.setBukkitEntityType(EntityType.PLAYER);
        npc.getOrAddTrait(hunterTrait.class);

        /*if (npc.hasTrait(hunterTrait.class)) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Successfully created a Hunter!");
        }

         */

        HunterBots.addHunterBot(npc);

        if (!npc.isSpawned()){
            npc.spawn(target);
        }

        npc.teleport(target, PlayerTeleportEvent.TeleportCause.PLUGIN);


        npc.getNavigator().getDefaultParameters()

                .pathDistanceMargin(500)
                .attackRange(30)
                .speedModifier(Math.max(2, speed));


        npc.setProtected(false);

        HunterTarget targeter = new HunterTarget(npc,damage);

        Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
            @Override
            public void run() {
                targeter.run(true);

            }
        }, 20L);


        BukkitTask runnable = new BukkitRunnable() {

            @Override
            public void run() {
                if(npc.isSpawned()){

                    npc.faceLocation(npc.getNavigator().getTargetAsLocation());

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

                    if(npc.getEntity().isOnGround()) ((Player) npc.getEntity()).setVelocity(new Vector(0, .36/*Previously 38*/, 0));
                }
            }
        }.runTaskTimer(BoxArea.getPlugin(), jumpTime, jumpTime);


        if (time > 0) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if(npc.isSpawned()){
                        runnable.cancel();
                        if(OWNER!=null) SpawnedBots.removeRegisteredBot(OWNER);
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
