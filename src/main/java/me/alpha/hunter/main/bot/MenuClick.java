package me.alpha.hunter.main.bot;

import me.alpha.hunter.api.BotConfig;
import me.alpha.hunter.api.BotConfigHelper;
import me.alpha.hunter.api.HunterAPI;
import me.alpha.hunter.api.hunterTrait;
import me.alpha.hunter.items.hunterArmor;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.alpha.hunter.api.BotConfigHelper.botConfigExists;

public class MenuClick implements Listener {


    @EventHandler
    public void handleCancel(InventoryClickEvent event){
        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Bot Menu")) event.setCancelled(true);
        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Armor Quality")) event.setCancelled(true);
        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Attack Pattern")) event.setCancelled(true);
        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Speed Pattern")) event.setCancelled(true);
    }


    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Bot Menu")) return;

        Player player = (Player) event.getWhoClicked();

        BotConfig botConfig = botConfigExists(player);

        if(event.getCurrentItem().getTypeId() == 351){

            botConfig.setMaxBots(util.getMaxBotCount(player));

            BotConfigHelper.addSpawnedBot(String.valueOf(player.getUniqueId()));

            if(!player.isOp() && BotConfigHelper.getSpawnedBot(String.valueOf(player.getUniqueId())) > botConfig.getMaxBots()){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&cYou have reached the max amount of bots that you can spawn!" +
                                "&7To spawn more bots please purchase a rank upgrade at: &bhttps://betterpit.tebex.io"));

                return;
            }

            player.openInventory(bot.MainInventory(player));

            HunterAPI.createTargetHunter(ChatColor.translateAlternateColorCodes('&',
                    ChatColor.stripColor(player.getDisplayName())), player, botConfig.getSpeed(), botConfig.getJumpTick(), botConfig.getTime(), botConfig.getDamage(), botConfig.getSword(), botConfig.getHelmet(), botConfig.getChestplate(), botConfig.getLeggings(), botConfig.getBoots());
        }else if(event.getCurrentItem().equals(bot.attackPattern)){
            player.openInventory(bot.ConfigMenu(player, ChatColor.GRAY + "Attack Pattern"));
        }else if(event.getCurrentItem().equals(bot.armor)){
            player.openInventory(bot.ConfigMenu(player, ChatColor.GRAY + "Armor Quality"));
        }else if(event.getCurrentItem().equals(bot.speed)){
            player.openInventory(bot.ConfigMenu(player, ChatColor.GRAY + "Speed Pattern"));
        }

    }

    @EventHandler
    public void onConfigClick(InventoryClickEvent event){
        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Armor Quality")) {
            Player player = (Player) event.getWhoClicked();

            BotConfig botConfig = botConfigExists(player);

            if(event.getCurrentItem().equals(bot.low)){
                botConfig.setSword(hunterArmor.IronSword);
                botConfig.setBoots(hunterArmor.ChainBoots);
                botConfig.setLeggings(hunterArmor.ChainLeggings);
                botConfig.setChestplate(hunterArmor.ChainChestplate);
                botConfig.setHelmet(null);
                player.openInventory(bot.MainInventory(player));
                return;
            }else if(event.getCurrentItem().equals(bot.medium)){
                botConfig.setSword(hunterArmor.DiamondSword);
                botConfig.setBoots(hunterArmor.IronBoots);
                botConfig.setLeggings(hunterArmor.ChainLeggings);
                botConfig.setChestplate(hunterArmor.IronChestplate);
                botConfig.setHelmet(hunterArmor.IronHelmet);
                player.openInventory(bot.MainInventory(player));
                return;
            }else if(event.getCurrentItem().equals(bot.hard)){
                botConfig.setSword(hunterArmor.DiamondSword);
                botConfig.setBoots(hunterArmor.DiamondBoots);
                botConfig.setLeggings(hunterArmor.ChainLeggings);
                botConfig.setChestplate(hunterArmor.DiamondChestplate);
                botConfig.setHelmet(hunterArmor.IronHelmet);
                player.openInventory(bot.MainInventory(player));
                return;
            }

        }

        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Attack Pattern")) {
            Player player = (Player) event.getWhoClicked();
            BotConfig botConfig = botConfigExists(player);
            if(event.getCurrentItem().equals(bot.low)) {
                botConfig.setDamage(7);
                player.openInventory(bot.MainInventory(player));
                return;
            } else if(event.getCurrentItem().equals(bot.medium)) {
                botConfig.setDamage(11);
                player.openInventory(bot.MainInventory(player));
                return;
            } else if(event.getCurrentItem().equals(bot.hard)) {
                botConfig.setDamage(15);
                player.openInventory(bot.MainInventory(player));
                return;
            }
        }

        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Speed Pattern")) {
            Player player = (Player) event.getWhoClicked();
            BotConfig botConfig = botConfigExists(player);
            if(event.getCurrentItem().equals(bot.low)) {
                botConfig.setSpeed(2);
                botConfig.setJumpTick(5);
                player.openInventory(bot.MainInventory(player));
            }else if(event.getCurrentItem().equals(bot.medium)) {
                botConfig.setSpeed(3);
                botConfig.setJumpTick(4);
                player.openInventory(bot.MainInventory(player));
            } else if(event.getCurrentItem().equals(bot.hard)) {
                botConfig.setSpeed(7);
                botConfig.setJumpTick(3);
                player.openInventory(bot.MainInventory(player));
            }
        }


    }
}
