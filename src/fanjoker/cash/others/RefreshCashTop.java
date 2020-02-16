package fanjoker.cash.others;

import org.bukkit.Bukkit;

import fanjoker.cash.fanCash;

public class RefreshCashTop implements Runnable {

	private int task;

	public RefreshCashTop() {
		this.task = -1;
		reload();
	}

	@Override
	public void run() {
		fanCash.economia.loadMoneyTop();
	}

	public void reload() {
		if (task != -1) {
			Bukkit.getScheduler().cancelTask(this.task);
		}
		int refresh_time = 300;
		this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(fanCash.instance, this, 10,
				20 * refresh_time);
	}

}
