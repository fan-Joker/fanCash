package me.fanjoker.cash;

import me.fanjoker.cash.listener.PlayerJoin;
import me.fanjoker.cash.manager.CashConnection;
import me.fanjoker.cash.config.ConfigManager;
import me.fanjoker.cash.manager.CashManager;
import org.bukkit.Bukkit;
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
		manager = new CashManager();
		connection = new CashConnection();
		configManager.loadConfig("config");
		connection.openConnectionMySQL();
		register();
	}

	@Override
	public void onDisable() {
		manager.savePlayers();
		connection.close();
	}

	private void register() {
		getCommand("cash").setExecutor(new Cash("cash"));
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getScheduler().runTaskTimer(instance, manager::reloadAll, 0, 5*60*20);
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
