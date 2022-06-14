package me.alpha.hunter.api;

import me.alpha.hunter.main.math.AABB;
import me.alpha.hunter.main.math.Ray;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class HunterInFace {
    public static Player getTargetPlayer(Player player, int max) {
        List<Player> possible = player.getNearbyEntities(max, max, max).stream().filter(entity -> entity instanceof Player).map(entity -> (Player) entity)/*.filter(player::hasLineOfSight)*/.collect(Collectors.toList());
        Ray ray = Ray.from(player);
        double d = -1;
        Player closest = null;
        for (Player player1 : possible) {
            double dis = AABB.from(player1).collidesD(ray, 0, max);
            if (dis != -1) {
                if (dis < d || d == -1) {
                    d = dis;
                    closest = player1;
                }
            }
        }
        return closest;
    }
}
