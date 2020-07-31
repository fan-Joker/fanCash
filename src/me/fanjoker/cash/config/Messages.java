package me.fanjoker.cash.config;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.fanCash;
import me.fanjoker.cash.others.NFormat;
import org.bukkit.ChatColor;

public class Messages {

    public String getCashTopSettings(int i, String name, String valor) {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashTop.Settings")
                .replace("{numero}", "" + i)
                .replace("{nome}", name)
                .replace("{saldo}", "" + valor));
    }

    public String getCashSet(String nome, String value) {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashSet")
                .replace("{nome}", nome)
                .replace("{saldo}", value));
    }

    public String getCashTopTittle() {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashTop.Title"));
    }

    public String getYourself() {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.Yourself"));
    }

    public String getCashRemove(String name, String value) {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashRemove")
                .replace("{nome}", name)
                .replace("{saldo}", value));
    }

    public String getCashTop2() {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashTop.2"));
    }

    public String getCash2(String name, String value) {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.Cash.2")
                .replace("{nome}", name)
                .replace("{saldo}", value));
    }

    public String getCash1(String value) {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.Cash.1")
                .replace("{saldo}", value));
    }

    public String getCashAdd(String nome, String valor) {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashAdd")
                .replace("{nome}", nome)
                .replace("{saldo}", valor));
    }

    public String getCashEnviar3() {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashEnviar.3"));
    }

    public String getCashToggle2() {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashToggle.2"));
    }

    public String getCashEnviar2(String nome, String valor) {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashEnviar.2")
                .replace("{nome}", nome)
                .replace("{saldo}", valor));
    }

    public String getCashEnviar1(String nome, String valor) {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashEnviar.1")
                .replace("{nome}", nome)
                .replace("{saldo}", valor));
    }


    public String getNotFound() {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.NotFound"));
    }

    public String getIncorrectValue() {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.IncorrectValue"));
    }

    public String getNoPermission() {
        return colorText(Main.configManager.getConfig("config").getYaml().getString("Mensagens.NoPermission"));
    }


    public String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
