package com.j43hyun9.blockingdead;

import com.j43hyun9.blockingdead.admin.StateCommand;
import com.j43hyun9.blockingdead.effect.Bleeding;
import com.j43hyun9.blockingdead.effect.Effective;
import com.j43hyun9.blockingdead.effect.Thirst;
import com.j43hyun9.blockingdead.entity.Zombie;
import com.j43hyun9.blockingdead.handler.BleedHandler;
import com.j43hyun9.blockingdead.handler.ThirstHandler;
import com.j43hyun9.blockingdead.item.Bondage;
import com.j43hyun9.blockingdead.item.UsableItem;
import com.j43hyun9.blockingdead.item.UsableListener;
import com.j43hyun9.blockingdead.item.WaterBottle;
import com.j43hyun9.blockingdead.yml.Configurable;
import com.j43hyun9.blockingdead.yml.YmlConst;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockingDead extends JavaPlugin {
    public static BlockingDead blockingDead ;
    private FileConfiguration config;
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[BlockingDead] onEnable() 진입!");
        saveDefaultConfig(); // config 없으면 만들어준대 덮씌는 안한대.
        blockingDead = this;

        // 각 상태는 핸들러에 의존적이다.
        // N개의 상태에 대하여 N개의 핸들러를 두는게 괜찮은 디자인일까?
        // 각 상태객체가 자신의 상태로써 핸들러를 두는게 괜찮지 않을까? 고민해보자.
        Thirst thirst = new Thirst();
        ThirstHandler thirstHandler = new ThirstHandler(thirst);

        // use_thirst의 유무에 따라 thirstHandler를 동적으로 할당하고 해제한다.
        // player의 상태는 기록되어야한다.
        BleedHandler bleedHandler = new BleedHandler();
        Bleeding bleeding = new Bleeding(bleedHandler);
        Effective [] effectives = { bleeding, thirst };

        UsableItem [] usableItems = { new WaterBottle(), new Bondage(bleedHandler) };
        getLogger().info("Blocking Dead Enabling!");
        getServer().getPluginManager().registerEvents(thirstHandler, this);
        getServer().getPluginManager().registerEvents(new UsableListener(usableItems), this);
        getServer().getPluginManager().registerEvents(new Zombie(bleeding), this);



        // 설정파일 로드
        Configurable [] configurables = {thirstHandler, bleedHandler};
        for(Configurable config : configurables) {
            config.inItConfig(YamlConfiguration.loadConfiguration(YmlConst.CONFIG_YML));
        }

        getCommand("bksys").setExecutor(new StateCommand(usableItems, effectives, configurables));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BlockingDead getInstance() { return blockingDead; }
}
