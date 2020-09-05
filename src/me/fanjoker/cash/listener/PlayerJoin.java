package me.fanjoker.cash.listener;

import me.fanjoker.cash.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Main.getManager().loadPlayer(e.getPlayer().getName());
    }
}
