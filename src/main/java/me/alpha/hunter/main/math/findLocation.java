package me.alpha.hunter.main.math;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class findLocation {
    public static Location getLocation(){

        List<Location> locationList = new ArrayList<Location>();


        locationList.add(new Location(Bukkit.getWorld("world"), 0, 124, 9));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), 2, 124, 12));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), 0, 124, 15));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -2, 124, 17));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -5, 124, 15));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -5, 124, 15));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -7, 124, 12));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -5, 124, 9));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -2, 124, 7));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -2, 124, 12));

        double d = Math.random();

        if (d <= .02) {
            return locationList.get(0);
        }else if (d <= .04) {
            return locationList.get(1);
        }else if (d <= .05) {
            return locationList.get(2);
        }else if (d <= .07) {
            return locationList.get(3);
        }else if (d <= .09) {
            return locationList.get(4);
        }else if (d <= .11) {
            return locationList.get(5);
        }else if (d <= .13) {
            return locationList.get(6);
        }else if (d <= .15) {
            return locationList.get(7);
        }else if (d <= .16) {
            return locationList.get(8);
        }else if (d <= .18) {
            return locationList.get(9);
        }

        return locationList.get(0);

    }
}