package com.j43hyun9.blockingdead.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface UsableItem {
    public void useOn(Player player);

    public ItemStack getItemStack();
}
