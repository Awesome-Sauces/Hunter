package me.alpha.hunter.api;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

 
public class HunterTargetEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Player hunter;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public HunterTargetEvent(Player player, Player hunter) {
        this.player = player;
        this.hunter = hunter;
        this.isCancelled = false;
    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public Player getHunted() {
        return this.player;
    }

    public Player getHunter() {
        return this.hunter;
    }


 
}
