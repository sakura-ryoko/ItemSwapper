package dev.tr7zw.itemswapper.config;

import dev.tr7zw.transition.config.ConfigManager;
import lombok.*;

@Getter
public class ConfigHolder {
    @Getter
    private final static ConfigHolder instance = new ConfigHolder();
    private final ConfigManager<Config> general = new ConfigManager<>("itemswapper.json", Config::new,
            ConfigUpgrader::upgradeConfig);
    private final ConfigManager<CacheServerAddresses> serverCache = new ConfigManager<>("itemswapper-server-cache.json",
            CacheServerAddresses::new, null);

    public void save() {
        general.writeConfig();
        serverCache.writeConfig();
    }

    public void reset() {
        general.reset();
        serverCache.reset();
    }
}
