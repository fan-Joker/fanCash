package me.fanjoker.cash;

import me.fanjoker.cash.manager.CashConnection;
import me.fanjoker.cash.config.ConfigManager;
import me.fanjoker.cash.manager.CashManager;
import me.fanjoker.cash.others.Runnable;
import org.bukkit.plugin.java.JavaPlugin;

import me.fanjoker.cash.commands.Cash;

public class Main extends JavaPlugin{
	
	private static Main instance;
	private static CashConnection connection;
	public static CashManager manager;

	public static ConfigManager configManager;

	@Override
	public void onEnable() {
		instance = this;
		configManager = new ConfigManager();
		connection = new CashConnection();
		configManager.loadConfig("config");
		getCommand("cash").setExecutor(new Cash("cash"));
		new Runnable().runTaskTimer(instance, 0, 20 * 60 * 5);
	}

	@Override
	public void onDisable() {
		manager.savePlayers();
		connection.close();
	}

	public static CashManager getManager() {
		return manager;
	}

	public static CashConnection getConnection() {
		return connection;
	}

	public static Main getInstance() {
		return instance;
	}
}
