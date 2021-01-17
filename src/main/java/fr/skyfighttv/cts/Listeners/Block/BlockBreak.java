package fr.skyfighttv.cts.Listeners.Block;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    private void onBlockBreak(BlockBreakEvent event) {
        if (CTS.inGamePlayers.contains(event.getPlayer())) {
            if (GameManager.getGames().contains(event.getPlayer().getWorld())
                    && BlockPlace.blocksPlaced.contains(event.getBlock())) {
                event.setDropItems(false);
            }
            event.setCancelled(true);
        }
    }
}
