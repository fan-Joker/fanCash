package me.fanjoker.cash.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

	public ConfigManager() {
		this.configs = new HashMap<>();
	}

	private Map<String, Configuration> configs;

	public Map<String, Configuration> getConfigs() {
		return configs;
	}

	public Configuration loadConfig(String config) {
		Configuration configuration = new Configuration(config);
		configuration.setCharset("UTF-8");
		configuration.saveDefault(false);
		configuration.reload();
		this.configs.put(config, configuration);
		return this.configs.get(config);
	}

	public Configuration getConfig(String config) {
		return this.configs.get(config);
	}
}
