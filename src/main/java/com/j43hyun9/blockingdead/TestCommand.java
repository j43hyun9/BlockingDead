package com.j43hyun9.blockingdead;

import com.j43hyun9.blockingdead.concept.People;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {
    People people;
    public TestCommand(People people) {
        this.people = people;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(commandSender instanceof Player)) return false;

        if(strings[0].equals("bledding")) {
            Bukkit.broadcastMessage("bledding");
        }

        return false;
    }
}
