package pl.darkelite.portal.database.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author Xierip
 */
public class Config {

    @Getter
    private static FileConfiguration yaml;
    @Getter
    private static String example;

    public Config(FileConfiguration yaml) {
        this.yaml = yaml;
        example = yaml.getString("PortalPlugin." + example);
    }
}
