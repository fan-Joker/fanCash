package me.fanjoker.cash.commands;

import java.math.BigDecimal;

import me.fanjoker.cash.others.CashPlayer;
import me.fanjoker.cash.others.NFormat;
import org.bukkit.command.CommandSender;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.fanCash;

public class CashAdd extends SubCommand {

	public CashAdd(String command) {
		super("add", "§cUse: /" + command + " add <jogador> <valor>", "cash.admin", "give");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {

			String nome = args[1];

			CashPlayer player = Main.getManager().getPlayer(args[1]);

			if (player == null) {
				sender.sendMessage(getNotFound());
				return;
			}

			BigDecimal valor = this.numbers.getDecimal(args[2]);

			if (valor.doubleValue() <= 0) {
				sender.sendMessage(getIncorrectValue());
				return;
			}

			sender.sendMessage(getCashAdd(nome, NFormat.format1000(valor)));

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
