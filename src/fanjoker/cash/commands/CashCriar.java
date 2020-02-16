package fanjoker.cash.commands;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class CashCriar extends SubCommand {

	public CashCriar(String command) {
		super("criar", "§cUse: /" + command + " criar <nome> <valor>", "cash.admin");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {
			String nome = args[1];

			BigDecimal valor = this.numbers.getDecimal(args[2]);

			if (valor.doubleValue() < 0) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.IncorrectValue")
						.replace("&", "§"));
				return;
			}

			if (fanCash.economia.createAccount(nome, valor)) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashCriar.1")
						.replace("&", "§")
						.replace("{nome}", nome));

			} else {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashCriar.2")
						.replace("&", "§")
						.replace("{nome}", nome));
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
