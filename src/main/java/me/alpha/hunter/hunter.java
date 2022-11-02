package me.alpha.hunter;

import me.alpha.hunter.api.HunterTarget;
import me.alpha.hunter.api.HunterTargetEvent;
import me.alpha.hunter.api.hunterTrait;
import me.alpha.hunter.commands.command;
import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.data.HunterBots;
import me.alpha.hunter.events.events;
import me.alpha.hunter.items.hunterArmor;
import me.alpha.hunter.main.bot.MenuClick;
import me.alpha.hunter.main.bot.StartUp;
import me.alpha.hunter.main.hunterUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.*;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static me.alpha.hunter.main.hunterUtils.*;

public class hunter extends JavaPlugin {


    public static hunter HUNTER_INSTANCE;


    @Override
    public void onEnable() {

        HUNTER_INSTANCE = this;

        hunterArmor.init();

        new BoxArea(this);

        command commands = new command();

        getServer().getPluginManager().registerEvents(new events(), this);
        getServer().getPluginManager().registerEvents(new MenuClick(), this);

        getCommand("hunter").setExecutor(commands);
        getCommand("bot").setExecutor(commands);



        hunter plugin = this;    //Your plugin instance



        long timeInTicks = 0L;

/*
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        //The code inside will be executed in {timeInTicks} ticks.
                        //After that, it'll be re-executed every {timeInTicks} ticks;
                        //Task can also cancel itself from running, if you want to.

                        if (getServer().getPluginManager().getPlugin("Citizens") == null || !getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
                            getLogger().log(Level.SEVERE, "Citizens 2.0 not found or not enabled");
                            getServer().getPluginManager().disablePlugin(getPlugin());
                        }else{
                            if(CitizensAPI.getTraitFactory().getRegisteredTraits().contains(net.citizensnpcs.api.trait.TraitInfo.create(hunterTrait.class).withName("hunterTrait"))) {
                                this.cancel();
                                return;
                            }else net.citizensnpcs.api.CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(hunterTrait.class).withName("hunterTrait"));
                            this.cancel();
                        }


                    }
                }.runTaskTimer(plugin, timeInTicks, timeInTicks);
            }
        }, 80L);

 */





        StartUp.start();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Hunter] is up and running!");

    }

    private Plugin getPlugin() {
        return this;
    }

    @Override
    public void onDisable() {
        HunterBots.removeHunterBots();
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Hunter] successfully disabled!");

    }

}