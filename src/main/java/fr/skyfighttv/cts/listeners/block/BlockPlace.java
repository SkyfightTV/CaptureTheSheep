package fr.skyfighttv.cts.listeners.block;

import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import fr.skyfighttv.cts.utils.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockPlace implements Listener {
    public static List<Block> blocksPlaced = new ArrayList<>();

    @EventHandler(priority = EventPriority.MONITOR)
    private void onBlockPlace(BlockPlaceEvent event) {
        if (GameManager.getInGamePlayers().contains(event.getPlayer())) {
            if (GameManager.getGames().contains(event.getPlayer().getWorld())) {
                if (Settings.getInstance().getAutoDeleteBlocks().contains(event.getBlock().getType().name())) {
                    blocksPlaced.add(event.getBlockPlaced());

                    event.getItemInHand().setAmount(64);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                        blocksPlaced.remove(event.getBlockPlaced());
                        event.getBlockPlaced().setType(Material.AIR);
                    }, (Settings.getInstance().getAutoDeleteTime() * 20L));
                    return;
                }
            }
            if (event.getPlayer().isOp()) return;

            event.setCancelled(true);
        }
    }
}
