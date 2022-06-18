package me.alpha.hunter.main.bot;

import me.alpha.hunter.apis.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class bot {
    public static ItemStack speed = advancedInventory.ItemMaker(Material.GOLD_BOOTS, ChatColor.RED + "Speed Pattern",
            ChatColor.translateAlternateColorCodes('&',
                    "&7Click to open the\n&7configuration menu!"),
            1, true);

    public static ItemStack armor = advancedInventory.ItemMaker(Material.IRON_CHESTPLATE, ChatColor.RED + "Armor Quality",
            ChatColor.translateAlternateColorCodes('&',
                    "&7Click to open the\n&7configuration menu!"),
            1, true);

    public static ItemStack attackPattern = advancedInventory.ItemMaker(Material.DIAMOND_SWORD, ChatColor.RED + "Attack Pattern",
            ChatColor.translateAlternateColorCodes('&',
                    "&7Click to open the\n&7configuration menu!"),
            1, true);

    public static ItemStack confirm = advancedInventory.DyeMaker((short) 10, ChatColor.GREEN + "Confirm",
            ChatColor.translateAlternateColorCodes('&',
            "&7Creates your &c&lSilent Bot&7!"));

    public static ItemStack low = advancedInventory.DyeMaker((short) 8, ChatColor.GRAY + "Low",
            ChatColor.translateAlternateColorCodes('&',
                    "&7Chooses the low settings for this module."));

    public static ItemStack medium = advancedInventory.DyeMaker((short) 10, ChatColor.GREEN + "Medium",
            ChatColor.translateAlternateColorCodes('&',
                    "&7Chooses the medium settings for this module."));

    public static ItemStack hard = advancedInventory.DyeMaker((short) 1, ChatColor.RED + "Hard",
            ChatColor.translateAlternateColorCodes('&',
                    "&7Chooses the hard settings for this module."));

    public static Inventory MainInventory(Player player){

        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Bot Menu");
        ItemStack base_glass = advancedInventory.cGlass();


        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        gui.setItem(10, speed);

        gui.setItem(12, attackPattern);

        gui.setItem(14, armor);

        gui.setItem(16, confirm);

        return gui;
    }

    public static Inventory ConfigMenu(Player player, String title){

        Inventory gui = advancedInventory.inv(player, 27, title);
        ItemStack base_glass = advancedInventory.cGlass();


        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        gui.setItem(11, low);

        gui.setItem(13, medium);

        gui.setItem(15, hard);


        return gui;
    }
}
