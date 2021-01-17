package fr.skyfighttv.cts.Listeners.Entity;

import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    private void onEntitySpawn(EntitySpawnEvent event) {
        if (GameManager.getNumberPlayers().containsKey(event.getLocation().getWorld()))
            if (!(event.getEntity() instanceof Sheep))
                event.setCancelled(true);
    }
}
