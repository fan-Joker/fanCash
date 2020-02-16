package fanjoker.cash.commands;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fanjoker.cash.Main;
import fanjoker.cash.fanCash;

public class Cash implements CommandExecutor {

	private List<SubCommand> subcommands;

	public Cash(String command) {
		this.subcommands = new ArrayList<SubCommand>();
		this.subcommands.add(new CashHelp(command));
		this.subcommands.add(new CashCriar(command));
		this.subcommands.add(new CashDeletar(command));
		this.subcommands.add(new CashAdd(command));
		this.subcommands.add(new CashRemove(command));
		this.subcommands.add(new CashSet(command));
		this.subcommands.add(new CashEnviar(command));
		this.subcommands.add(new CashTop(command));
		this.subcommands.add(new CashToggle(command));
		this.subcommands.add(new CashReload(command));
		this.subcommands.add(new CashReset(command));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (!fanCash.economia.existsAccount(sender.getName()))
				fanCash.economia.createAccount(sender.getName(),
						new BigDecimal(0));

		}
		if (args.length >= 1) {
			String arg = args[0].toLowerCase();
			if (!this.subcommands.isEmpty()) {
				for (SubCommand subCommand : this.subcommands) {
					if (arg.equalsIgnoreCase(subCommand.getName().toLowerCase())
							|| subCommand.getAlias().contains(arg)) {
						if (sender.hasPermission(subCommand.getPermission()) || subCommand.getPermission().isEmpty()) {
							subCommand.execute(sender, args);
						} else {
							sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.NoPermission")
									.replace("&", "§"));
						}
						return false;
					}
				}
			}

				if (fanCash.economia.existsAccount(args[0])) {
					if (sender.getName().equals(args[0])) {
						if (sender instanceof Player) {
							sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.Cash.2")
									.replace("&", "§")
									.replace("{nome}", args[0])
									.replace("{saldo}", fanCash.getValue(fanCash.economia.getBalance(args[0]))));
						} else {
							sender.sendMessage("§a/" + command.getName() + " ajuda §8- §7ver os comandos do plugin.");
						}
					} else {
						sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.Cash.2")
								.replace("&", "§")
								.replace("{nome}", args[0])
								.replace("{saldo}", fanCash.getValue(fanCash.economia.getBalance(args[0]))));
					}
				} else {
					sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.NotFound")
							.replace("&", "§"));
				}

		} else {
			if (sender instanceof Player) {
				sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.Cash.1")
						.replace("&", "§")
						.replace("{saldo}", fanCash.getValue(fanCash.economia.getBalance(sender.getName()))));
			} else {
				sender.sendMessage("§a/" + command.getName() + " ajuda §8- §7ver os comandos do plugin.");
			}
		}

		return false;
	}
	
}
