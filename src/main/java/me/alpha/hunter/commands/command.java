package me.alpha.hunter.commands;


import me.alpha.hunter.api.HunterAPI;
import me.alpha.hunter.api.HunterTarget;
import me.alpha.hunter.api.hunterTrait;
import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.data.HunterBots;
import me.alpha.hunter.main.bot.bot;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alpha.hunter.main.hunterUtils;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.logging.Level;


public class command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args){
        if(!(sender instanceof Player)) {return true;}

        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("bot")) player.openInventory(bot.MainInventory(player));

        if(!player.isOp()) return true;

        if(cmd.getName().equalsIgnoreCase("hunter")){
            if(args.length < 1){
                player.sendMessage(ChatColor.RED + "Try doing /hunter help!");
                return true;
            }

            if(args[0].equalsIgnoreCase("help")){
                player.sendMessage(ChatColor.GREEN + "Hunter 2.0.0");
                
                player.sendMessage(ChatColor.RED +   "/hunter create");
                player.sendMessage(ChatColor.RED +   "/hunter setMap");
                player.sendMessage(ChatColor.RED +   "/hunter atest");
                player.sendMessage(ChatColor.RED + "/hunter info");

                player.sendMessage(ChatColor.RED +   "More comming soon!");
            }if(args[0].equalsIgnoreCase("info")){
                player.sendMessage(ChatColor.GREEN + "Hunter 2.0.0");

                player.sendMessage(ChatColor.GRAY + "Hunter is a plugin dedicated to making Citizen's NPC a combat npc" +
                        "By using simple calculations and Citizens API this is possible. The main use case for this is Hypixel Pit remake servers in which require " +
                        "bots to be able to have any sort of progression in the game. These bots are aimed to be the most realistic possible and will have many different mechanics added to them " +
                        "over time. For example, being able to bow swap! For now this is just an early release. The bots will automatically be spawned on the cordinates 0 0 0 and based off those " +
                        "coordinates the path's of the bots will be made. Say 0 100 0 is the starting point, it will generate a random x and z coordinate that is 5-15 blocks away from the origin " +
                        "and will iterate through the Y axis until it finds a viable air block to locate to. If coordinates are taken by blocks a new coordinate will be generated.");

                player.sendMessage(ChatColor.RED +   "More comming soon!");
            }else if(args[0].equalsIgnoreCase("create")){
                if(args.length < 2){
                    player.sendMessage(ChatColor.RED + "Try doing /hunter help!");
                    return true;
                }else{
                    hunterUtils.createHunterBot(args[1]);
                }
            }else if(args[0].equalsIgnoreCase("setMap")){
                if(args.length < 2){
                    player.sendMessage(ChatColor.RED + "Example: /hunter setMap <Y-coordinate>");
                    return true;
                }else{
                    BoxArea.setBoxArea("map", Integer.parseInt(args[1]));
                }
            }else if(args[0].equalsIgnoreCase("atest")){

                if(args.length < 5) {
                    player.sendMessage(ChatColor.RED + "Example: /hunter atest <BotName> <Speed> <TickInJump> <TickInDespawn>");
                    return true;
                }

                HunterAPI.createTargetHunter(args[1], player, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), 5);


            }else {
                player.sendMessage(ChatColor.RED + "Try doing /hunter help!");
                return true;
            }
            return true;
        }


        return true;
    }
}

