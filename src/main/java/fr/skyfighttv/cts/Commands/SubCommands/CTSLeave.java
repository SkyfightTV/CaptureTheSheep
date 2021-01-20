package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Game;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.GameManager;
import fr.skyfighttv.cts.Utils.WorldManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class CTSLeave {
    public static void init(Player player) {
        Game game = Game.getInstance();

        if (game.getPlayers().contains(player)
                && WorldManager.getWorlds().contains(player.getWorld())) {
            leaveGame(player, player.getWorld());
        } else {
            player.sendMessage(FileManager.getValues().get(Files.Lang).getString("CantLeave"));
        }
    }

    public static void leaveGame(Player player, World world) {
        Game game = Game.getInstance();
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

        List<Player> numberPlayersGame = GameManager.getNumberPlayers().get(world);

        numberPlayersGame.remove(player);
        GameManager.getNumberPlayers().put(world, numberPlayersGame);

        game.setLobbyInventory(player);
        game.getPlayers().remove(player);

        if (spawnConfig.contains("Lobby")) {
            player.teleport((Location) spawnConfig.get("Lobby"));
        } else {
            player.sendMessage("Please set lobby : /cts setlobby");
        }

        if (GameManager.getNumberPlayers().get(world).size() == 0)
            GameManager.stopGame(world);
    }
}
