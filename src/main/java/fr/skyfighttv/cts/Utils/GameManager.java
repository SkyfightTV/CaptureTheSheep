package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Settings;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {
    private static HashMap<World, List<Player>> numberPlayers;
    private static List<World> games;

    public GameManager() {
        numberPlayers = new HashMap<>();
        games = new ArrayList<>();

        for (World world : WorldManager.getWorlds()) {
            numberPlayers.put(world, new ArrayList<>());
        }
    }

    public static boolean joinGame(Player player) {
        for (World world : numberPlayers.keySet()) {
            if (numberPlayers.get(world).size() < Settings.getMaxPlayers()
                    && !games.contains(world)) {
                YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

                List<Player> playerGame = numberPlayers.get(world);
                playerGame.add(player);

                numberPlayers.put(world, playerGame);
                games.add(world);

                player.getInventory().clear();
                player.getInventory().setArmorContents(new ItemStack[0]);

                CTS.inGamePlayers.add(player);
                player.teleport(new Location(world, spawnConfig.getLocation("Wait").getX(), spawnConfig.getLocation("Wait").getY(), spawnConfig.getLocation("Wait").getZ()));

                if (numberPlayers.get(world).size() >= Settings.getMaxPlayers() - 5) {
                    startGame(world);
                }

                return true;
            }
        }
        return false;
    }

    public static void startGame(World world) {

    }

    public static HashMap<World, List<Player>> getNumberPlayers() {
        return numberPlayers;
    }
}
