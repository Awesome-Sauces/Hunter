package me.alpha.hunter.bot;

import me.alpha.hunter.hunter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class HunterRunnable {

    boolean isCanceled = false;
    public HunterRunnable(){

    }

    public void code(){

    }

    public void cancel(){
        this.isCanceled = true;
    }

    public BukkitTask execute(int delay){
        // Runnable for Jumping
        return new BukkitRunnable() {
            @Override
            public void run(){
                if(isCanceled) this.cancel();
                code();
            }
        }.runTaskTimer(hunter.HUNTER_INSTANCE, delay, delay);
    }
}
