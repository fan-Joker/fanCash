package me.fanjoker.cash.commands;

import java.util.List;

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
		List<CashPlayer> moneytop = Main.getManager().getTopList();

		if (!moneytop.isEmpty()) {
			int i = 1;
			sender.sendMessage(" ");
			sender.sendMessage(getCashTopTittle());
			sender.sendMessage(" ");
			for (CashPlayer player : Main.getManager().getTopList()) {

				double value = player.getValue();
				String name = player.getName();

				if (i == 1)
					sender.sendMessage(getCashTopSettings(i, name, NFormat.format1000(value)));
				else
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