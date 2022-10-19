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

        List<Location> locations = new ArrayList<>();
        locations.add(new Location(Bukkit.getWorld("world"), -2, 124, 12));
        locations.add(new Location(Bukkit.getWorld("world"), -5, 124, 15));
        locations.add(new Location(Bukkit.getWorld("world"), -5, 124, 9));
        locations.add(new Location(Bukkit.getWorld("world"), 0, 124, 11));
        locations.add(new Location(Bukkit.getWorld("world"), -5, 124, 15));
        locations.add(new Location(Bukkit.getWorld("world"), -1, 124, 15));
        locations.add(new Location(Bukkit.getWorld("world"), -4, 124, 10));

        Collections.shuffle(locations);

        return locations.get(0);
    }
}