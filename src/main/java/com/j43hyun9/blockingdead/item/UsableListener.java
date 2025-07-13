package com.j43hyun9.blockingdead.item;

import com.j43hyun9.blockingdead.item.UsableItem;
import com.j43hyun9.blockingdead.util.PDCRegister;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class UsableListener implements Listener {
    Map<String, UsableItem> usableItemMap = new HashMap<>();

    public UsableListener(UsableItem [] usableItem) {
        for(UsableItem item : usableItem) {
                usableItemMap.put(item.getItemStack().getItemMeta()
                    .getPersistentDataContainer()
                    .get(PDCRegister.USABLE_ITEM_NAME, PersistentDataType.STRING), item);
            Bukkit.getConsoleSender().sendMessage("-----------------PDC key : " + (item.getItemStack().getItemMeta()
                            .getPersistentDataContainer()
                            .get(PDCRegister.USABLE_ITEM_NAME, PersistentDataType.STRING)));
        }
    }

    @EventHandler
    public void onClick (PlayerInteractEvent event) {
        // 1. 우클릭 확인
        if( !event.getAction().isRightClick()) return;

        Player player = event.getPlayer();

        // 2. 사용 아이템 체크
        ItemStack item = event.getItem();
        if(item == null) return;

        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return;

        String key = itemMeta.getPersistentDataContainer().get(PDCRegister.USABLE_ITEM_NAME, PersistentDataType.STRING);

        UsableItem usableItem = usableItemMap.get(key);
        // 3. 사용
        if(usableItem != null) usableItem.useOn(player);

    }

}
