package com.j43hyun9.blockingdead.yml;

import com.j43hyun9.blockingdead.BlockingDead;

import java.io.File;

public class YmlConst {
    public static final String ITEM_LIST = "itemList.";
    public static final String FEATURES = "features.";

    public static final File CONFIG_YML = new File(BlockingDead.getInstance().getDataFolder(), "config.yml");
}