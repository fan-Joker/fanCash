package me.fanjoker.cash.manager;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import me.fanjoker.cash.Main;
import me.fanjoker.cash.others.CashPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CashManager {

    private Map<String, CashPlayer> cache = new ConcurrentHashMap<>();
    private Map<String, Double> topList = new LinkedHashMap<>();
    private Connection getCon() { return Main.getConnection().getConnection(); }

    public Map<String, CashPlayer> getCache() {
        return cache;
    }
    public Map<String, Double> getTopList() {
        return topList;
    }

    public boolean existsPlayer(String player) {
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("SELECT `name` FROM `Cash_PLAYER` WHERE LOWER(`name`) = ?");
            stm.setString(1, player.toLowerCase());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void createAccount(String name) {
        if (existsPlayer(name)) return;
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("INSERT INTO `Cash_PLAYER` (`name`, `value`) VALUES(?,0)");
            stm.setString(1, name);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadPlayer(String name) {
        if (!existsPlayer(name))
            createAccount(name);

        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("SELECT * FROM `Cash_PLAYER` WHERE LOWER(`name`) = ?");
            stm.setString(1, name.toLowerCase());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                CashPlayer cash = new CashPlayer(rs.getString("name"),
                        rs.getDouble("value"),
                        rs.getBoolean("toggle"));
                getCache().put(name.toLowerCase(), cash);
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reloadTop() {
        getTopList().clear();
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("SELECT * FROM `Cash_PLAYER` ORDER BY CAST(value as decimal) DESC LIMIT 10");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                getTopList().put(rs.getString("name"), rs.getDouble("value"));
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reloadAll() {
        Main.getManager().savePlayers();
        Main.getManager().reloadTop();
        Main.getManager().loadPlayers();
    }

    public void savePlayer(String name) {
        CashPlayer player = getPlayer(name);
        set(name, "value", player.getValue());
        set(name, "toggle", player.isToggle());
    }

    public void savePlayers() {
        if (getCache().isEmpty()) return;
        getCache().values().forEach(cash -> savePlayer(cash.getName()));
        getCache().clear();
    }
    public void loadPlayers() {
        if (Bukkit.getOnlinePlayers().isEmpty()) return;
        Bukkit.getOnlinePlayers().forEach(player -> loadPlayer(player.getName()));
    }


    private void set(String name, String path, Object value) {
        if (!existsPlayer(name)) createAccount(name);
        PreparedStatement stm = null;
        try {
            stm = getCon().prepareStatement("UPDATE `Cash_PLAYER` SET `" + path + "` = ? WHERE LOWER(`name`) = ?");
            stm.setObject(1, value);
            stm.setString(2, name.toLowerCase());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isLoaded(String name) {
        return getCache().containsKey(name.toLowerCase());
    }

    public CashPlayer getPlayer(String name) {
        if (!existsPlayer(name)) return null;
        if (!isLoaded(name)) loadPlayer(name);
        return getCache().get(name.toLowerCase());
    }



}
