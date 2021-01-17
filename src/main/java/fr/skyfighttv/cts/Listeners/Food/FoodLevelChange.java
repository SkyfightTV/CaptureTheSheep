package fr.skyfighttv.cts.Listeners.Food;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener {
    @EventHandler
    public void FoodChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player)
            event.setCancelled(true);
    }
}
