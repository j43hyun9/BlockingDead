package com.j43hyun9.blockingdead.concept;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ThirstHandler implements StateHandler{

    // * 플레이어는 ThirstHandler에게 요청하고 ThirstHandler는 자신이 관리하는
    private Map<Player>
    // Map에 플레이어를 등록한다. 언제든 플레이어는 상태를 회복할 수 있기 때문에
    // Map의 유저를 제거하고 등록하는 인터페이스를 제공해야한다.
    // 1. BlockingDead 인스턴스를 싱글턴으로 접근하는게 나을까
    // 2. 의존성 주입으로 접근하는게 나을까?
    // 3. 다른 경우에 의존성 주입이 유연하다 들었다. 따라서 의존성 주입으로 구현
    Plugin plugin;
    public ThirstHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void register() {
        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {

            }
        }.runTaskTimer(plugin, 60L, 60L);
        // 여기
    }

    @Override
    public void unregister() {

    }
}
