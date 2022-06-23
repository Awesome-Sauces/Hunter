package me.alpha.hunter.api;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;


public class BotConfigHelper implements Listener {
    private static final Map<Player, BotConfig> BotConfigHandler = new HashMap<Player, BotConfig>();

    public static BotConfig botConfigExists(Player player){
        if (BotConfigHandler.containsKey(player)){
            return BotConfigHandler.get(player);
        }else{
            BotConfigHandler.put(player, new BotConfig(player));

            return BotConfigHandler.get(player);
        }
    }

    public static HashMap<String, Integer> spawnedBots = new HashMap<>();

    public static void addSpawnedBot(String player){
        if(!spawnedBots.containsKey(player))spawnedBots.put(player, 1);
        else spawnedBots.put(player, spawnedBots.get(player) + 1);
    }

    public static void removeSpawnedBot(String player, int amount){
        if(spawnedBots.containsKey(player)) spawnedBots.put(player, Math.max(0, spawnedBots.get(player) - amount));
    }

    public static void removeSpawnedBot(String player){
        if(spawnedBots.containsKey(player)) spawnedBots.put(player, spawnedBots.get(player) - 1);
    }

    public static int getSpawnedBot(String player){
        if(!spawnedBots.containsKey(player)) {
            spawnedBots.put(player, 0);
        }
        return spawnedBots.get(player);
    }

}
