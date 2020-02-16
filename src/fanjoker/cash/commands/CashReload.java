package fanjoker.cash.commands;

import org.bukkit.command.CommandSender;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class CashReload extends SubCommand {

	public CashReload(String command) {
		super("reload", "§cUse: /" + command + " reload", "money.admin");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		fanCash.economia.loadMoneyTop();
		Main.configManager.getConfig("config").reload();
		fanCash.refreshCashTop.reload();
		
		sender.sendMessage("§eArquivos de configurações recarregados com sucesso.");

	}

}
