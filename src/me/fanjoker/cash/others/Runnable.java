package me.fanjoker.cash.others;

import me.fanjoker.cash.Main;
import org.bukkit.Bukkit;

import me.fanjoker.cash.fanCash;
import org.bukkit.scheduler.BukkitRunnable;

public class Runnable extends BukkitRunnable {

	@Override
	public void run() {
		Main.getManager().reloadAll();
	}

}
