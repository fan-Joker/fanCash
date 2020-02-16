package fanjoker.cash;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fanjoker.cash.database.Database;
import fanjoker.cash.database.MySQL;
import fanjoker.cash.database.SQLite;
import fanjoker.cash.others.RefreshCashTop;

public class fanCash implements Listener {

	public fanCash(JavaPlugin plugin) {
		instance = plugin;
	}

	public static final String PLUGIN_NAME = "fanCash";
	public static final String AUTHOR = "zFan";
	public static final String VERSION = "1.3";

	public static String table;

	public static JavaPlugin instance;
	public static Database database;
	public static Economia economia;
	public static RefreshCashTop refreshCashTop;

	public void onEnable() {
		database();
		economia = new Economia();
		refreshCashTop = new RefreshCashTop();
		instance.getServer().getPluginManager().registerEvents(this, instance);

	}

	public void onDisable() {
		if (database != null) {
			if (database.connection()) {
				database.close();
			}
		}

	}

	public void database() {
		try {
			FileConfiguration config = instance.getConfig();
			boolean usemysql = config.getBoolean("Mysql.enable");
			if (usemysql) {
				String hostname = config.getString("Mysql.hostname");
				String database_name = config.getString("Mysql.database");
				String username = config.getString("Mysql.username");
				String password = config.getString("Mysql.password");
				int port = config.getInt("Mysql.port");
				MySQL mysql = new MySQL(instance);
				mysql.setHostname(hostname);
				mysql.setDatabase(database_name);
				mysql.setUsername(username);
				mysql.setPassword(password);
				mysql.setPort(port);
				table = "fanCash";
				database = mysql;

			} else {
				table = PLUGIN_NAME.toLowerCase().replace("-", "");
				database = new SQLite(instance);
			}

			if (database.open()) {
				database.close();
			} else {
				table = PLUGIN_NAME.toLowerCase().replace("-", "");
				database = new SQLite(instance);
			}
			database.open();

			database.execute("create table if not exists " + table + " (name varchar(40), valor text, toggle int);");

			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String numberFormat(BigDecimal valor) 
	{
    	String formated = "";
    	DecimalFormat decimalFormat = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.GERMAN));
    	formated += decimalFormat.format(valor);
    	return formated;
	}
	public static String formats[] = {"-", "-", "K", "M", "B", "T", "Q", "QQ", "S", "SS", "OC", "N", "D", "UN", "DD", "TD", "QD", "QQD"};
	public static String format(Object value){
		String val = new DecimalFormat("#,###").format(value).replace(".", ",");
		Integer ii = val.indexOf(","), i = val.split(",").length;
		if(ii == -1)return val;
		return (val.substring(0, ii+2)+formats[i]).replace(",0", "");
	}
	public static String formats2[] = {"-", "-", " mil", " milhões", " bilhões", " trilhões", " quadrilhões", " quintilhões", " sextilhões", " septilhões", " octilhões", " nonilhões", " decilhões", " undecilhões", " duodecilhões", " tredecilhões", " quatrodecilhões", " quindecilhões"};
	public static String format2(Object value){
		String val = new DecimalFormat("#,###").format(value).replace(".", ",");
		Integer ii = val.indexOf(","), i = val.split(",").length;
		if(ii == -1)return val;
		return (val.substring(0, ii+2)+formats2[i]).replace(",0", "");
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!economia.existsAccount(event.getPlayer().getName())) {
			economia.createAccount(event.getPlayer().getName(),
					new BigDecimal(0));
		}
	}
	public static String getValue(BigDecimal name) {
		if(Main.configManager.getConfig("config").getYaml().getInt("Settings.Formato") == 0) {
			return fanCash.numberFormat(name);
		} else if(Main.configManager.getConfig("config").getYaml().getInt("Settings.Formato") == 1) {
			return fanCash.format(name);
		} else if(Main.configManager.getConfig("config").getYaml().getInt("Settings.Formato") == 2) {
			return fanCash.format2(name);
		}
		return fanCash.numberFormat(name);
	}

}
