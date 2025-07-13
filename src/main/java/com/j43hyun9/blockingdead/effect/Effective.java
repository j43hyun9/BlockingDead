package com.j43hyun9.blockingdead.effect;

import org.bukkit.entity.Player;

public interface Effective {
    public void applyTo(Player player);

    public String getName();

    public void execute(Player player);
}
