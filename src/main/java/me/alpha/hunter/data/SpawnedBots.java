package me.alpha.hunter.data;

import java.util.HashMap;
import java.util.UUID;

public class SpawnedBots {
    private static HashMap<UUID, Integer> registeredBots = new HashMap<>();

    public static void addRegisteredBots(UUID uuid){
        if (registeredBots.containsKey(uuid)) registeredBots.put(uuid, registeredBots.get(uuid)+1);
        else registeredBots.put(uuid, 1);
    }

    public static int getRegisteredBots(UUID uuid){
        return registeredBots.getOrDefault(uuid, 0);
    }

    public static void removeRegisteredBot(UUID uuid){
        if(registeredBots.containsKey(uuid)) registeredBots.put(uuid, registeredBots.get(uuid)-1);
    }
}
