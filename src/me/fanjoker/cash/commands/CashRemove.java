package me.fanjoker.cash.commands;

import java.math.BigDecimal;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.fanCash;
import me.fanjoker.cash.others.CashPlayer;
import me.fanjoker.cash.others.NFormat;
import org.bukkit.command.CommandSender;

public class CashRemove extends SubCommand {

	public CashRemove(String command) {
		super("remove", "§cUse: /" + command + " remove <jogador> <valor>", "cash.admin");
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

			if (valor.doubleValue() <= 0) {
				sender.sendMessage(getIncorrectValue());
				return;
			}

			sender.sendMessage(getCashRemove(player.getName(), NFormat.format1000(player.getValue())));


		} else {
			sender.sendMessage(getUsage());
		}

	}

}
