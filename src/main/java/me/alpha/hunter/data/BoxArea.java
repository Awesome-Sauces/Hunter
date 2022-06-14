package me.alpha.hunter.data;

import me.alpha.hunter.hunter;

import java.util.HashMap;

public class BoxArea {
    private static hunter plugin;

    public BoxArea(hunter instance){
        plugin = instance;
    }

    public static HashMap<String, Integer> BoxArea = new HashMap<>();



    public static void setBoxArea(String player, int amount){
        BoxArea.put(player, amount);
    }

    public static void addBoxArea(String player, int amount){
        BoxArea.put(player, BoxArea.get(player)+amount);
    }

    public static int getBoxArea(String player){
        return BoxArea.get(player);
    }

    public static HashMap<String, Integer> getBoxAreaMap(){
        return BoxArea;
    }

    public static boolean hasBoxArea(String player){
        if(BoxArea.containsKey(player)){
            return true;
        }else{
            BoxArea.put(player, 0);
        }
        return true;
    }

    public static hunter getPlugin(){
        return plugin;
    }
}
