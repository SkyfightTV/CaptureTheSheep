package fr.skyfighttv.cts.Listeners.Block;

import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import fr.skyfighttv.cts.Utils.GameManager;
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
                if (Settings.getAutoDeleteBlocks().contains(event.getBlock().getType().name())) {
                    blocksPlaced.add(event.getBlockPlaced());

                    event.getItemInHand().setAmount(64);

                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                        event.getBlockPlaced().setType(Material.AIR);
                        blocksPlaced.remove(event.getBlockPlaced());
                    }, (Settings.getAutoDeleteTime() * 20L));
                    return;
                }
            }
            if (event.getPlayer().isOp()) return;

            event.setCancelled(true);
        }
    }
}
