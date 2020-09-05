package me.fanjoker.cash.commands.cash;

import java.util.ArrayList;
import java.util.List;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.config.Messages;
import me.fanjoker.cash.others.CashPlayer;
import me.fanjoker.cash.others.NFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cash implements CommandExecutor {

	private List<SubCommand> subcommands;
	private Messages messages = new Messages();

	public Cash(String command) {
		this.subcommands = new ArrayList<>();
		this.subcommands.add(new CashHelp(command));
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

		if (args.length >= 1) {
			String arg = args[0].toLowerCase();
			if (!this.subcommands.isEmpty()) {
				for (SubCommand subCommand : this.subcommands) {
					if (arg.equalsIgnoreCase(subCommand.getName().toLowerCase())
							|| subCommand.getAlias().contains(arg)) {
						if (sender.hasPermission(subCommand.getPermission()) || subCommand.getPermission().isEmpty()) {
							subCommand.execute(sender, args);
							return true;
						}
						sender.sendMessage(messages.getNoPermission());
						return false;
					}
				}
			}

			CashPlayer player = Main.getManager().getPlayer(args[0]);
			if (player == null) {
				sender.sendMessage(messages.getNotFound());
				return true;
			}
			sender.sendMessage(messages.getCash2(args[0], NFormat.format1000(player.getValue())));

		} else {
			if (sender instanceof Player) {
				CashPlayer player = Main.getManager().getPlayer(sender.getName());
				sender.sendMessage(messages.getCash1(NFormat.format1000(player.getValue())));
				return true;
			}
			sender.sendMessage("§a/" + command.getName() + " ajuda §8- §7ver os comandos do plugin.");
		}

		return false;
	}
	
}
