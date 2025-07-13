package com.j43hyun9.blockingdead.effect;


import com.j43hyun9.blockingdead.handler.StateHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class Thirst implements Effective, Listener {
    private final String NAME = "Thirst";

    public Thirst() {

    }
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void applyTo(Player player) {
        player.setLevel(0);
        execute(player);
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§f갈증에 의해 죽어가고 있습니다.");
        double amount = 2.5;
        double newHealth = Math.max(0, player.getHealth() - amount);
        player.setHealth(newHealth);
    }


}
