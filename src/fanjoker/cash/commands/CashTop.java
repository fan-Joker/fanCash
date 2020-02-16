package fanjoker.cash.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import fanjoker.cash.fanCash;
import fanjoker.cash.Account;
import fanjoker.cash.Main;
import fanjoker.cash.others.Vault;

public class CashTop extends SubCommand {

	public CashTop(String command) {
		super("top", "§cUse: /" + command + " top", "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		List<Account> moneytop = fanCash.economia.getMoneyTop();

		if (!moneytop.isEmpty()) {
			int i = 1;
			sender.sendMessage(" ");
			sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashTop.Title").replace("&", "§"));
			sender.sendMessage(" ");
			for (Account account : moneytop) {
				String valor = fanCash.getValue(account.getBalance());
				String accountname = account.getName();
				Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
				if (vault != null) {
					accountname = Vault.getPrefix(account.getName()).concat(account.getName());
				}

				if (i == 1) {
					sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashTop.Settings")
							.replace("&", "§")
							.replace("{numero}", "" + i)
							.replace("{nome}", accountname.replace("&", "§"))
							.replace("{saldo}", valor));
				} else {
					sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashTop.Settings")
							.replace("&", "§")
							.replace("{numero}", "" + i)
							.replace("{nome}", accountname.replace("&", "§"))
							.replace("{saldo}", valor));
				}

				i++;
			}
			sender.sendMessage(" ");

		} else {
			sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashTop.Title").replace("&", "§"));
			sender.sendMessage(" ");
			sender.sendMessage(Main.configManager.getConfig("config").getYaml().getString("Mensagens.CashTop.2").replace("&", "§"));
			sender.sendMessage(" ");
		}

	}

}