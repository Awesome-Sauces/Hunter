package me.alpha.hunter.data;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HunterBots {

    private static List<NPC> hunterBots = new ArrayList<>();

    public static void addHunterBot(NPC npc){
        hunterBots.add(npc);
    }

    public static void removeHunterBots(){
        for(NPC npc : hunterBots){
            if(npc.isSpawned()) npc.despawn();
            npc.destroy();
            CitizensAPI.getNPCRegistry().deregister(npc);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&lA player has been removed from your game for hacking or abuse &bThanks for reporting it!"));
        }
    }

}
