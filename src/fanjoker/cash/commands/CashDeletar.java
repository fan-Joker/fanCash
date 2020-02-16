package fanjoker.cash.commands;

import org.bukkit.command.CommandSender;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class CashDeletar extends SubCommand {

	public CashDeletar(String command) {
		super("deletar", "§cUse: /" + command + " deletar <nome>", "cash.admin");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 2) {
			String nome = args[1];
			if (fanCash.economia.deleteAccount(nome)) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashDeletar.1")
						.replace("&", "§")
						.replace("{nome}", nome));
			} else {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashDeletar.2")
						.replace("&", "§")
						.replace("{nome}", nome));
			}
		} else {
			sender.sendMessage(getUsage());
		}

	}

}
