package me.fanjoker.cash.commands.cash;

import java.math.BigDecimal;

import me.fanjoker.cash.Main;
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

			player.setValue(player.getValue() - valor.doubleValue());
			sender.sendMessage(getCashRemove(player.getName(), NFormat.format1000(valor)));


		} else {
			sender.sendMessage(getUsage());
		}

	}

}
