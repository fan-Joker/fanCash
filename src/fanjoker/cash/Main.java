package fanjoker.cash;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fanjoker.cash.commands.Cash;
import fanjoker.cash.others.ConfigManager;

public class Main extends JavaPlugin{
	
	public static fanCash plugin;
	public static ConfigManager configManager;

	@Override
	public void onEnable() {
		plugin = new fanCash(this);
		plugin.onEnable();
		configManager = new ConfigManager();
		configManager.loadConfig("config");
		if (Bukkit.getPluginManager().isPluginEnabled(this)) {
			getCommand("cash").setExecutor(new Cash("cash"));
			getCommand("pontos").setExecutor(new Cash("pontos"));
		}
	}

	@Override
	public void onDisable() {
		plugin.onDisable();
	}
	public static Main getInstance()
	{
		return (Main)Bukkit.getServer().getPluginManager().getPlugin("fanCash");
	}

}
