package com.j43hyun9.blockingdead.concept.yml;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class StateConfigLoader {
    private final YamlConfiguration config;

    public StateConfigLoader(JavaPlugin plugin) {
        File file = new File(plugin.getDataFolder(), "state.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public int getBleedingProbability() {
        return config.getInt("bledding-probablity", 5); // 기본값 5
    }
}
