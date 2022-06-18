package me.alpha.hunter.api;

import me.alpha.hunter.items.hunterArmor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BotConfig {

    int speed;
    int spawnedBots;
    int jumpTick;
    int time;
    double damage;

    Player player;
    ItemStack sword;
    ItemStack helmet;
    ItemStack chestplate;
    ItemStack leggings;
    ItemStack boots;

    public BotConfig(Player player){
        this.player = player;

        this.helmet = null;
        this.chestplate = hunterArmor.ChainChestplate;
        this.leggings = hunterArmor.ChainLeggings;
        this.boots = hunterArmor.ChainBoots;
        this.sword = hunterArmor.IronSword;
        this.damage = 7;
        this.speed = 2;
        this.jumpTick = 5;
        this.time = 600;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public int getSpawnedBots() {
        return spawnedBots;
    }

    public void setSpawnedBots(int spawnedBots) {
        this.spawnedBots = spawnedBots;
    }

    public int getJumpTick() {
        return jumpTick;
    }

    public void setJumpTick(int jumpTick) {
        this.jumpTick = jumpTick;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ItemStack getSword() {
        return sword;
    }

    public void setSword(ItemStack sword) {
        this.sword = sword;
    }


}
