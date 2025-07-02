package com.j43hyun9.blockingdead;

import com.j43hyun9.blockingdead.concept.Bleeding;
import com.j43hyun9.blockingdead.concept.People;
import com.j43hyun9.blockingdead.concept.yml.StateConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockingDead extends JavaPlugin {

    // yml
    private StateConfigLoader stateConfigLoader;
    @Override
    public void onEnable() {
        stateConfigLoader = new StateConfigLoader(this);

        Bleeding bleeding = new Bleeding(stateConfigLoader);
        getServer().getPluginManager().registerEvents(new People(bleeding, this), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
