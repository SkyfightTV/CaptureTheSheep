package fr.skyfighttv.cts.Listeners.Entity;

import fr.skyfighttv.cts.Utils.GameManager;
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
