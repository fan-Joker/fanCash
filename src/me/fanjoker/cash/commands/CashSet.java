package me.fanjoker.cash.commands;

import java.math.BigDecimal;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.fanCash;
import me.fanjoker.cash.others.CashPlayer;
import org.bukkit.command.CommandSender;

public class CashSet extends SubCommand {

	public CashSet(String command) {
		super("set", "§cUse: /" + command + " set <jogador> <valor>", "cash.admin", "setar");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {
			String nome = args[1];

			CashPlayer player = Main.getManager().getPlayer(nome);

			if (player == null) {
				sender.sendMessage(getNotFound());
				return;
			}

			BigDecimal valor = this.numbers.getDecimal(args[2]);

			if (valor.doubleValue() < 0) {
				sender.sendMessage(getIncorrectValue());
				return;
			}

			sender.sendMessage(getCashSet(nome, "0"));

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
