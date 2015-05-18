package pl.darkelite.portal.database.portals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.darkelite.portal.database.objects.Portal;
import pl.darkelite.portal.utils.Utils;

/**
 *
 * @author Xierip
 */
public class PortalsYAML {

    private static JavaPlugin plugin;
    @Getter
    public static YamlConfiguration portalsYAML;
    @Getter
    private static File portalsFile;
    @Getter
    public static Map<String, Portal> portals = new HashMap();

    public PortalsYAML(JavaPlugin plugin) {
        this.plugin = plugin;
        this.portalsFile = new File(plugin.getDataFolder(), "portals.yml");
        if (!portalsFile.exists()) {
            saveDefaultFile();
        }
        this.portalsYAML = YamlConfiguration.loadConfiguration(portalsFile);
        this.loadPortals();
    }

    public static void saveDefaultFile() {
        String filename = "portals.yml";
        try {
            portalsFile.createNewFile();
            PrintWriter writer;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(PortalsYAML.class.getClassLoader().getResourceAsStream(filename)))) {
                writer = new PrintWriter(portalsFile);
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void saveFile() {
        try {
            portalsYAML.save(portalsFile);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private void loadPortals() {
        try {
            portalsYAML.getConfigurationSection("portals").getKeys(false).forEach(s -> portals.put(s, getPortalFromYAML(s)));
        } catch (NullPointerException e) {
            System.err.println("Portal section is empty");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static Portal getPortalFromYAML(String key) {
        StringBuilder targetPatch = new StringBuilder("portals.");
        targetPatch.append(key).append(".target");
        StringBuilder locationPatch = new StringBuilder("portals.");
        locationPatch.append(key).append(".location");
        StringBuilder enablePatch = new StringBuilder("portals.");
        enablePatch.append(key).append(".enable");
        return new Portal(key, portalsYAML.getString(targetPatch.toString()), portalsYAML.getBoolean(enablePatch.toString()), Utils.deserializeLocation(locationPatch.toString()));
    }

    public static Portal setPortal(String name, String target, boolean enable, Location location) {
        final Portal portal = new Portal(name, target, enable, location);
        portals.put(name, portal);
        portalsYAML.createSection("portals." + name, portal.serialize());
        saveFile();
        return portal;
    }

    public static void removePortal(String name) {
        portalsYAML.set("portals." + name, null);
        portals.remove(name);
        saveFile();
    }

    public static Portal updatePortal(Portal portal) {
        portals.put(portal.getName(), portal);
        portalsYAML.createSection("portals." + portal.getName(), portal.serialize());
        saveFile();
        return portal;
    }
}
