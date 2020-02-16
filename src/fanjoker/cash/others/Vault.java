package fanjoker.cash.others;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;

public class Vault {

	public Vault() {
	}

	@SuppressWarnings("deprecation")
	public static String getPrefix(String string) {

		String prefix = "";
		try {
			RegisteredServiceProvider<Chat> chatclazz = Bukkit.getServicesManager().getRegistration(Chat.class);
			if (chatclazz != null) {
				Chat chat = chatclazz.getProvider();
				if (chat != null) {
					try {
						prefix = chat.getPlayerSuffix(("world"), string).replace("&",
								"§");
					} catch (Exception e) {
						prefix = "";
					}
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return prefix;
	}

}
