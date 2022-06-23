package me.alpha.hunter.items;

import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class hunterArmor {

    public static ItemStack ChainHelmet;
    public static ItemStack ChainChestplate;
    public static ItemStack ChainLeggings;
    public static ItemStack ChainBoots;
    public static ItemStack DiamondHelmet;
    public static ItemStack DiamondChestplate;
    public static ItemStack DiamondLeggings;
    public static ItemStack DiamondBoots;
    public static ItemStack IronHelmet;
    public static ItemStack IronChestplate;
    public static ItemStack IronBoots;
    public static ItemStack IronSword;
    public static ItemStack DiamondSword;

    public static void init() {
        createChainBoots();
        createChainLeggings();
        createChainChestplate();
        createChainHelmet();
        createIronSword();

        createDiamondSword();
        createIronBoots();
        createDiamondLeggings();
        createIronChestplate();
        createIronHelmet();

        createDiamondChestplate();
        createDiamondHelmet();
        createDiamondLeggings();
        createDiamondBoots();
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


    private static void createDiamondSword() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        DiamondSword = item;
    }

    private static void createIronHelmet() {
        ItemStack item = new ItemStack(Material.IRON_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        IronHelmet = item;
    }

    private static void createDiamondHelmet() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        DiamondHelmet = item;
    }

    private static void createDiamondChestplate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        DiamondChestplate = item;
    }

    private static void createIronChestplate() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        IronChestplate = item;
    }

    private static void createDiamondLeggings() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        DiamondLeggings = item;
    }

    private static void createIronBoots() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        IronBoots = item;
    }

    private static void createDiamondBoots() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        DiamondBoots = item;
    }

}
