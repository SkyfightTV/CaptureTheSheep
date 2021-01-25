package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.GameManager;
import fr.skyfighttv.cts.Utils.WorldManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;

public class CTSLeave {
    public static void init(Player player) {
        if (GameManager.getInGamePlayers().contains(player)
                && WorldManager.getWorlds().contains(player.getWorld())) {
            leaveGame(player, player.getWorld());
        } else {
            player.sendMessage(Language.getInstance().getCantLeave());
        }
    }

    public static void leaveGame(Player player, World world) {
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

        Set<Player> numberPlayersGame = GameManager.getNumberPlayers().get(world);
        numberPlayersGame.remove(player);
        GameManager.getNumberPlayers().put(world, numberPlayersGame);

        Main.setLobbyInventory(player);
        GameManager.getInGamePlayers().remove(player);

        if (spawnConfig.contains("lobby")) {
            player.teleport((Location) spawnConfig.get("lobby"));
        }

        if (GameManager.getNumberPlayers().get(world).size() == 0)
            GameManager.stopGame(world);
    }
}
