package com.j43hyun9.blockingdead.handler;

import com.j43hyun9.blockingdead.BlockingDead;
import com.j43hyun9.blockingdead.effect.Effective;
import com.j43hyun9.blockingdead.state.CurrentBehavior;
import com.j43hyun9.blockingdead.yml.Configurable;
import com.j43hyun9.blockingdead.yml.YmlConst;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirstHandler implements StateHandler, Listener, Configurable {
    private Map<Player, Location> playerslocation = new HashMap<>();
    private final int BASE = 2;
    private final int DIV = 10;
    private boolean useThirst = true;
    private List<String> useWorlds = new ArrayList<>();
    public ThirstHandler(Effective effective) {

         new BukkitRunnable(){
            @Override
            public void run() {

                if(useThirst) {
                    for(Map.Entry<Player, Location> entry : playerslocation.entrySet()) {
                        Player player = entry.getKey();
                        String playWorld = player.getWorld().getName();
                        if (useWorlds.contains(playWorld)) {
                            Location location = entry.getValue();
                            double distance = location.distance(player.getLocation());
                            playerslocation.put(player, player.getLocation()); // location 업데이트
                            Bukkit.broadcastMessage("distance : " + distance);
                            Bukkit.broadcastMessage("playerLevel : " + player.getLevel());
                            int currentThirst = player.getLevel();
                            switch (currentThirst) {
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                    player.sendMessage("§8입 안이 바짝바짝 마르는게 느껴진다.");
                                    break;
                                case 35:
                                case 34:
                                case 33:
                                case 32:
                                case 31:
                                case 30:
                                    player.sendMessage("§8한 모금의 물이 이렇게 간절할 수 있다니");
                                    break;
                                case 15:
                                case 14:
                                case 13:
                                case 12:
                                case 11:
                                case 10:
                                    player.sendMessage("§8머릿속엔 오로지 '물, 물, 물'이라는 생각밖에 남지 않는다.");
                                    break;
                                case 3:
                                case 2:
                                    player.sendMessage("§f갈증에 의해 죽어가고 있습니다.");
                                    break;
                                case 1:
                                case 0:
                                    if (effective != null)
                                        effective.execute(player);
                                    break;
                            }

                            Bukkit.broadcastMessage("수분 감소 : " + Math.pow(BASE, Math.floorDiv((int) distance, DIV)));
                            int decraseAmount = (int) Math.pow(BASE, Math.floorDiv((int) distance, DIV));
                            Bukkit.broadcastMessage("decrease : " + decraseAmount);
                            player.setLevel(currentThirst - decraseAmount);
                            // 핸들러는 적용될 월드와 config 설정에 대해 알아서는 안된다.
                            // 근데 알아야되는거 같음. onEnable에서 객체를 갈아끼워주는 방식도 있긴한데, 구조가 복잡해짐
                        } else { Bukkit.broadcastMessage("적용 월드가 아닙니다. "); }
                    }
                } else { Bukkit.broadcastMessage("useThirst is False"); }
            }
        }.runTaskTimer(BlockingDead.getInstance(), 80L, 80L);
    }
    @Override
    public void register(Player player, Effective effective) {
        playerslocation.put(player, player.getLocation());

    }
    // SurvivorPlayer 객체로부

    @Override
    public void unregister(Player player) {
        playerslocation.remove(player);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.playerslocation.put(event.getPlayer(), event.getPlayer().getLocation());
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.playerslocation.remove(event.getPlayer());
    }

    @Override
    public void inItConfig(FileConfiguration config) {
        // 각 특성에 대한 접근은 하-드 코딩
        this.useThirst = config.getBoolean(YmlConst.FEATURES+"thirst");
        this.useWorlds = config.getStringList("worlds");
    }
}
