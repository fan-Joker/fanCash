package fanjoker.cash.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CashHelp extends SubCommand {

	private String command;

	public CashHelp(String command) {
		super("help", "§cUse: /" + command + " ajuda", "", "ajuda");
		this.command = command;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(" ");

		Player p = (Player)sender;
		if (sender instanceof Player) {
			if(p.hasPermission("money.admin")) {
				p.sendMessage("§e/" + command + " §6- §fPara ver o seu cash atual.");
				p.sendMessage("§e/" + command + " <jogador> §6- §fPara ver o cash de um jogador.");
				p.sendMessage("§e/" + command + " enviar <jogador> <valor> §6- §fPara enviar cash a um jogador.");
				p.sendMessage("§e/" + command + " top §6- §fPara ver o cash top do servidor.");
				p.sendMessage("§e/" + command + " ajuda §6- §fPara ver os comandos.");
				p.sendMessage("§e/" + command + " set <jogador> <valor> §6- §fPara setar o cash de um jogador.");
				p.sendMessage("§e/" + command + " add <jogador> <valor> §6- §fPara adicionar cash a um jogador.");
				p.sendMessage("§e/" + command + " remove <jogador> <valor> §6- §fPara remover cash de um jogador.");
				p.sendMessage("§e/" + command + " reset <jogador> §6- §fPara resetar cash de um jogador.");
				p.sendMessage("§e/" + command + " reload §6- §fPara recarregar o cash top.");
				//p.sendMessage("§e/" + command + " converter [plugin] §6- §fconverter a economia de um plugin.");
			} else {
				p.sendMessage("§e/" + command + " §6- §fPara ver o seu cash atual.");
				p.sendMessage("§e/" + command + " <jogador> §6- §fPara ver o cash de um jogador.");
				p.sendMessage("§e/" + command + " enviar <jogador> <valor> §6- §fPara enviar cash a um jogador.");
				p.sendMessage("§e/" + command + " top §6- §fPara ver o cash top do servidor.");
				p.sendMessage("§e/" + command + " ajuda §6- §fPara ver os comandos.");
			}
				
		}

		sender.sendMessage(" ");

	}

}
