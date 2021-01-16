package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameManager {
    private static HashMap<World, List<Player>> numberPlayers;
    private static HashMap<World, HashMap<Player, String>> teamPlayers;
    private static List<World> games;

    public GameManager() {
        numberPlayers = new HashMap<>();
        teamPlayers = new HashMap<>();
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

                player.getInventory().clear();
                player.getInventory().setArmorContents(new ItemStack[0]);

                CTS.inGamePlayers.add(player);
                player.teleport(new Location(world, spawnConfig.getLocation("Wait").getX(), spawnConfig.getLocation("Wait").getY(), spawnConfig.getLocation("Wait").getZ()));

                if (numberPlayers.get(world).size() >= Settings.getMaxPlayers() - 5)
                    startGame(world);

                return true;
            }
        }
        return false;
    }

    public static void startGame(World world) {
        YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);


        AtomicInteger number = new AtomicInteger(10);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (langConfig.contains("WaitTitle." + number.get())
                    || langConfig.contains("WaitSubTitle." + number.get())) {
                for (Player player : numberPlayers.get(world)) {
                    player.sendTitle(langConfig.getString("WaitTitle." + number.get()), langConfig.getString("WaitSubTitle." + number.get()));
                }
            }

            if (number.get() <= 0) {
                games.add(world);


            }

            number.getAndDecrement();
        }, 0, 20);
    }

    public static void stopGame(World world) {

        games.add(world);
    }

    public static HashMap<World, List<Player>> getNumberPlayers() {
        return numberPlayers;
    }

    public static List<World> getGames() {
        return games;
    }

    public static HashMap<World, HashMap<Player, String>> getTeamPlayers() {
        return teamPlayers;
    }
}
