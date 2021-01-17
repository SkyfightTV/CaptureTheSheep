package fr.skyfighttv.cts.Listeners.Player;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Utils.GameManager;
import fr.skyfighttv.cts.Utils.SheepManager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntity implements Listener {
    @EventHandler
    private void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Sheep
                && CTS.inGamePlayers.contains(event.getPlayer())
                && GameManager.getGames().contains(event.getPlayer().getWorld())) {
            Sheep sheepClicked = (Sheep) event.getRightClicked();
            Player player = event.getPlayer();

            SheepManager.setPassenger(player, sheepClicked);
        }
    }
}
