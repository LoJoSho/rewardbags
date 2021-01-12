package me.lojosho.rewardbags;

import me.lojosho.rewardbags.Listener.PlayerOpenBag;
import me.lojosho.rewardbags.Listener.voteListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class RewardBags extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new voteListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerOpenBag(this), this);
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


    String Displayname = "&a&lVoting Reward Bag!";
    public ItemStack GoddieBag() {
        ItemStack Bag = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta bagmeta = Bag.getItemMeta();
        bagmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Displayname));
        bagmeta.addEnchant(Enchantment.DURABILITY, 1, false);
        bagmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        bagmeta.setCustomModelData(2);
        ArrayList ItemLore = new ArrayList<String>();
        ItemLore.add(ChatColor.translateAlternateColorCodes('&', "&7Open for a &a&lSuprise!"));
        ItemLore.add(ChatColor.translateAlternateColorCodes('&', "&7"));
        ItemLore.add(ChatColor.translateAlternateColorCodes('&', "&a&l$25-100"));
        ItemLore.add(ChatColor.translateAlternateColorCodes('&', "&b&lValuable Items!"));
        ItemLore.add(ChatColor.translateAlternateColorCodes('&', "&d&lIntersting loot..."));
        bagmeta.setLore(ItemLore);
        Bag.setItemMeta(bagmeta);
        return Bag;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
