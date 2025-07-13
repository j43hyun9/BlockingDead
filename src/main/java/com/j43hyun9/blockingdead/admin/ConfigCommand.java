package com.j43hyun9.blockingdead.admin;

import com.j43hyun9.blockingdead.yml.Configurable;
import com.j43hyun9.blockingdead.yml.YmlConst;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ConfigCommand {

    Configurable [] configurables;
    public ConfigCommand(Configurable[] configrables) {
        this.configurables = configrables;
    }
    public void commandDisplay(Player player) {
        player.sendMessage("/bksys config reload");
    }

    public void configReload() {
        for(Configurable config : configurables) {
            config.inItConfig(YamlConfiguration.loadConfiguration(YmlConst.CONFIG_YML));
        }
        Bukkit.broadcastMessage("리로드!");
    }
}
