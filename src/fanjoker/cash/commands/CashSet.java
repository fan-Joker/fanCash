package fanjoker.cash.commands;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class CashSet extends SubCommand {

	public CashSet(String command) {
		super("set", "§cUse: /" + command + " set <jogador> <valor>", "cash.admin", "setar");
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

			if (fanCash.economia.setBalance(nome, valor)) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashSet")
						.replace("&", "§")
						.replace("{nome}", nome)
						.replace("{saldo}", fanCash.getValue(valor)));
//						"§aFoi setado a quantia de " + fanCash.numberFormat(valor) + " na conta de " + nome);
			} else {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.NotFound")
						.replace("&", "§"));
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
