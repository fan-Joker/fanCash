package fanjoker.cash.commands;

import org.bukkit.command.CommandSender;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class CashToggle extends SubCommand {

	public CashToggle(String command) {
		super("toggle", "§cUse: /" + command + " toggle", "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		String toggle = fanCash.economia.toggle(sender.getName()) ? "desativou" : "ativou";
		sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashToggle.1")
						.replace("&", "§")
						.replace("{toggle}", toggle));

	}

}
