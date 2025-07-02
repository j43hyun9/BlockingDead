package com.j43hyun9.blockingdead.concept;

import com.j43hyun9.blockingdead.concept.yml.StateConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class People implements Listener {
    // Zombie -> People startBleeding(), 오직 Enemy 들만 가능하다.
    // 만약 Enemy들이 아닌 객체들이 해당 인터페이스에 접근한다면
    // 접근을 막을 방법이 존재하는가?
    // 1.
    public People() {

    }
    // Enemy는 Player를 알고있다.
    // 어떤 적의 공격인지에 따라 입을 수 있는 상태공격이 여러가지인가?
    // 가능성을 열어두는 편이 좋지 않겠는가?
    // N개의 상태에 대하여 N개의 핸들러를 둘것인가?
    // People은 N개의 핸들러 모두를 알고있어야 하는가?
    // 타격을 입히는 Enemy는 플레이어와 핸들러 둘을 알고있는편이 낫지않나?
    public void startState(Player player) {}

    public void useItem(){}
}
