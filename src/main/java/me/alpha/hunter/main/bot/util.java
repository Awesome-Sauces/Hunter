package me.alpha.hunter.main.bot;

import org.bukkit.entity.Player;

public class util {
    public static int getMaxBotCount(Player player){
        int bots = 0;

        if(player.hasPermission("MVP++")){
            bots += 10;
        }else if(player.hasPermission("MVP+")){
            bots += 5;
        }else if(player.hasPermission("MVP")){
            bots += 3;
        }else if(player.hasPermission("VIP+")){
            bots += 3;
        }else if(player.hasPermission("VIP")){
            bots+=3;
        }else{
            return 0;
        }

        return bots;
    }
}
