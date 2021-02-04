package fr.skyfighttv.cts.listeners.entity;

import fr.skyfighttv.cts.utils.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplode implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    private void onEntityExplode(EntityExplodeEvent event) {
        if (GameManager.getNumberPlayers().containsKey(event.getEntity().getWorld()))
            event.setCancelled(true);
    }
}
