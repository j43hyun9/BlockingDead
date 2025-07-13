package com.j43hyun9.blockingdead.item;

import com.j43hyun9.blockingdead.BlockingDead;
import com.j43hyun9.blockingdead.handler.StateHandler;
import com.j43hyun9.blockingdead.util.PDCRegister;
import com.j43hyun9.blockingdead.yml.YmlConst;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bondage implements UsableItem {
    private ItemStack myItem;
    private StateHandler stateHandler; // 과연 잘하는 짓일까..?
    private File file = new File(BlockingDead.getInstance().getDataFolder(), "data.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    private final String YML_KEY_NAME = "Bondage";
    public Bondage(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
        myItem = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = getItemStack().getItemMeta();
        itemMeta.setDisplayName("§7[ 붕대 ]");
        String myValue = config.getString(YmlConst.ITEM_LIST + YML_KEY_NAME);
        Bukkit.getConsoleSender().sendMessage("myValue : " + String.valueOf(myValue));
        if (myValue == null){
            myValue = UUID.randomUUID().toString();
            // 개꿀잼인 상황은 만약 uuid 가 겹칠 경우 엉뚱한 아이템의 사용으로 인해 다른 아이템의 효과를 얻을거임
            config.set(YmlConst.ITEM_LIST + YML_KEY_NAME, myValue);
            try {
                config.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        itemMeta.getPersistentDataContainer().set(PDCRegister.USABLE_ITEM_NAME,
                PersistentDataType.STRING,
                myValue);

        List<String> lore = new ArrayList<>();
        lore.add("§7- 상처를 감싸 출혈을 막아줍니다.");
        lore.add("§a- 오른쪽 클릭 후, 치료할 부위를 선택하세요.");
        lore.add("§8- 응급처치에 필수적인 기본 아이템");
        itemMeta.setLore(lore);

        myItem.setItemMeta(itemMeta);
    }
    @Override
    public void useOn(Player player) {

        player.sendMessage("상처를 회복하였습니다.");
        stateHandler.unregister(player);
    }

    @Override
    public ItemStack getItemStack() {
        return myItem.clone();
    }
}
