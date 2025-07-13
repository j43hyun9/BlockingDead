package com.j43hyun9.blockingdead.admin;

import com.j43hyun9.blockingdead.effect.Effective;
import com.j43hyun9.blockingdead.item.UsableItem;
import com.j43hyun9.blockingdead.yml.Configurable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StateCommand implements CommandExecutor {
    UsableItem [] usableItem;
    Effective [] effectives;
    Configurable [] configurables;
    ConfigCommand configCommand;
    public StateCommand(UsableItem[] usableItem, Effective [] effectives, Configurable [] configurables) {
        this.usableItem = usableItem;
        this.effectives = effectives;
        this.configurables = configurables;
        configCommand = new ConfigCommand(configurables);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        /* 헷갈린다.
        args[n] : /bksys arg1 arg2 arg3
        length  :    0    1     2   3
        */
        if(!(commandSender instanceof Player)) return false;

        Player player = ((Player) commandSender).getPlayer();
        player.sendMessage("§7 BockingDead");

        if(strings.length == 0) {
            player.sendMessage("/bksys itemList");
            player.sendMessage("/bksys stateList");
            player.sendMessage("/bksys get <index>");
            player.sendMessage("/bksys set <index>");
            configCommand.commandDisplay(player);
            return false;
        }
        String args = strings[0];
        player.sendMessage("args :" + args);
        switch(args) {
            case "itemList":
                player.sendMessage("itemList");
                showItemList(player);
                break;
            case "get":
                player.sendMessage("get");
                if(strings.length != 2) return false;
                getItem(player, strings[1]);
                break;
            case "stateList":
                showStateList(player);
                break;
            case "set":
                if(strings.length == 1) { showStateList(player); return false; } // bksys set
                if(strings.length == 2) { // /bksys set <index>
                    if(!isInteger(strings[1])){
                        player.sendMessage("인덱스는 숫자이어야 합니다.");
                        return false;
                    }
                    Bukkit.broadcastMessage("setState");
                    setState(player, Integer.parseInt(strings[1]));
                }
                if(strings.length == 3) { // bksys set <index> [player]
                    if(!isInteger(strings[1])){
                        player.sendMessage("인덱스는 숫자이어야 합니다.");
                        return false;
                    }
                    Bukkit.broadcastMessage("setState length 3");
                    Player target_player = Bukkit.getPlayer(strings[2]);
                    if(target_player == null) { player.sendMessage(String.valueOf(strings[2]) + "플레이어를 찾을 수 없습니다."); return false; }
                    setState(target_player, Integer.parseInt(strings[1]));
                }
                break;
            case "config":
                if(strings.length == 1) configCommand.commandDisplay(player);
                else if(strings.length == 2) {
                    if(strings[1].equals("reload")) {
                        configCommand.configReload();
                    }
                    else {
                        Bukkit.broadcastMessage("리로드 실패!");
                    }
                }
                break;

        }
        return false;
    }

    private void showStateList(Player player) {
        player.sendMessage("- StateList");
        for(int i=0; i<effectives.length; i++) {
            player.sendMessage(i + ". " + effectives[i].getName());
        }
        player.sendMessage("- - /bksys set <index> [player (입력하지 않으면 본인에게 적용)]");
    }
    private void showItemList(Player player) {
       for(int i=0; i< usableItem.length; i++) {
           player.sendMessage("- ["+i+"] " + usableItem[i].getItemStack().getItemMeta().getDisplayName());
       }
        player.sendMessage("- - /bksys get <index> ");
    }

    private void getItem(Player player, String secondArgs) {
        if(!isInteger(secondArgs)) return;
        int index = Integer.parseInt(secondArgs);
        if(index > usableItem.length - 1) { player.sendMessage("index at least" + (usableItem.length-1));return; }
        player.sendMessage("index : " + index);
        player.getInventory().addItem(usableItem[index].getItemStack());
    }

    private void setState(Player player, int index) {
        effectives[index].applyTo(player);
    }
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
