package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameManager {
    private static HashMap<World, List<Player>> numberPlayers;
    private static HashMap<World, List<Player>> redTeam;
    private static HashMap<World, List<Player>> blueTeam;

    private static HashMap<Player, Integer> actionBadId;
    private static HashMap<World, Integer> startGameId;

    private static List<World> games;

    public GameManager() {
        numberPlayers = new HashMap<>();
        actionBadId = new HashMap<>();
        startGameId = new HashMap<>();
        redTeam = new HashMap<>();
        blueTeam = new HashMap<>();
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

                Location waitLoc = new Location(world
                        , ((Location) spawnConfig.get("Wait")).getX()
                        , ((Location) spawnConfig.get("Wait")).getY()
                        , ((Location) spawnConfig.get("Wait")).getZ());

                player.teleport(waitLoc);

                actionBadId.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                    if (games.contains(world)) {
                        Bukkit.getScheduler().cancelTask(actionBadId.get(player));
                        actionBadId.remove(player);
                        return;
                    }

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(numberPlayers.get(world).size() + " / " + Settings.getMaxPlayers()));
                }, 0, 2));

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
        startGameId.put(world, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (langConfig.contains("WaitTitle." + number.get())
                    || langConfig.contains("WaitSubTitle." + number.get())) {
                for (Player player : numberPlayers.get(world)) {
                    player.sendTitle(langConfig.getString("WaitTitle." + number.get()), langConfig.getString("WaitSubTitle." + number.get()));
                }
            }

            if (number.get() <= 0) {
                Bukkit.getScheduler().cancelTask(startGameId.get(world));
                startGameId.remove(world);

                games.add(world);

                YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);

                Sheep blueSheep = world.spawn((Location) sheepConfig.get("Blue"), Sheep.class);
                blueSheep.setAI(false);
                blueSheep.setAdult();
                blueSheep.setCollidable(false);
                blueSheep.setInvulnerable(true);
                blueSheep.setSheared(false);
                blueSheep.setGravity(false);
                blueSheep.setBreed(false);
                blueSheep.setColor(DyeColor.BLUE);

                Sheep redSheep = world.spawn((Location) sheepConfig.get("Red"), Sheep.class);
                redSheep.setAI(false);
                redSheep.setAdult();
                redSheep.setCollidable(false);
                redSheep.setInvulnerable(true);
                redSheep.setSheared(false);
                redSheep.setGravity(false);
                redSheep.setBreed(false);
                redSheep.setColor(DyeColor.RED);

                YamlConfiguration kitsConfig = FileManager.getValues().get(Files.Kits);

                blueTeam.putIfAbsent(world, new ArrayList<>());
                redTeam.putIfAbsent(world, new ArrayList<>());

                for (Player player : numberPlayers.get(world)) {
                    if (!blueTeam.get(world).contains(player)
                            && !redTeam.get(world).contains(player)) {
                        if (blueTeam.get(world).size() < redTeam.get(world).size()) {
                            List<Player> teamPlayers = blueTeam.get(world);
                            teamPlayers.add(player);
                            blueTeam.put(world, teamPlayers);
                        } else {
                            List<Player> teamPlayers = redTeam.get(world);
                            teamPlayers.add(player);
                            redTeam.put(world, teamPlayers);
                        }
                    }

                    teleportPlayerTeam(world, player);

                    int emplacement = 0;
                    for (Object kit : kitsConfig.getList(PlayersManager.getKit(player) + ".Content")) {
                        if (!(kit == null)) {
                            player.getInventory().setItem(emplacement, (ItemStack) kit);
                        }
                        emplacement++;
                    }
                    final List<ItemStack> itemStackList = new ArrayList<ItemStack>();
                    for (Object kit : kitsConfig.getList(PlayersManager.getKit(player) + ".ArmorContent")) {
                        itemStackList.add((ItemStack) kit);
                    }
                    ItemStack[] itemStacks = itemStackList.toArray(new ItemStack[0]);
                    player.getInventory().setArmorContents(itemStacks);
                }
            }

            number.getAndDecrement();
        }, 0, 20));
    }

    private static void teleportPlayerTeam(World world, Player player) {
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

        if (blueTeam.get(world).contains(player)) {
            Location blueLoc = new Location(world
                    , ((Location) spawnConfig.get("Blue")).getX()
                    , ((Location) spawnConfig.get("Blue")).getY()
                    , ((Location) spawnConfig.get("Blue")).getZ());

            player.teleport(blueLoc);
        } else {
            Location redLoc = new Location(world
                    , ((Location) spawnConfig.get("Red")).getX()
                    , ((Location) spawnConfig.get("Red")).getY()
                    , ((Location) spawnConfig.get("Red")).getZ());

            player.teleport(redLoc);
        }
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

    public static HashMap<World, List<Player>> getRedTeam() {
        return redTeam;
    }

    public static HashMap<World, List<Player>> getBlueTeam() {
        return blueTeam;
    }
}
