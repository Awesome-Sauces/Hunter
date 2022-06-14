package me.alpha.hunter.apis;

import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.hunter;

import static me.alpha.hunter.data.BoxArea.getBoxAreaMap;

public class configAPI {

    private static final hunter plugin = BoxArea.getPlugin();

    public static void saveBoxArea(){
        for(String p : getBoxAreaMap().keySet()){
            plugin.getConfig().set("BoxArea."+p, BoxArea.getBoxAreaMap().get(p));
        }
        plugin.saveConfig();
    }

    public static void loadBoxArea(){
        if (!plugin.getConfig().contains("BoxArea")) return;
        for (String s : plugin.getConfig().getConfigurationSection("BoxArea").getKeys(false))
        {
            BoxArea.setBoxArea(s, plugin.getConfig().getInt("BoxArea."+s));
        }
    }

}