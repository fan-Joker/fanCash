package me.fanjoker.cash.commands;

import java.util.Arrays;
import java.util.List;

import me.fanjoker.cash.Main;
import me.fanjoker.cash.config.Messages;
import me.fanjoker.cash.fanCash;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {

	private String name;
	private String usage;
	private String permission;
	private List<String> alias;
	public Numbers numbers;

	public SubCommand(String name, String usage, String permission, String... alias) {
		this.name = name;
		this.usage = usage;
		this.permission = permission;
		this.alias = Arrays.asList(alias);
		this.numbers = new Numbers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

//
//	GET MESSAGES
//
	private Messages messages = new Messages();

	public String getOnlyPlayer() {
		return "§cSomente jogadores podem executar esse comando.";
	}

	public String getCashTopSettings(int i, String name, String valor) {
		return messages.getCashTopSettings(i, name, valor);
	}

	public String getCashSet(String nome, String value) {
		return messages.getCashSet(nome, value);
	}

	public String getCashTopTittle() {
		return messages.getCashTopTittle();
	}

	public String getYourself() {
		return messages.getYourself();
	}

	public String getCashRemove(String name, String value) {
		return messages.getCashRemove(name, value);
	}

	public String getCashTop2() {
		return messages.getCashTop2();
	}

	public String getNotFound() {
		return messages.getNotFound();
	}

	public String getIncorrectValue() {
		return messages.getNotFound();
	}

	public String getCashToggle1(String toggle) {
		return messages.getCashToggle1(toggle);
	}

	public String getCashToggle2() {
		return messages.getCashToggle2();
	}

	public String getCashEnviar2(String nome, String valor) {
		return messages.getCashEnviar2(nome, valor);
	}

	public String getCashEnviar1(String nome, String valor) {
		return messages.getCashEnviar1(nome, valor);
	}

	public String getCashEnviar3() {
		return messages.getCashEnviar3();
	}

	public String getNoPermission() {
		return messages.getNoPermission();
	}

	public String getCashAdd(String nome, String valor) {
		return messages.getCashAdd(nome, valor);
	}




	public String colorText(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	public abstract void execute(CommandSender sender, String[] args);

}
