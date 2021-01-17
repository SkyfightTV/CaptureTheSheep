package fr.skyfighttv.cts.Listeners.Player;

import fr.skyfighttv.cts.Commands.CTS;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener {
    @EventHandler
    private void onPlayerDrop(PlayerDropItemEvent event) {
        if (CTS.inGamePlayers.contains(event.getPlayer()))
            event.setCancelled(true);
    }
}
