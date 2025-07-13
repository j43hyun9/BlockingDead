package com.j43hyun9.blockingdead.entity;

import com.j43hyun9.blockingdead.effect.Effective;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Zombie implements Enemy, Listener {
    Effective effective;
    Zombie zombie;
    public Zombie(Effective effective) {
        this.effective = effective;
    }
    @Override
    public void appear() {

    }

    @EventHandler
    public void onPlayerHit(EntityDamageEvent e) {
        // 1. 타격대상이 플레이어이가 아니라면 종료
        if (!(e.getEntity() instanceof Player)) return;
        // 2. 이벤트가 EntityDamageByEntityEvent인지 확인
        if (e instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
            Entity damager = event.getDamager();
            // 3. 공격자가 좀비인지 확인
            if (damager instanceof org.bukkit.entity.Zombie) {
                Player player = (Player) e.getEntity();
                // 여기서 원하는 로직 실행 (예: 상태 전송)
                player.sendMessage("좀비에게 공격당했습니다!");
                effective.applyTo(player);
            }
        }


    }
}
