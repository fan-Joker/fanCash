package me.fanjoker.cash;

import me.fanjoker.cash.commands.shop.Shop;
import me.fanjoker.cash.listener.PlayerJoin;
import me.fanjoker.cash.listener.PlayerQuit;
import me.fanjoker.cash.manager.CashConnection;
import me.fanjoker.cash.config.ConfigManager;
import me.fanjoker.cash.manager.CashManager;
import me.fanjoker.cash.others.PAPIHook;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.fanjoker.cash.commands.cash.Cash;

public class Main extends JavaPlugin{
	
	private static Main instance;
	private static CashConnection connection;
	private static CashManager manager;

	public static ConfigManager configManager;

	@Override
	public void onEnable() {
		registerManager();

		configManager.loadConfig("config");
		connection.openConnectionMySQL();
		manager.loadPlayers();
		manager.reloadTop();

		register();

		new PAPIHook().register();
	}

	@Override
	public void onDisable() {
		manager.savePlayers();
		connection.close();
	}

	private void registerManager() {
		instance = this;
		configManager = new ConfigManager();
		manager = new CashManager();
		connection = new CashConnection();
	}

	private void register() {
		getCommand("shop").setExecutor(new Shop());
		getCommand("cash").setExecutor(new Cash("cash"));
		Bukkit.getPluginManager().registerEvents(new Shop(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getScheduler().runTaskTimer(instance, manager::reloadAll, 5*60*20, 5*60*20);
		
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
