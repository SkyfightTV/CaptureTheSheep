package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.GameManager;
import fr.skyfighttv.cts.Utils.WorldManager;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class CTSLeave {
    public static void init(Player player) {
        if (CTS.inGamePlayers.contains(player)
                && WorldManager.getWorlds().contains(player.getWorld())) {
            leaveGame(player, player.getWorld());
        }
    }

    public static void leaveGame(Player player, World world) {
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

        List<Player> numberPlayersGame = GameManager.getNumberPlayers().get(world);
        numberPlayersGame.remove(player);
        GameManager.getNumberPlayers().put(world, numberPlayersGame);

        if (GameManager.getNumberPlayers().get(world).size() == 0)
            GameManager.stopGame(world);

        CTS.setLobbyInventory(player);
        CTS.inGamePlayers.remove(player);

        if (spawnConfig.contains("Lobby")) {
            player.teleport(spawnConfig.getLocation("Lobby"));
        } else {
            player.sendMessage("Please set lobby : /cts setlobby");
        }
    }
}
