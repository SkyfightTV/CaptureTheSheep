package fr.skyfighttv.cts.Listeners.Entity;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    @EventHandler
    private void onEntityDamage(EntityDamageEvent event) {
        if (GameManager.getNumberPlayers().containsKey(event.getEntity().getWorld())) {
            if (event.getEntity() instanceof Sheep) {
                event.setCancelled(true);
            } else if (event.getEntity() instanceof Player
                    && CTS.inGamePlayers.contains(event.getEntity())
                    && event.getCause() == EntityDamageEvent.DamageCause.FALL
                    || !GameManager.getGames().contains(event.getEntity().getWorld())) {
                event.setCancelled(true);
            }
        }
    }
}
