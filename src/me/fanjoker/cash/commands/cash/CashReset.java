package me.fanjoker.cash.commands.cash;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.others.CashPlayer;
import org.bukkit.command.CommandSender;

public class CashReset extends SubCommand {

	public CashReset(String command) {
		super("reset", "§cUse: /" + command + " reset <jogador>", "cash.admin", "resetar");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if (args.length >= 2) {
			String nome = args[1];

			CashPlayer player = Main.getManager().getPlayer(nome);

			if (player == null) {
				sender.sendMessage(getNotFound());
				return;
			}

			player.setValue(0D);
			sender.sendMessage(getCashSet(player.getName(), "0"));

		} else {
			sender.sendMessage(getUsage());
		}
		
	}

}
