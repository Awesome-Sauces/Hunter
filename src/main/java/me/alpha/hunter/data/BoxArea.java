package me.alpha.hunter.data;

import me.alpha.hunter.hunter;

import java.util.HashMap;

public class BoxArea {
    private static hunter plugin;

    public BoxArea(hunter instance){
        plugin = instance;
    }

    public static HashMap<String, Integer> BoxArea = new HashMap<>();

    public static void setDefaultBoxAreas(){
        if(BoxArea.isEmpty()){
            BoxArea.put("map", 82); // Map is the one used by default, if you use another map, change this value.
            BoxArea.put("og", 82);
            BoxArea.put("king", 71);
            BoxArea.put("beach", 82);
        }
    }

    public static void setBoxArea(String world, int Y){
        BoxArea.put(world, Y);
    }

    public static int getBoxArea(){
        return BoxArea.get("map");
    }

    public static HashMap<String, Integer> getBoxAreaMap(){
        return BoxArea;
    }

    public static hunter getPlugin(){
        return plugin;
    }
}
