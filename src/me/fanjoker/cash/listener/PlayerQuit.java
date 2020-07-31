package me.fanjoker.cash.listener;

import me.fanjoker.cash.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Main.getManager().savePlayer(e.getPlayer().getName());
    }
}
