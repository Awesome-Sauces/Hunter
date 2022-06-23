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


        locationList.add(new Location(Bukkit.getWorld("world"), -3.5, 82, -4.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), 2.5, 82, -3.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -0.5, 82, -1.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -2.5, 82, 0.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -5.5, 82, 1.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), -3.5, 82, 4.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), 1.5, 82, 5.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), 5.5, 82, 4.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), 5.5, 82, 1.5));
        //
        locationList.add(new Location(Bukkit.getWorld("world"), 3.5, 82, -0.5));

        Collections.shuffle(locationList);


        return locationList.get(0);

    }
}