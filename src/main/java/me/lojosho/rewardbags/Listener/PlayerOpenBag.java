package me.lojosho.rewardbags.Listener;

import me.lojosho.rewardbags.RewardBags;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;

import java.util.Random;

import static me.lojosho.rewardbags.RewardBags.econ;

public class PlayerOpenBag implements Listener {

    Random rand = new Random();

    private final RewardBags plugin;

    public PlayerOpenBag(RewardBags plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent event) {
        String player = event.getPlayer().getName();
        if (!event.getItem().getItemMeta().getItemFlags() == null) {
            if (event.getItem().getItemMeta().getDisplayName().contains("Voting Reward Bag!")) {
                if (event.getItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)) {
                    EconomyResponse r = econ.depositPlayer(player, RandomMoney());
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&lYou have gotten &a&l$" + RandomMoney()));
                    event.getItem().add(-1);
                }
            }
        }
    }

    public int RandomMoney() {
        Random rand = new Random();
        int minRange = 25, maxRange= 100;
        int randommoney = rand.nextInt(maxRange - minRange) + minRange;
        return randommoney;
    }
}
