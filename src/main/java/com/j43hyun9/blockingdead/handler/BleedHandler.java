package com.j43hyun9.blockingdead.handler;

import com.j43hyun9.blockingdead.BlockingDead;
import com.j43hyun9.blockingdead.effect.Effective;
import com.j43hyun9.blockingdead.yml.Configurable;
import com.j43hyun9.blockingdead.yml.YmlConst;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class BleedHandler implements StateHandler, Configurable {
    Map<Player, BukkitTask> handlerMap = new HashMap<>();
    private boolean useBleeding = true;
    @Override
    public void register(Player player, Effective effective) {

        if(handlerMap.containsKey(player)) {
            Bukkit.broadcastMessage("already Exists");
            return;
        }
        BukkitTask bukkitTask = new BukkitRunnable(){
                @Override
                public void run() {
                    if(useBleeding) {
                        effective.execute(player);
                    }
                }
            }.runTaskTimer(BlockingDead.getInstance(), 80L, 80L); // 80L = 출혈 딜레이

        handlerMap.put(player, bukkitTask);
        Bukkit.broadcastMessage("register : " + player.getName());
    }

    @Override
    public void unregister(Player player) {
        Bukkit.broadcastMessage("unregister : " + player.getName());
        BukkitTask bukkitTask =  handlerMap.get(player);
        if(bukkitTask != null) {
            Bukkit.broadcastMessage("bukkitTask : ");
            bukkitTask.cancel();
            handlerMap.remove(player);
        }


    }

    @Override
    public void inItConfig(FileConfiguration config) {
        this.useBleeding = config.getBoolean(YmlConst.FEATURES+"bleeding");
    }
}
