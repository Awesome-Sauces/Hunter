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
    public static Location getLocation(NPC npc){

        List<Location> locations = new ArrayList<>();
        locations.add(new Location(npc.getEntity().getWorld(), 3, 82, 3));
        locations.add(new Location(npc.getEntity().getWorld(), 0, 82, 0));
        locations.add(new Location(npc.getEntity().getWorld(), -3, 82, 2));
        locations.add(new Location(npc.getEntity().getWorld(), -1, 82, 0));
        locations.add(new Location(npc.getEntity().getWorld(), 0, 82, 0));
        locations.add(new Location(npc.getEntity().getWorld(), 1, 82, -3));
        locations.add(new Location(npc.getEntity().getWorld(), 4, 82, 0));

        Collections.shuffle(locations);

        return locations.get(0);
    }

    public static Location getRandomLocation(Location location){

        List<Location> locations = new ArrayList<>();
        locations.add(new Location(location.getWorld(), location.getX()+1,
                location.getY(),
                location.getZ()+1));
        locations.add(new Location(location.getWorld(), location.getX()-1,
                location.getY(),
                location.getZ()-1));
        locations.add(new Location(location.getWorld(), location.getX()+1,
                location.getY(),
                location.getZ()));
        locations.add(new Location(location.getWorld(), location.getX()-1,
                location.getY(),
                location.getZ()));
        locations.add(new Location(location.getWorld(), location.getX(),
                location.getY(),
                location.getZ()-1));
        locations.add(new Location(location.getWorld(), location.getX(),
                location.getY(),
                location.getZ()+1));

        Collections.shuffle(locations);

        return locations.get(0);
    }

    public static Location getOutskirtsLocation(NPC npc){

        List<Location> locations = new ArrayList<>();

        // Four Seasons
        locations.add(new Location(npc.getEntity().getWorld(), -57, 87, -38));
        locations.add(new Location(npc.getEntity().getWorld(), -41, 87, -62));
        locations.add(new Location(npc.getEntity().getWorld(), -58, 86, -64));

        locations.add(new Location(npc.getEntity().getWorld(), 41, 85, -12));
        locations.add(new Location(npc.getEntity().getWorld(), 40, 85, -32));
        locations.add(new Location(npc.getEntity().getWorld(), 20, 85, -37));

        locations.add(new Location(npc.getEntity().getWorld(), 39, 85, 46));
        locations.add(new Location(npc.getEntity().getWorld(), 46, 85, 34));
        locations.add(new Location(npc.getEntity().getWorld(), 34, 85, 61));

        /* Crab MAP
        locations.add(new Location(npc.getEntity().getWorld(), -29, 84, -28));
        locations.add(new Location(npc.getEntity().getWorld(), -15, 88, -47));
        locations.add(new Location(npc.getEntity().getWorld(), -21, 88, -67));
        locations.add(new Location(npc.getEntity().getWorld(), 24, 91, -88));
        locations.add(new Location(npc.getEntity().getWorld(), -45, 92, -77));
        locations.add(new Location(npc.getEntity().getWorld(), -43, 95, -58));
        locations.add(new Location(npc.getEntity().getWorld(), -72, 88, -46));

        locations.add(new Location(npc.getEntity().getWorld(), -57, 90, 16));
        locations.add(new Location(npc.getEntity().getWorld(), -57, 90, 16));
        locations.add(new Location(npc.getEntity().getWorld(), -74, 94, 54));
        locations.add(new Location(npc.getEntity().getWorld(), -57, 94, 72));
        locations.add(new Location(npc.getEntity().getWorld(), -25, 90, 65));
        locations.add(new Location(npc.getEntity().getWorld(), -58, 85, 78));
        locations.add(new Location(npc.getEntity().getWorld(), -57, 85, 54));

        locations.add(new Location(npc.getEntity().getWorld(), 11, 86, 89));
        locations.add(new Location(npc.getEntity().getWorld(), 11, 86, 89));
        locations.add(new Location(npc.getEntity().getWorld(), 24, 84, 42));
        locations.add(new Location(npc.getEntity().getWorld(), 55, 87, 29));
        locations.add(new Location(npc.getEntity().getWorld(), 83, 87, 32));
        locations.add(new Location(npc.getEntity().getWorld(), 76, 93, 65));
        locations.add(new Location(npc.getEntity().getWorld(), 63, 93, 58));

        locations.add(new Location(npc.getEntity().getWorld(), 41, 88, -73));
        locations.add(new Location(npc.getEntity().getWorld(), 41, 88, -73));
        locations.add(new Location(npc.getEntity().getWorld(), 25, 88, -84));
        locations.add(new Location(npc.getEntity().getWorld(), 25, 88, -84));
        locations.add(new Location(npc.getEntity().getWorld(), 63, 86, -38));
        locations.add(new Location(npc.getEntity().getWorld(), 75, 94, -65));
        locations.add(new Location(npc.getEntity().getWorld(), 61, 91, -69));


         */
        Collections.shuffle(locations);

        return locations.get(0);
    }
}