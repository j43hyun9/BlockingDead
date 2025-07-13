package com.j43hyun9.blockingdead.item;

import com.j43hyun9.blockingdead.BlockingDead;
import com.j43hyun9.blockingdead.util.PDCRegister;
import com.j43hyun9.blockingdead.yml.YmlConst;
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

public class WaterBottle implements UsableItem {
    private ItemStack myItem;
    private String myValue;
    private File file = new File(BlockingDead.getInstance().getDataFolder(), "data.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    private final String YML_KEY_NAME = "WaterBottle";
    public WaterBottle() {
        myItem = new ItemStack(Material.POTION);
        ItemMeta itemMeta = getItemStack().getItemMeta();
        itemMeta.setDisplayName("§7[ 물병 ]");

        String myValue = config.getString(YmlConst.ITEM_LIST + YML_KEY_NAME);
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
        lore.add("§7- 갈증을 해소하고 스테미너를 회복합니다.");
        lore.add("§a- 오른쪽 클릭 후, 사용하세요.");
        lore.add("§8- 생존에 필수적인 기본 아이템");
        itemMeta.setLore(lore);

        myItem.setItemMeta(itemMeta);
    }
    @Override
    public void useOn(Player player) {
        setThirst(player);
        player.sendMessage("갈증을 해소하였습니다.");
    }

    @Override
    public ItemStack getItemStack() {
        return myItem.clone();
    }

    private void setThirst(Player player) {
        player.setLevel(100); // 100은 갈증 수치
    }
}
