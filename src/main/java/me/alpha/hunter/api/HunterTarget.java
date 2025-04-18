package me.alpha.hunter.api;

import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.main.hunterUtils;
import me.alpha.hunter.main.math.findLocation;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.TargetType;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

import static me.alpha.hunter.main.hunterUtils.gearNearby;

public class HunterTarget {

    NPC npc;
    double damage;

    public HunterTarget(NPC npc, double damage){
        this.damage = damage;
        this.npc = npc;
        this.npc.getNavigator().getDefaultParameters()
                .attackDelayTicks(5);
    }



    public void run(){
        new BukkitRunnable() {

            @Override
            public void run() {
                if(npc.isSpawned()){


                    //if(npc.getNavigator().getTargetType() != null && npc.getNavigator().getTargetType().equals(TargetType.ENTITY) && npc.getEntity().getLocation().distance(npc.getNavigator().getTargetAsLocation()) <= 3.3)


                    if(!gearNearby(npc.getEntity(), 10).isEmpty()){

                        for (Player players : gearNearby(npc.getEntity(), 5)){
                            if(!CitizensAPI.getNPCRegistry().isNPC(players)){
                                PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity) npc.getEntity()).getHandle(), 0);
                                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(animationPacket);
                            }
                        }

                        Player player = hunterUtils.getNearbyPlayers(npc.getEntity(), 10);

                        if(player != null){

                            if(npc.getEntity().isOnGround()){
                                npc.getNavigator().cancelNavigation();
                                npc.getNavigator().setTarget(findLocation.getLocation(npc));
                            }
                            //npc.getNavigator().setTarget(player, false);
                            npc.faceLocation(npc.getNavigator().getTargetAsLocation());

                            if(npc.getEntity()!=null){
                                if(CitizensAPI.getNPCRegistry().isNPC(player) && player.getLocation().getY()==124 && player.getLocation().distance(npc.getEntity().getLocation()) <= 4 || CitizensAPI.getNPCRegistry().isNPC(player) && player.getLocation().getY() - npc.getEntity().getLocation().getY() <= 1 && player.getLocation().distance(npc.getEntity().getLocation()) <= 4)player.damage(damage, npc.getEntity());
                                else if(!CitizensAPI.getNPCRegistry().isNPC(player) && player.getLocation().distance(npc.getEntity().getLocation()) <= 4 )player.damage(5, npc.getEntity());
                            }

                        }else{
                            //Player nearby = gearNearby(npc.getEntity(), 10).get(0);

                            if(npc.getEntity().isOnGround()){
                                npc.getNavigator().cancelNavigation();
                                npc.getNavigator().setTarget(findLocation.getLocation(npc));
                            }

                        }



                    }/*else{

                        if(npc.getNavigator().isNavigating() && npc.getNavigator().getTargetType() != null && npc.getNavigator().getTargetType().equals(TargetType.ENTITY)){
                            npc.getNavigator().cancelNavigation();
                            npc.getNavigator().setTarget(new Location(Bukkit.getWorld("world"), -2, 124, 12));
                        }



                    }
                    */

                }


            }
        }.runTaskTimer(BoxArea.getPlugin(), 5, 5);
    }

    public void run(boolean outskirt){
        if (outskirt) new BukkitRunnable() {

            @Override
            public void run() {
                if(npc.isSpawned()){


                    //if(npc.getNavigator().getTargetType() != null && npc.getNavigator().getTargetType().equals(TargetType.ENTITY) && npc.getEntity().getLocation().distance(npc.getNavigator().getTargetAsLocation()) <= 3.3)


                    if(!gearNearby(npc.getEntity(), 10).isEmpty()){

                        for (Player players : gearNearby(npc.getEntity(), 5)){
                            if(!CitizensAPI.getNPCRegistry().isNPC(players)){
                                PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity) npc.getEntity()).getHandle(), 0);
                                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(animationPacket);
                            }
                        }

                        Player player = hunterUtils.getNearbyPlayers(npc.getEntity(), 10);

                        if(player != null){

                            if(npc.getEntity().isOnGround() && !npc.getNavigator().isNavigating()){
                                while (true){
                                    Location loc = findLocation.getOutskirtsLocation(npc);
                                    if(loc.distance(npc.getEntity().getLocation())<=75){
                                        npc.getNavigator().cancelNavigation();
                                        npc.getNavigator().setTarget(loc);
                                        break;
                                    }
                                }
                            }
                            //npc.getNavigator().setTarget(player, false);
                            npc.faceLocation(npc.getNavigator().getTargetAsLocation());

                            if(npc.getEntity()!=null){
                                if(CitizensAPI.getNPCRegistry().isNPC(player) && !(player.getLocation().distance(new Location(player.getWorld(), 0, 82, 0))<= 30) && player.getLocation().distance(npc.getEntity().getLocation()) <= 4 || CitizensAPI.getNPCRegistry().isNPC(player) && player.getLocation().getY() - npc.getEntity().getLocation().getY() <= 1 && player.getLocation().distance(npc.getEntity().getLocation()) <= 4)player.damage(damage, npc.getEntity());
                                else if(!CitizensAPI.getNPCRegistry().isNPC(player) && player.getLocation().distance(npc.getEntity().getLocation()) <= 4 )player.damage(7, npc.getEntity());
                            }

                        }else{
                            //Player nearby = gearNearby(npc.getEntity(), 10).get(0);

                            if(npc.getEntity().isOnGround()){
                                npc.getNavigator().cancelNavigation();
                                while (true){
                                    Location loc = findLocation.getOutskirtsLocation(npc);
                                    if(loc.distance(npc.getEntity().getLocation())<=75){
                                        npc.getNavigator().setTarget(loc);
                                        break;
                                    }
                                }
                            }

                        }



                    }else{
                        if(!npc.getNavigator().isNavigating()){
                            while (true){
                                Location loc = findLocation.getOutskirtsLocation(npc);
                                if(loc.distance(npc.getEntity().getLocation())<=75){
                                    npc.getNavigator().cancelNavigation();
                                    npc.getNavigator().setTarget(loc);
                                    break;
                                }
                            }
                        }
                    }/*else{

                        if(npc.getNavigator().isNavigating() && npc.getNavigator().getTargetType() != null && npc.getNavigator().getTargetType().equals(TargetType.ENTITY)){
                            npc.getNavigator().cancelNavigation();
                            npc.getNavigator().setTarget(new Location(Bukkit.getWorld("world"), -2, 124, 12));
                        }



                    }
                    */

                }


            }
        }.runTaskTimer(BoxArea.getPlugin(), 5, 5);
    }

}
