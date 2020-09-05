package me.fanjoker.cash.commands.cash;

import me.fanjoker.cash.Main;
import org.bukkit.command.CommandSender;

public class CashReload extends SubCommand {

	public CashReload(String command) {
		super("reload", "§cUse: /" + command + " reload", "money.admin");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		Main.getManager().reloadTop();
		Main.configManager.getConfig("config").reload();
		
		sender.sendMessage("§eArquivo de configuração recarregado com sucesso.");

	}

}
