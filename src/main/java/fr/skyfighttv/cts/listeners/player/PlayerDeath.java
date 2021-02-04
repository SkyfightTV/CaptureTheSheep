package fr.skyfighttv.cts.listeners.player;

import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import fr.skyfighttv.cts.utils.GameManager;
import fr.skyfighttv.cts.utils.PlayersManager;
import fr.skyfighttv.cts.utils.ScoreBoardManager;
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

        if (GameManager.getInGamePlayers().contains(player)
                && GameManager.getGames().contains(event.getEntity().getWorld())) {
            event.getDrops().clear();

            PlayersManager.addDeath(player, 1);

            player.spigot().respawn();

            GameManager.teleportPlayerTeam(event.getEntity().getWorld(), player);
            GameManager.givePlayerKit(player);

            ScoreBoardManager.forceUpdate(player);
            GameManager.getInvinciblePlayers().add(player);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> GameManager.getInvinciblePlayers().remove(player), (Settings.getInstance().getGameInvincibility() * 20L));
        }
    }
}