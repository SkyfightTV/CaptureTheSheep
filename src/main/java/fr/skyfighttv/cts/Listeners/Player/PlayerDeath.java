package fr.skyfighttv.cts.Listeners.Player;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.GameManager;
import fr.skyfighttv.cts.Utils.PlayersManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;

public class PlayerDeath implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        Player player = event.getEntity();

        if (CTS.inGamePlayers.contains(player)
                && GameManager.getGames().contains(event.getEntity().getWorld())) {
            event.getDrops().clear();

            PlayersManager.addDeath(player, 1);

            player.spigot().respawn();

            GameManager.teleportPlayerTeam(event.getEntity().getWorld(), player);
            GameManager.givePlayerKit(player);

            CTS.invinciblePlayers.add(player);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> CTS.invinciblePlayers.remove(player), FileManager.getValues().get(Files.Config).getInt("Game.Invincibility"));
        }
    }
}
