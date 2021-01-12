package me.lojosho.rewardbags.Listener;

import com.vexsoftware.votifier.model.VotifierEvent;
import me.lojosho.rewardbags.RewardBags;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class voteListener implements Listener {

    private final RewardBags plugin;

    public voteListener(RewardBags plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void onPlayerVote(VotifierEvent event) {
        String player = event.getVote().getUsername();
        Bukkit.getPlayer(player).getInventory().addItem(plugin.GoddieBag());
    }
}
