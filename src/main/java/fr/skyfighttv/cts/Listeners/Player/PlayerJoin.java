package fr.skyfighttv.cts.Listeners.Player;

import fr.skyfighttv.cts.Commands.SubCommands.CTSSetup;
import fr.skyfighttv.cts.Game;
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
        Game game = Game.getInstance();

        if (FileManager.getValues().get(Files.Config).getBoolean("IsSetup")) {
            try {
                PlayersManager.create(event.getPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

            if (spawnConfig.contains("Lobby"))
                event.getPlayer().teleport((Location) spawnConfig.get("Lobby"));
            game.setLobbyInventory(event.getPlayer());
        } else {
            if (!event.getPlayer().hasPermission("CTS.setup")) {
                event.getPlayer().sendMessage(FileManager.getValues().get(Files.Lang).getString("NoPermission"));
                return;
            }

            CTSSetup.init(event.getPlayer());
        }
    }
}
