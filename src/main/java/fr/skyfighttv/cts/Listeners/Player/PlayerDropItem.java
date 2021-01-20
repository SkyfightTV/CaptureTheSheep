package fr.skyfighttv.cts.Listeners.Player;

import fr.skyfighttv.cts.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener {

    @EventHandler
    private void onPlayerDrop(PlayerDropItemEvent event) {
        Game game = Game.getInstance();

        if (game.getPlayers().contains(event.getPlayer()))
            event.setCancelled(true);
    }
}
