package fr.skyfighttv.cts.Listeners.Block;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Game;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
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
        Game game = Game.getInstance();

        if (game.getPlayers().contains(event.getPlayer())) {
            if (GameManager.getGames().contains(event.getPlayer().getWorld())) {
                YamlConfiguration config = FileManager.getValues().get(Files.Config);

                if (config.getStringList("AutoDelete.Blocks").contains(event.getBlock().getType().name())) {
                    blocksPlaced.add(event.getBlockPlaced());

                    event.getItemInHand().setAmount(64);

                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                        event.getBlockPlaced().setType(Material.AIR);
                        blocksPlaced.remove(event.getBlockPlaced());
                    }, (config.getInt("AutoDelete.Time") * 20));
                    return;
                }
            }
            if (event.getPlayer().isOp()) return;

            event.setCancelled(true);
        }
    }
}
