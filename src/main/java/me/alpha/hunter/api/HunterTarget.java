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

    public HunterTarget(NPC npc){
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


                    if(!gearNearby(npc.getEntity(), 5).isEmpty()){

                        for (Player players : gearNearby(npc.getEntity(), 5)){
                            if(!CitizensAPI.getNPCRegistry().isNPC(players)){
                                PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity) npc.getEntity()).getHandle(), 0);
                                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(animationPacket);
                            }
                        }

                        Player player = hunterUtils.getNearbyPlayers(npc.getEntity(), 4);

                        if(player != null){

                            npc.getNavigator().setTarget(player, false);


                            if(CitizensAPI.getNPCRegistry().isNPC(player) && player.isOnGround() || CitizensAPI.getNPCRegistry().isNPC(player) && player.getLocation().getY() - npc.getEntity().getLocation().getY() <= 1)player.damage(5, npc.getEntity());
                            else if(!CitizensAPI.getNPCRegistry().isNPC(player))player.damage(5, npc.getEntity());
                        }else{
                            npc.getNavigator().setTarget(gearNearby(npc.getEntity(), 5).get(0), false);
                        }



                    }else{
                        Location location = findLocation.getLocation();

                        if(npc.getNavigator().isNavigating() && npc.getNavigator().getTargetType().equals(TargetType.ENTITY) && npc.getEntity().getLocation().distance(npc.getNavigator().getEntityTarget().getTarget().getLocation()) >= 4.5){
                            npc.getNavigator().cancelNavigation();
                            npc.getNavigator().setTarget(location);
                        }



                    }

                }else{
                    this.cancel();
                }


            }
        }.runTaskTimer(BoxArea.getPlugin(), 5, 5);
    }

}