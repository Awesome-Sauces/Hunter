package me.alpha.hunter.main;

import me.alpha.hunter.api.HunterTarget;
import me.alpha.hunter.api.hunterTrait;
import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.data.HunterBots;
import me.alpha.hunter.items.hunterArmor;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

import net.citizensnpcs.api.trait.trait.Equipment;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.*;

import static me.alpha.hunter.api.HunterInFace.getTargetPlayer;


public class hunterUtils {


    public static List<Player> gearNearby(Entity hunter, double x) {

        List<Player> playerList = new ArrayList<Player>();

        if (hunter == null) {
            return playerList;
        }

        if (hunter != null) {
            List<Entity> entityList = hunter.getNearbyEntities(x, x, x);
            for (Entity player : entityList) {
                if (hunter.getLocation().distance(player.getLocation()) <= x) {
                    if (player instanceof Player) playerList.add((Player) player);
                }
            }
        }


        //for (Entity player : hunter.getNearbyEntities(x, y, z)){


        return playerList;
    }

    public static Player getClosetNearby(Player hunter) {
        if(hunter==null) return null;


        List<Player> playerList = new ArrayList<Player>();
        List<NPC> npcList = new ArrayList<NPC>();

        for(NPC npc : CitizensAPI.getNPCRegistry()){
            if(npc.getEntity() != null && npc.getEntity().getType().equals(EntityType.PLAYER) &&
            npc.getEntity().getWorld().equals(hunter.getWorld()) &&
            !npc.isProtected() && npc.getEntity() != hunter) playerList.add((Player)npc.getEntity());
        }

        for (Player player : Bukkit.getOnlinePlayers()){
            if(player.getWorld().equals(hunter.getWorld()) &&
            player != hunter){
                playerList.add(player);
            }
        }

        Player lastPlayer = null;

        for (Player players : playerList){
            if(lastPlayer==null){
                lastPlayer=players;
            }else{
                double distance1 = hunter.getLocation().distance(lastPlayer.getLocation());
                double distance2 = hunter.getLocation().distance(players.getLocation());

                if(distance2 > distance1){
                    continue;
                }else if (distance2 < distance1){
                    lastPlayer=players;
                    continue;
                }else{
                    lastPlayer=players;
                }

            }
        }


        return lastPlayer;
    }

    public static Player getNearbyPlayers(Entity hunter, int x) {

        if (hunter != null) {
            Player player = getTargetPlayer((Player) hunter, x);
            if (player != null) {
                return player;
            }
        }


        return null;
    }

    public static boolean isNPC(Player player) {
        for (NPC npc : CitizensAPI.getNPCRegistry()) {
            if (npc.getEntity() == player) return true;
        }
        return false;
    }

    public static NPC getNPCbyPlayer(Player player) {
        for (NPC npc : CitizensAPI.getNPCRegistry()) {
            if (npc.getEntity() == player) return npc;
        }
        return null;
    }

    public static void createHunterBot(String hunter_name) {

        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, hunter_name);


        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, hunterArmor.ChainBoots);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, hunterArmor.ChainLeggings);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, hunterArmor.ChainChestplate);

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, hunterArmor.IronSword);

        npc.setBukkitEntityType(EntityType.PLAYER);

        npc.getOrAddTrait(hunterTrait.class);

        if (npc.hasTrait(hunterTrait.class)) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Successfully created a Hunter!");
        }

    }

    public static NPC createHunterBot(String hunter_name, boolean t) {

        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, hunter_name);


        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, hunterArmor.ChainBoots);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, hunterArmor.ChainLeggings);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, hunterArmor.ChainChestplate);

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, hunterArmor.IronSword);

        npc.setBukkitEntityType(EntityType.PLAYER);

        npc.getOrAddTrait(hunterTrait.class);

        if (npc.hasTrait(hunterTrait.class)) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Successfully created a Hunter!");
            return npc;
        }

        return npc;
    }

}
