package fr.skyfighttv.cts.listeners.food;

import fr.skyfighttv.cts.utils.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void FoodChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player
                && GameManager.getInGamePlayers().contains(event.getEntity()))
            event.setCancelled(true);
    }
}
