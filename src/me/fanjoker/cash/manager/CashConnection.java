package me.fanjoker.cash.manager;

import me.fanjoker.cash.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CashConnection {

    private static Connection con = null;
    private String prefix = "Cash";

    private void createTables() {
        table1();
    }
    private void table1() {
        PreparedStatement stm = null;
        try {
            PreparedStatement localPreparedStatement = con.prepareStatement("CREATE TABLE IF NOT EXISTS `Cash_PLAYER`(" +
                    "`Id` INTEGER PRIMARY KEY, `name` TEXT, `value` TEXT, `toggle` TEXT)");
            localPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
        }
    }

    public void openConnectionMySQL() {

        FileConfiguration config = Main.configManager.getConfig("config").getYaml();
        boolean usemysql = config.getBoolean("Mysql.enable");
        if (usemysql) {
            String hostname = config.getString("Mysql.hostname");
            String database_name = config.getString("Mysql.database");
            String username = config.getString("Mysql.username");
            String password = config.getString("Mysql.password");
            Integer port = config.getInt("Mysql.port");

            String URL = "jdbc:mysql://" + hostname + ":" + port + "/" + database_name;

            try {
                con = DriverManager.getConnection(URL, username, password);
                createTables();
                Bukkit.getConsoleSender().sendMessage("§c[" + prefix + "] §fConexão com o §fMySQL §afoi aberta!");
            } catch (SQLException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("§aConexão com o §fMySQL §afalhou, alterando modo para §fSQLITE!");
            }

        } else {
            openConnectionSQLite();
        }
    }
    private void openConnectionSQLite() {
        File file = new File(Main.getInstance().getDataFolder(), "database.db");

        String URL = "jdbc:sqlite:" + file;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
            createTables();
            Bukkit.getConsoleSender().sendMessage("§c[" + prefix +"] §fConexao com o SQLite foi aberta!");
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§aConexão com o §fSQLite §afalhou, desabilitando o plugin");
            Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
        }
    }

    public void close() {
        if(con != null) {
            try {
                con.close();
                con = null;
                Bukkit.getConsoleSender().sendMessage("§c[" + prefix + "] §fConexao com o banco de dados foi fechada!");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cNão foi possível fechar a conexão.");
            }
        }
    }
    public Connection getConnection()
    {
        return con;
    }

}
