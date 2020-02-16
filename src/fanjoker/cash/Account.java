package fanjoker.cash;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import fanjoker.cash.database.Database;

public class Account {

	public Account(String nome, BigDecimal valor) {
		this.name = nome;
		this.balance = valor;
	}

	private String name;
	private BigDecimal balance;
	private boolean toggle;

	private BukkitTask asyncSaveTask;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal valor) {
		this.balance = valor;
	}

	public boolean isToggle() {
		return toggle;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}

	public void saveAsync(long delay) {
		if (this.asyncSaveTask == null) {
			this.asyncSaveTask = Bukkit.getScheduler().runTaskLater(fanCash.instance, new Runnable() {
				@Override
				public void run() {
					try {
						Account.this.save();
						Account.this.asyncSaveTask = null;
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}, delay);
		}
	}

	public void save() {
		Database database = fanCash.database;
		String table = fanCash.table;
		database.open();
		try { 
			ResultSet result = database.query("select toggle from " + table + " where name='" + this.name + "'");
			if (result.next()) {
				database.execute("update " + table + " set valor='" + this.balance.toPlainString() + "', toggle='" + (this.toggle ? 1 : 0)
						+ "' where name='" + this.name + "'");
			} else {
				database.execute("insert into " + table + " values ('" + this.name + "', '" + this.balance.toPlainString() + "', '"
						+ (this.toggle ? 1 : 0) + "')");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		database.close();
	}

	public void delete() {
		Database database = fanCash.database;
		String table = fanCash.table;
		database.open();
		try {
			ResultSet result = database.query("select toggle from " + table + " where name='" + this.name + "'");
			if (result.next()) {
				database.execute("delete from " + table + " where name='" + this.name + "'");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		database.close();
	}

	public static Account valueOf(ResultSet result) {
		Account account = null;
		try {
			String name = result.getString("name");
			String valorString = result.getString("valor");
			boolean toggle = result.getInt("toggle") >= 1 ? true : false;
			account = new Account(name, new BigDecimal(valorString));
			account.setToggle(toggle);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return account;
	}

}
