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

    @EventHandler
    public static void handleOfflinePlayers(PlayerQuitEvent event){
        Player player = event.getPlayer();

        BotConfigHandler.remove(player);
    }

}
