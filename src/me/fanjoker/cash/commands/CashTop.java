package me.fanjoker.cash.commands;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import me.fanjoker.cash.others.CashPlayer;
import me.fanjoker.cash.others.NFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import me.fanjoker.cash.fanCash;
import me.fanjoker.cash.Main;

public class CashTop extends SubCommand {

	public CashTop(String command) {
		super("top", "§cUse: /" + command + " top", "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Map<String, Double> moneytop = Main.getManager().getTopList();

		if (!moneytop.isEmpty()) {
			int i = 1;
			sender.sendMessage(" ");
			sender.sendMessage(getCashTopTittle());
			sender.sendMessage(" ");
			for (String name : moneytop.keySet()) {

				double value = moneytop.get(name);
				sender.sendMessage(getCashTopSettings(i, name, NFormat.format1000(value)));

				i++;
			}
			sender.sendMessage(" ");
			return;
		}

		sender.sendMessage("");
		sender.sendMessage(getCashTopTittle());
		sender.sendMessage(" ");
		sender.sendMessage(getCashTop2());
		sender.sendMessage(" ");

	}

}