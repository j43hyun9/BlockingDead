package com.j43hyun9.blockingdead.effect;


import com.j43hyun9.blockingdead.handler.StateHandler;
import org.bukkit.entity.Player;

public class Bleeding implements Effective {
    StateHandler stateHandler;
    private final String NAME = "Bleeding";
    public Bleeding(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void applyTo(Player player) {

        player.sendMessage("출혈이 발생하였습니다.");
        stateHandler.register(player, this);
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("출혈 상태로 인해 체력이 닳습니다...");
        double amount = 2.5;
        double newHealth = Math.max(0, player.getHealth() - amount);
        player.setHealth(newHealth);
    }
}
