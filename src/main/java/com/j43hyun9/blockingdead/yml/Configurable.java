package com.j43hyun9.blockingdead.yml;


import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.Map;

public interface Configurable {
    public void inItConfig(FileConfiguration config); // 각 객체들은 해당 메시지를 구현하고, 설정값을 초기화 받는다.
}

