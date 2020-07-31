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
		sender.sendMessage(" ");

		sender.sendMessage("§e/" + command + " §6- §fPara ver o seu cash atual.");
		sender.sendMessage("§e/" + command + " <jogador> §6- §fPara ver o cash de um jogador.");
		sender.sendMessage("§e/" + command + " enviar <jogador> <valor> §6- §fPara enviar cash a um jogador.");
		sender.sendMessage("§e/" + command + " top §6- §fPara ver o cash top do servidor.");
		sender.sendMessage("§e/" + command + " ajuda §6- §fPara ver os comandos.");

		if (sender instanceof Player)
			sender.sendMessage("§e/" + command + " toggle §6- §fPara ativar/desativar o recebimento.");

		if (sender.hasPermission("money.admin")) {
			sender.sendMessage("§e/" + command + " set <jogador> <valor> §6- §fPara setar o cash de um jogador.");
			sender.sendMessage("§e/" + command + " add <jogador> <valor> §6- §fPara adicionar cash a um jogador.");
			sender.sendMessage("§e/" + command + " remove <jogador> <valor> §6- §fPara remover cash de um jogador.");
			sender.sendMessage("§e/" + command + " reset <jogador> §6- §fPara resetar cash de um jogador.");
			sender.sendMessage("§e/" + command + " reload §6- §fPara recarregar o cash top.");
		}

		sender.sendMessage(" ");

	}

}
