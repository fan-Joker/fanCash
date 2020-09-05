package me.fanjoker.cash.commands.shop;

import com.mojang.authlib.BaseUserAuthentication;
import me.fanjoker.cash.Main;
import me.fanjoker.cash.fanCash;
import me.fanjoker.cash.others.CashPlayer;
import me.fanjoker.cash.others.ItemBuilder;
import me.fanjoker.cash.others.NFormat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Shop implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage("§cA consola não pode executar comandos. SEU NOOB DO KRL");
            return true;
        }

        Player p = (Player)s;
        p.openInventory(mainInv(p));

        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().startsWith("Loja de Cash")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (e.getCurrentItem().getType() == Material.AIR) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            if (e.getCurrentItem().equals(voltarItem())) {
                p.openInventory(mainInv(p));
                return;
            }

            if (e.getInventory().getTitle().equalsIgnoreCase("Loja de Cash")) {

                if (e.getSlot() == 20) p.openInventory(swordsInv(p));
                else if (e.getSlot() == 21) p.openInventory(chavesInv(p));
                else if (e.getSlot() == 22) p.openInventory(diversosInv(p));


            } else if (e.getInventory().getTitle().equalsIgnoreCase("Loja de Cash - Espadas")) {

                if (e.getSlot() == 10) buyItem(p, 100, "espada1");

            } else if (e.getInventory().getTitle().equalsIgnoreCase("Loja de Cash - Chaves")) {

                if (e.getSlot() == 11) buyItem(p, 30, "crate1");
                else if (e.getSlot() == 12) buyItem(p, 250, "/crate giveall physical comum 1");

                else if (e.getSlot() == 14) buyItem(p, 70, "crate3");
                else if (e.getSlot() == 15) buyItem(p, 500, "/crate giveall physical epic 1");

            } else if (e.getInventory().getTitle().equalsIgnoreCase("Loja de Cash - Chaves")) {

                if (e.getSlot() == 11) buyItem(p, 150, "booster");
                else if (e.getSlot() == 13) buyItem(p, 270, "resetkdr");
                else if (e.getSlot() == 15) buyItem(p, 100, "/effect %name% minecraft:haste 300 2");

            }
        }
    }

    private void buyItem(Player p, double value, String kitName) {
        if (fanCash.getCash(p.getName()) < 30) return;
        if (kitName.startsWith("/"))
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), kitName
                    .replace("/", "")
                    .replace("%name%", p.getName()));
        else
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "darkit " + kitName + " " + p.getName());
        fanCash.remCash(p.getName(), 30);
    }


    private Inventory mainInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "Loja de Cash");
        double cash = Main.getManager().getPlayer(p.getName()).getValue();

        inv.setItem(4, new ItemBuilder(Material.GOLD_NUGGET).name("§aVocê possui:").lore("§6✪§f" + NFormat.format1000(cash)).removeAttributes().build());

        inv.setItem(20, new ItemBuilder(Material.DIAMOND_SWORD).name("§eFerramentas & Espadas").lore(
                "§7Adquira espadas ou ferramentas",
                "§7que lhe dão mais drops e",
                "§7minérios.", "",
                "§fItens disponíveis: §a1").enchant(Enchantment.DURABILITY, 1).removeAttributes().build());

        inv.setItem(21, new ItemBuilder(Material.TRIPWIRE_HOOK).name("§eChaves").lore(
                "§7Adquira chaves individuais",
                "§7ou para todos os jogadores",
                "§7online.", "",
                "§fChaves disponíveis: §a4").enchant(Enchantment.DURABILITY, 1).removeAttributes().build());

        inv.setItem(22, new ItemBuilder(Material.GHAST_TEAR).name("§eDiversos").lore(
                "§7Adquira itens que não se",
                "§7adequam nas restantes",
                "§7categorias.", "",
                "§fItens disponíveis: §a3").removeAttributes().build());

        for (int i = 23; i <= 33; i++) {
            if (i >= 25 && i <= 28) continue;
            inv.setItem(i, new ItemBuilder(Material.SKULL_ITEM).durability(3)
                    .head("http://textures.minecraft.net/texture/31a3cd9b016b1228ec01fd6f0992c64f3b9b7b29773fa46439ab3f3c8a347704")
                    .name("§c§kasdaa")
                    .lore("§7Categoria indisponível.").build());
        }

        return inv;
    }
    private Inventory swordsInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, "Loja de Cash - Espadas");

        inv.setItem(10, cashItem(p, 100D, Material.DIAMOND_SWORD, "§aEspada Farm"));
        inv.setItem(31, voltarItem());

        return inv;
    }

    private Inventory chavesInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, "Loja de Cash - Chaves");

        inv.setItem(11, cashItem(p, 30D, Material.TRIPWIRE_HOOK, "§bChave única",
                 "§fTipo: §7Comum"));
        inv.setItem(12, cashItem(p, 250D, Material.TRIPWIRE_HOOK, "§bChave ALL",
                "§fTipo: §7Comum"));
        inv.setItem(14, cashItem(p, 70D, Material.TRIPWIRE_HOOK, "§bChave única",
                "§fTipo: §7Epic"));
        inv.setItem(15, cashItem(p, 500D, Material.TRIPWIRE_HOOK, "§bChave ALL",
                "§fTipo: §7Epic"));
        inv.setItem(31, voltarItem());

        return inv;
    }

    private Inventory diversosInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, "Loja de Cash - Diversos");

        inv.setItem(11, cashItem(p, 150D, Material.EXP_BOTTLE, "§bBooster McMMO",
                "§fTipo: §7Booster instantâneo",
                "§fDuração: §71 hora",
                "§fMultiplicador: §72x"));
        inv.setItem(13, cashItem(p, 270D, Material.MAGMA_CREAM, "§bReset KDR",
                "§fTipo: §7Reset Instantâneo"));
        inv.setItem(15, cashItem(p, 100D, Material.RABBIT_HIDE, "§bHaste II",
                "§fTipo: §7Haste Instantâneo", "§fDuração: §75 minutos"));
        inv.setItem(31, voltarItem());

        return inv;
    }

    private ItemStack voltarItem() {
        return new ItemBuilder(Material.ARROW).name("§aVoltar").removeAttributes().build();
    }

    private ItemStack cashItem(Player p, double cash, Material mat, String name, String... lore) {
        double cashPlayer = Main.getManager().getPlayer(p.getName()).getValue();
        if (cashPlayer >= cash) return new ItemBuilder(mat).name(name).lore(lore.clone()).lore("", " §fCusto: §6✪" + NFormat.format1000(cash), "").removeAttributes().build();

        return new ItemBuilder(mat).name(name).lore(lore.clone())
                .lore("", " §fCusto: §6✪" + NFormat.format1000(cash), "", "§cVocê não possui cash suficiente", "§cpara adquirir este item.", "").removeAttributes().build();
    }
}
