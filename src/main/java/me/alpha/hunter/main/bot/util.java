package me.alpha.hunter.main.bot;

import org.bukkit.entity.Player;

public class util {
    public static int getMaxBotCount(Player player){
        int bots = 2;

        if(player.hasPermission("VIP")){
            bots++;
        }else if(player.hasPermission("VIP+")){
            bots += 2;
        }else if(player.hasPermission("MVP")){
            bots += 3;
        }else if(player.hasPermission("MVP+")){
            bots += 5;
        }else if(player.hasPermission("MVP++")){
            bots += 8;
        }

        return bots;
    }
}
