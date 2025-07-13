package com.j43hyun9.blockingdead.handler;

import com.j43hyun9.blockingdead.effect.Effective;
import org.bukkit.entity.Player;

public interface StateHandler {
    public void register(Player player, Effective effective);
    // 등록이 있다면 등록 해제도 당연히 필요하지 않을까?
    public void unregister(Player player);
}
