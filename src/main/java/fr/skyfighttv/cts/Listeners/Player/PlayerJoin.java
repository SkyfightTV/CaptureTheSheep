package fr.skyfighttv.cts.Listeners.Player;

import fr.skyfighttv.cts.Commands.SubCommands.CTSSetup;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.PlayersManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerJoin implements Listener {
    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        try {
            PlayersManager.create(event.getPlayer());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Settings.getInstance().isSetup()) {
            YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

            if (spawnConfig.contains("Lobby"))
                event.getPlayer().teleport((Location) spawnConfig.get("Lobby"));

            Main.setLobbyInventory(event.getPlayer());
        } else {
            if (!event.getPlayer().hasPermission("CTS.setup")) return;

            CTSSetup.init(event.getPlayer());
        }
    }
}
