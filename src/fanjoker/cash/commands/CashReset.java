package fanjoker.cash.commands;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class CashReset extends SubCommand {

	public CashReset(String command) {
		super("reset", "§cUse: /" + command + " reset <jogador>", "cash.admin", "resetar");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if (args.length >= 2) {
			String nome = args[1];

			if (fanCash.economia.setBalance(nome, BigDecimal.valueOf(0))) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashSet")
						.replace("&", "§")
						.replace("{nome}", nome)
						.replace("{saldo}", fanCash.numberFormat(BigDecimal.valueOf(0))));
			} else {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.NotFound")
						.replace("&", "§"));
			}
		} else {
			sender.sendMessage(getUsage());
		}
		
	}

}
