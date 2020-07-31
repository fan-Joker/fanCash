package me.fanjoker.cash.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CashHelp extends SubCommand {

	private String command;

	public CashHelp(String command) {
		super("help", "§cUse: /" + command + " ajuda", "", "ajuda", "?");
		this.command = command;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage("");
		sender.sendMessage("  §e§lCash: §7Lista de comandos:");
		sender.sendMessage("");
		sender.sendMessage("  §e/" + command + ": §7Para ver o seu cash atual.");
		sender.sendMessage("  §e/" + command + " <jogador>: §7Para ver o cash de um jogador.");
		sender.sendMessage("  §e/" + command + " top: §7Para ver o cash top do servidor.");
		sender.sendMessage("  §e/" + command + " ajuda: §7Para ver os comandos.");

		if (sender instanceof Player) {
			sender.sendMessage("  §e/" + command + " toggle: §7Para ativar/desativar o recebimento.");
			sender.sendMessage("  §e/" + command + " enviar <jogador> <valor>: §7Para enviar cash a um player.");
		}

		if (sender.hasPermission("money.admin")) {
			sender.sendMessage("  §e/" + command + " set <jogador> <valor>: §7Para setar o cash de um player.");
			sender.sendMessage("  §e/" + command + " add <jogador> <valor>: §7Para adicionar cash a um player.");
			sender.sendMessage("  §e/" + command + " remove <jogador> <valor>: §7Para remover cash de um player.");
			sender.sendMessage("  §e/" + command + " reset <jogador>: §7Para resetar cash de um player.");
			sender.sendMessage("  §e/" + command + " reload: §7Para recarregar o cash top.");
		}

		sender.sendMessage("");

	}

}
