package me.fanjoker.cash.commands;

import java.math.BigDecimal;

import me.fanjoker.cash.others.CashPlayer;
import me.fanjoker.cash.others.NFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.fanCash;

public class CashEnviar extends SubCommand {

	public CashEnviar(String command) {
		super("enviar", "§cUse: /" + command + " enviar <jogador> <cash>", "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(getOnlyPlayer());
			return;
		}

		if (args.length >= 3) {

			String nome = args[1];

			CashPlayer player = Main.getManager().getPlayer(nome);
			CashPlayer cashSender = Main.getManager().getPlayer(sender.getName());

			if (player == null) {
				sender.sendMessage(getNotFound());
				return;
			}

			BigDecimal valor = this.numbers.getDecimal(args[2]);

			if (valor.doubleValue() < 1.0) {
				sender.sendMessage(getIncorrectValue());
				return;
			}

			if (sender.getName().equalsIgnoreCase(nome)) {
				sender.sendMessage(getYourself());
				return;
			}

			if (valor.doubleValue() > player.getValue()) {
				sender.sendMessage(getCashEnviar3());
				return;
			}
			if (player.isToggle()) {
				sender.sendMessage(getCashToggle2());
				return;
			}

			player.setValue(player.getValue() + valor.doubleValue());
			cashSender.setValue(cashSender.getValue() - valor.doubleValue());

			sender.sendMessage(getCashEnviar1(nome, NFormat.format1000(valor)));

			Player target = Bukkit.getPlayer(nome);
			if (target != null) {
				target.sendMessage(getCashEnviar2(nome, NFormat.format1000(valor)));
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
