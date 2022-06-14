package me.alpha.hunter.items;

import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class hunterArmor {

    public static ItemStack ChainHelmet;
    public static ItemStack ChainChestplate;
    public static ItemStack ChainLeggings;
    public static ItemStack ChainBoots;
    public static ItemStack IronSword;

    public static void init() {
        createChainBoots();
        createChainLeggings();
        createChainChestplate();
        createChainHelmet();
        createIronSword();
    }

    private static void createIronSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        IronSword = item;
    }

    private static void createChainHelmet() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        ChainHelmet = item;
    }

    private static void createChainChestplate() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        ChainChestplate = item;
    }

    private static void createChainLeggings() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        ChainLeggings = item;
    }

    private static void createChainBoots() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        ChainBoots = item;
    }


}
