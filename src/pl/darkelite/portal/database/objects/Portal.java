package pl.darkelite.portal.database.objects;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.bukkit.Location;
import pl.darkelite.portal.utils.Utils;

/**
 *
 * @author Xierip
 */
public @Data
class Portal {

    private String name, target;
    private boolean enable;
    private Location location;

    public Portal(String name, String target, boolean enable, Location location) {
        this.name = name;
        this.target = target;
        this.enable = enable;
        this.location = location;
    }

    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap();
        map.put("target", target);
        map.put("enable", enable);
        map.put("location", Utils.serializeLocation(location));
        return map;
    }
}
