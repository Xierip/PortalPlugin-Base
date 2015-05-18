package pl.darkelite.portal;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import pl.darkelite.portal.database.config.Config;
import pl.darkelite.portal.database.objects.Portal;
import pl.darkelite.portal.database.portals.PortalsYAML;

/**
 *
 * @author Xierip
 */
public class PortalPlugin extends JavaPlugin {

    @Getter
    private static PortalPlugin plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new Config(getConfig());
        new PortalsYAML(this);

        //INFO CODE
        //
        // Create: PortalsYAML.setPortal("nameOfPortal", "nameOfTargetPortal", true, new Location(Bukkit.getWorlds().get(0), 0, 0, 0));
        // Remove: PortalsYAML.removePortal("nameOfPortal");
        // Get:    PortalsYAML.getPortals().get("nameOfPortal")
        // Update: PortalsYAML.updatePortal(new Portal("nameOfPortal", "nameOfTargetPortal", true, new Location(Bukkit.getWorlds().get(0), 0, 0, 0))); Update by name of portal
        //
        // Libraries: Bukkit & Lombok
        //
        //INFO CODE
    }

    @Override
    public void onDisable() {

    }

}
