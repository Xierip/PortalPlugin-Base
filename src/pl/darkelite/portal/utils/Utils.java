package pl.darkelite.portal.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 *
 * @author Xierip
 */
public final class Utils {

    public static String serializeLocation(Location location) {
        StringBuilder sb = new StringBuilder();
        sb.append(location.getBlockX()).append("|").append(location.getBlockY()).append("|").append(location.getBlockZ()).append("|").append(location.getWorld().getName());
        return sb.toString();
    }

    public static Location deserializeLocation(String serialized) {
        String[] split = serialized.split("\\|");
        if (split.length == 4) {
            return new Location(Bukkit.getWorld(split[3]), Integer.decode(split[0]), Integer.decode(split[1]), Integer.decode(split[2]));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Bad serialized string: '").append(serialized).append("'");
            Bukkit.getConsoleSender().sendMessage(sb.toString());
            return null;
        }
    }
}
