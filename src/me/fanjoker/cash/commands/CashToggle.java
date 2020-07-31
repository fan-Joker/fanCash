package me.fanjoker.cash.commands;

import me.fanjoker.cash.others.CashPlayer;
import org.bukkit.command.CommandSender;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.fanCash;
import org.bukkit.entity.Player;

public class CashToggle extends SubCommand {

	public CashToggle(String command) {
		super("toggle", "§cUse: /" + command + " toggle", "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(getOnlyPlayer());
			return;
		}
		CashPlayer player = Main.getManager().getPlayer(sender.getName());
		String toggle = player.isToggle() ? "desativou" : "ativou";

		sender.sendMessage(getCashToggle1(toggle));
		player.setToggle(!player.isToggle());

	}

}
