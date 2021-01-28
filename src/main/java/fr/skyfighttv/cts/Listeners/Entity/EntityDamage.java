package fr.skyfighttv.cts.Listeners.Entity;

import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    private void onEntityDamage(EntityDamageEvent event) {
        if (GameManager.getNumberPlayers().containsKey(event.getEntity().getWorld())) {
            if (event.getEntity() instanceof Player
                    && GameManager.getInGamePlayers().contains(event.getEntity())
                    && event.getCause() == EntityDamageEvent.DamageCause.FALL
                    || !GameManager.getGames().contains(event.getEntity().getWorld())
                    || GameManager.getInvinciblePlayers().contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }
}
