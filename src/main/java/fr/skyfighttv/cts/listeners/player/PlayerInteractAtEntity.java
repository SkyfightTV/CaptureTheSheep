package fr.skyfighttv.cts.listeners.player;

import fr.skyfighttv.cts.utils.GameManager;
import fr.skyfighttv.cts.utils.SheepManager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntity implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Sheep
                && GameManager.getInGamePlayers().contains(event.getPlayer())
                && GameManager.getGames().contains(event.getPlayer().getWorld())) {
            Sheep sheepClicked = (Sheep) event.getRightClicked();
            Player player = event.getPlayer();

            SheepManager.setPassenger(player, sheepClicked);
        }
    }
}
