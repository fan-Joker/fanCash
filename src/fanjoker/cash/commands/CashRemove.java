package fanjoker.cash.commands;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class CashRemove extends SubCommand {

	public CashRemove(String command) {
		super("remove", "§cUse: /" + command + " remove <jogador> <valor>", "cash.admin");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {
			String nome = args[1];
			BigDecimal valor = this.numbers.getDecimal(args[2]);
			
			if (valor.doubleValue() <= 0) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.IncorrectValue")
						.replace("&", "§"));
				return;
			}

			if (fanCash.economia.substractBalance(nome, valor)) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashRemove")
						.replace("&", "§")
						.replace("{nome}", nome)
						.replace("{saldo}", fanCash.getValue(valor)));
//						"§aFoi removido a quantia de " + fanCash.numberFormat(valor) + " da conta de " + nome);
			} else {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.NotFound")
						.replace("&", "§"));
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
