package fr.skyfighttv.cts.Listeners.Player;

import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerQuit implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        GameManager.getInGamePlayers().remove(player);

        GameManager.getNumberPlayers().get(event.getPlayer().getWorld()).remove(player);
        GameManager.getRedTeam().get(event.getPlayer().getWorld()).remove(player);
        GameManager.getBlueTeam().get(event.getPlayer().getWorld()).remove(player);

        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[0]);
    }
}
