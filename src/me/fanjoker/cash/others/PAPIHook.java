package me.fanjoker.cash.others;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.fanjoker.cash.fanCash;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PAPIHook extends PlaceholderExpansion {


    @Override
    public String getIdentifier() {
        return "fancash";
    }

    @Override
    public String getAuthor() {
        return "zFan";
    }

    @Override
    public String getVersion() {
        return "1337";
    }

    public String onPlaceholderRequest(Player p, String i)
    {
        if (p == null) return null;

        if (i.equalsIgnoreCase("cash")) {

            return NFormat.format1000(fanCash.getCash(p.getName()));
        }
        return null;
    }

}
