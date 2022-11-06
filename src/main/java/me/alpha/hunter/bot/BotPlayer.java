package me.alpha.hunter.bot;

import me.alpha.hunter.api.HunterAPI;
import me.alpha.hunter.api.hunterTrait;
import me.alpha.hunter.data.BoxArea;
import me.alpha.hunter.data.HunterBots;
import me.alpha.hunter.data.SpawnedBots;
import me.alpha.hunter.hunter;
import me.alpha.hunter.items.hunterArmor;
import me.alpha.hunter.main.hunterUtils;
import me.alpha.hunter.main.math.findLocation;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.EntityTarget;
import net.citizensnpcs.api.ai.TargetType;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

import static me.alpha.hunter.main.hunterUtils.gearNearby;

public class BotPlayer {

    private NPC bot;
    private Player OWNER;
    private BotAction action = BotAction.FROZEN;
    private Location pauseLocation;
    private String name;
    private int speed;
    private int jumpTime;
    private int time;
    private int damage;

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack sword;

    public BotPlayer() {
        this.name = HunterAPI.getRandomName();
        this.speed = 2;
        this.jumpTime = 7;
        this.time = 60;
        this.damage = 7;

        this.helmet = null;
        this.chestplate = hunterArmor.ChainChestplate;
        this.leggings = hunterArmor.ChainLeggings;
        this.boots = hunterArmor.ChainBoots;
        this.sword = hunterArmor.IronSword;
    }

    public BotPlayer(String name, int speed, int jumpTime, int time, int damage) {
        this.name = name;
        this.speed = speed;
        this.jumpTime = jumpTime;
        this.time = time;
        this.damage = damage;

        this.helmet = null;
        this.chestplate = hunterArmor.ChainChestplate;
        this.leggings = hunterArmor.ChainLeggings;
        this.boots = hunterArmor.ChainBoots;
        this.sword = hunterArmor.IronSword;
    }

    public BotPlayer(Player owner, String name, int speed, int time, int damage, ItemStack helmet,
                     ItemStack chestplate, ItemStack leggings, ItemStack boots,
                        ItemStack sword) {
        this.OWNER = owner;
        this.name = name;
        this.speed = speed;
        this.jumpTime = 7;
        this.time = time;
        this.damage = damage;

        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.sword = sword;
    }

    public NPC getBot(){
        if(this.bot != null) return bot;

        // Create NPC Object
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, this.name);

        // Adding the armor and Gear
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, this.boots);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, this.leggings);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, this.chestplate);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, sword);
        if(helmet != null) npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, this.helmet);

        // Setting NPC entity Type
        npc.setBukkitEntityType(EntityType.PLAYER);
        npc.setProtected(false);

        // Setting Speed and other Modifiers
        npc.getNavigator().getDefaultParameters()
                .attackDelayTicks(1)
                .pathDistanceMargin(500)
                .attackRange(30)
                .speedModifier(Math.max(2, speed));

        // Adding Hunter trait
        npc.getOrAddTrait(hunterTrait.class);

        // Setting the private variable
        this.bot = npc;

        return npc;
    }

    public void spawnBot(Location location){
        HunterBots.addHunterBot(this.bot);

        if (!this.bot.isSpawned()){this.bot.spawn(location);}

        this.bot.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);

    }

    public void run(){
        // De-spawn Bot
        if (time > 0) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(BoxArea.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if(bot.isSpawned()){
                        if(OWNER!=null) SpawnedBots.removeRegisteredBot(OWNER.getUniqueId());
                        bot.despawn();
                        bot.destroy();
                        CitizensAPI.getNPCRegistry().deregister(bot);
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&c&lA bot has been removed from your game for hacking or abuse &bThanks for reporting it!"));
                    }
                }
            }, time * 20L);
        }

        // Runnable for Jumping
        new HunterRunnable() {
            @Override
            public void code(){
                if(!bot.isSpawned()){
                    this.cancel();
                }

                if(bot.getEntity() != null && bot.getEntity().isOnGround())((Player) bot.getEntity()).setVelocity(new Vector(0, .36/*Previously 38*/, 0));
            }
        }.execute(this.jumpTime);

        // Every Tick
        new HunterRunnable() {
            @Override
            public void code(){
                if(!bot.isSpawned()){
                    this.cancel();
                }

                if(bot.getNavigator().isNavigating()){
                    bot.faceLocation(bot.getNavigator().getTargetAsLocation());
                }
            }
        }.execute(1);

        // Attacking Action Runnable
        new HunterRunnable() {
            @Override
            public void code(){
                if(!bot.isSpawned()){
                    this.cancel();
                }

                if(getAction().equals(BotAction.PATHFINDING)) return;
                if(getAction().equals(BotAction.FROZEN)) return;
                if(bot.getEntity()==null) return;

                if(bot.getEntity().isOnGround())((Player) bot.getEntity()).setVelocity(new Vector(0, .36/*Previously 38*/, 0));

                List<Player> nearby = gearNearby(bot.getEntity(), 15);

                for (Player players : nearby){
                    if(!CitizensAPI.getNPCRegistry().isNPC(players)){
                        PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity) bot.getEntity()).getHandle(), 0);
                        ((CraftPlayer) players).getHandle().playerConnection.sendPacket(animationPacket);
                    }
                }



                if(!bot.getNavigator().isNavigating() && bot.getEntity() != null && bot.getEntity().isOnGround()){
                    Player lastPlayer = null;

                    for (Player players : nearby){
                        if(lastPlayer==null){
                            lastPlayer=players;
                        }else{
                            double distance1 = bot.getEntity().getLocation().distance(lastPlayer.getLocation());
                            double distance2 = bot.getEntity().getLocation().distance(players.getLocation());

                            if(distance2 > distance1){
                                continue;
                            }else if (distance2 < distance1){
                                lastPlayer=players;
                                continue;
                            }else{
                                lastPlayer=players;
                            }

                        }
                    }

                    bot.getNavigator().setTarget(lastPlayer, false);
                }


                if(bot.getNavigator().isNavigating() &&
                    bot.getNavigator().getTargetType().equals(TargetType.ENTITY)){
                    Entity player = bot.getNavigator().getEntityTarget().getTarget();


                    if(bot.getEntity() != null &&player.getType().equals(EntityType.PLAYER) && player.getLocation().distance(bot.getEntity().getLocation()) <= 3.5 &&
                    player.isOnGround() && CitizensAPI.getNPCRegistry().isNPC(player)) {
                        ((Player) player).damage(damage, bot.getEntity());
                    }else if(bot.getEntity() != null &&player.getType().equals(EntityType.PLAYER) && player.getLocation().distance(bot.getEntity().getLocation()) <= 3.5){
                        ((Player) player).damage(damage, bot.getEntity());
                    }

                    if(player.getLocation().distance(bot.getEntity().getLocation()) > 7 && !nearby.isEmpty()) {
                        bot.getNavigator().cancelNavigation();
                    }

                }

                if(!bot.getNavigator().isNavigating()){
                    setAction(BotAction.FROZEN);
                }

            }
        }.execute(5);

        // Pathfinding Action Runnable
        new HunterRunnable() {
            @Override
            public void code(){
                if(!bot.isSpawned()){
                    this.cancel();
                }

                if(getAction().equals(BotAction.ATTACKING)) return;

                if(bot.getEntity() != null && bot.getEntity().isOnGround()) {
                    Player target = hunterUtils.getClosetNearby((Player) bot.getEntity());
                    if(target!=null){
                        bot.getNavigator().cancelNavigation();
                        bot.getNavigator().setTarget(target, false);
                    }
                    setAction(BotAction.ATTACKING);
                }
            }
        }.execute(5);

        // General Action Runnable
        new HunterRunnable() {
            @Override
            public void code(){

                if(!bot.isSpawned()){
                    this.cancel();
                }

                if(getAction().equals(BotAction.PATHFINDING)) return;
                if(getAction().equals(BotAction.ATTACKING)) return;
                if(getAction().equals(BotAction.FROZEN)){

                    Location location;
                    if(bot.getEntity() != null) location = bot.getEntity().getLocation();

                    if(bot.getNavigator().isNavigating()) return;
                    if(bot.getEntity() != null && !bot.getEntity().isOnGround()) return;

                    if(bot.getEntity() != null &&bot.getEntity().isOnGround()) {
                        Player target = hunterUtils.getClosetNearby((Player) bot.getEntity());
                        if(target!=null){
                            bot.getNavigator().cancelNavigation();
                            bot.getNavigator().setTarget(target, false);
                        }
                        setAction(BotAction.ATTACKING);
                    }
                }

            }
        }.execute(5);

    }

    public BotAction getAction() {
        return action;
    }

    public void setAction(BotAction action) {
        this.action = action;
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

    public ItemStack getSword() {
        return sword;
    }

    public void setSword(ItemStack sword) {
        this.sword = sword;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getJumpTime() {
        return jumpTime;
    }

    public void setJumpTime(int jumpTime) {
        this.jumpTime = jumpTime;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}