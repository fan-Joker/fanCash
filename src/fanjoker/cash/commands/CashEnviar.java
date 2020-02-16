package fanjoker.cash.commands;

import java.math.BigDecimal;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class CashEnviar extends SubCommand {

	public CashEnviar(String command) {
		super("enviar", "§cUse: /" + command + " enviar <jogador> <cash>", "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {
			String nome = args[1];

			BigDecimal valor = this.numbers.getDecimal(args[2]);

			if (valor.doubleValue() < 1.0) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.IncorrectValue")
						.replace("&", "§"));
				return;
			}

			if (!(sender instanceof Player))
				return;

			if (sender.getName().equalsIgnoreCase(nome)) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.Yourself")
						.replace("&", "§"));
				return;
			}

			if (fanCash.economia.hasBalance(sender.getName(), valor)) {

				if (!fanCash.economia.isToggle(nome)) {
					if (fanCash.economia.addBalance(nome, valor)) {
						fanCash.economia.substractBalance(sender.getName(), valor);
						sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashEnviar.1")
								.replace("&", "§")
								.replace("{nome}", nome)
								.replace("{saldo}", fanCash.getValue(valor)));

						Player target = Bukkit.getPlayer(nome);
						if (target != null) {
							if (sender != target) {
								target.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashEnviar.2")
										.replace("&", "§")
										.replace("{nome}", nome)
										.replace("{saldo}", fanCash.getValue(valor)));
							}
						}

					} else {
						sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.NotFound")
								.replace("&", "§"));
					}
				} else {
					sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashToggle.2")
							.replace("&", "§"));
				}
			} else {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashEnviar.3")
						.replace("&", "§"));
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
