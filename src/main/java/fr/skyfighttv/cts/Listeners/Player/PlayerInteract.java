package fr.skyfighttv.cts.Listeners.Player;

import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onRightClick(PlayerInteractEvent event) {
        if (!GameManager.getInGamePlayers().contains(event.getPlayer())) {
            if (!event.getPlayer().isOp()) {
                event.setCancelled(true);
            }
            if (event.getItem() == null
                    || event.getItem().getType().equals(Material.AIR)
                    || !event.getItem().hasItemMeta()) {
                return;
            }

            YamlConfiguration config = FileManager.getValues().get(Files.Config);

            for (String items : config.getConfigurationSection("LobbyItems").getKeys(false)) {
                if (event.getItem().getItemMeta().getLore().equals(config.getStringList("LobbyItems." + items + ".Lore"))
                        && event.getItem().getItemMeta().getDisplayName().equals(config.getString("LobbyItems." + items + ".Title"))) {
                    Bukkit.dispatchCommand(event.getPlayer(), config.getString("LobbyItems." + items + ".Command"));
                }
            }
        }
    }
}
