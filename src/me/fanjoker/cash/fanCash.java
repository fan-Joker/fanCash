package me.fanjoker.cash;

import me.fanjoker.cash.others.CashPlayer;
import org.bukkit.event.Listener;

import java.util.List;

public class fanCash implements Listener {

//
//	API PARA DESENVOLVEDORES
//

	private static CashPlayer getCashPlayer(String name) {
		return Main.getManager().getPlayer(name);
	}

	public static double getCash(String name) {
		return getCashPlayer(name).getValue();
	}

	public static boolean getBolean(String name) {
		return getCashPlayer(name).isToggle();
	}

	public static void setCash(String name, double cash) {
		getCashPlayer(name).setValue(cash);
	}

	public static void addCash(String name, double cash) {
		getCashPlayer(name).setValue(getCash(name) + cash);
	}

	public static void remCash(String name, double cash) {
		getCashPlayer(name).setValue(getCash(name) - cash);
	}

	public static boolean hasCash(String name, double value) {
		return getCashPlayer(name).getValue() >= value;
	}

	public static List<CashPlayer> topList() { return Main.getManager().getTopList(); }


}
