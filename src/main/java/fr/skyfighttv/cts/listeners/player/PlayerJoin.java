package fr.skyfighttv.cts.listeners.player;

import fr.skyfighttv.cts.commands.subcommands.CTSSetSpawn;
import fr.skyfighttv.cts.commands.subcommands.CTSSetup;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.utils.FileManager;
import fr.skyfighttv.cts.utils.Files;
import fr.skyfighttv.cts.utils.PlayersManager;
import fr.skyfighttv.cts.utils.TempManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerJoin implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    private void onJoin(PlayerJoinEvent event) {
        try {
            PlayersManager.create(event.getPlayer());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (TempManager.isSetup()) {
            sendLobby(event.getPlayer());
        } else {
            if (!event.getPlayer().hasPermission("CTS.setup")) return;

            CTSSetup.init(event.getPlayer());
        }
    }

    public static void sendLobby(Player player) {
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

        if (spawnConfig.contains(CTSSetSpawn.getLobby()))
            player.teleport((Location) spawnConfig.get(CTSSetSpawn.getLobby()));

        Main.setLobbyInventory(player);
    }
}
