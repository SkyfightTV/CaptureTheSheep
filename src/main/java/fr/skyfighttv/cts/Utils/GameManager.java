package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Commands.SubCommands.CTSLeave;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameManager {
    private static Set<Player> inGamePlayers;
    private static Set<Player> invinciblePlayers;

    private static HashMap<World, Set<Player>> numberPlayers;
    private static HashMap<World, Set<Player>> redTeam;
    private static HashMap<World, Set<Player>> blueTeam;

    private static HashMap<Player, Integer> actionBadId;
    private static HashMap<World, Integer> startGameId;

    private static Set<World> games;

    public GameManager() {
        inGamePlayers = new HashSet<>();
        invinciblePlayers = new HashSet<>();
        numberPlayers = new HashMap<>();
        actionBadId = new HashMap<>();
        startGameId = new HashMap<>();
        redTeam = new HashMap<>();
        blueTeam = new HashMap<>();
        games = new HashSet<>();

        for (World world : WorldManager.getWorlds())
            numberPlayers.put(world, new HashSet<>());
    }

    public static boolean joinGame(Player player) {
        List<World> worldList = WorldManager.getWorlds();
        Collections.reverse(worldList);

        System.out.println(worldList);

        for (World world : worldList) {
            if (world == null) continue;

            if (numberPlayers.get(world).size() < Settings.getInstance().getGameMaxPlayers()
                    && !games.contains(world)) {
                YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

                Set<Player> playerGame = numberPlayers.get(world);
                playerGame.add(player);

                numberPlayers.put(world, playerGame);

                player.getInventory().clear();
                player.getInventory().setArmorContents(new ItemStack[0]);

                inGamePlayers.add(player);

                Location waitLoc = new Location(world
                        , ((Location) spawnConfig.get("wait")).getX()
                        , ((Location) spawnConfig.get("wait")).getY()
                        , ((Location) spawnConfig.get("wait")).getZ());

                try {
                    player.teleport(waitLoc);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }

                actionBadId.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                    if (games.contains(world)
                            || !world.equals(player.getWorld())) {
                        Bukkit.getScheduler().cancelTask(actionBadId.get(player));
                        actionBadId.remove(player);
                        return;
                    }

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(numberPlayers.get(world).size() + " / " + Settings.getInstance().getGameMaxPlayers()));
                }, 0, 2));

                if (numberPlayers.get(world).size()
                        >= Settings.getInstance().getGameMaxPlayers()
                        - (Settings.getInstance().getGameMaxPlayers()
                        - Settings.getInstance().getGameMinPlayers()))
                    startGame(world);

                return true;
            }
        }
        return false;
    }

    public static void startGame(World world) {
        YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);

        AtomicInteger number = new AtomicInteger(Settings.getInstance().getGameWaitTime());
        startGameId.put(world, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (numberPlayers.get(world).size() < Settings.getInstance().getGameMaxPlayers() - (Settings.getInstance().getGameMaxPlayers() - Settings.getInstance().getGameMinPlayers())) {
                Bukkit.getScheduler().cancelTask(startGameId.get(world));
                startGameId.remove(world);

                for (Player player : numberPlayers.get(world))
                    player.sendMessage(Language.getInstance().getFailedGameStart());
            }

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

                if (WorldManager.getWorlds().size() == games.size()) {
                    TempManager.getConfiguration().set("Worlds.Number", TempManager.getWorldsNumber() + Settings.getInstance().getWorldsIncrease());
                    TempManager.save();
                }

                SheepManager.create(world, "blue");
                SheepManager.create(world, "red");

                blueTeam.putIfAbsent(world, new HashSet<>());
                redTeam.putIfAbsent(world, new HashSet<>());

                for (Player player : numberPlayers.get(world)) {
                    if (!blueTeam.get(world).contains(player)
                            && !redTeam.get(world).contains(player)) {
                        if (blueTeam.get(world).size() < redTeam.get(world).size()) {
                            Set<Player> teamPlayers = blueTeam.get(world);
                            teamPlayers.add(player);
                            blueTeam.put(world, teamPlayers);
                        } else {
                            Set<Player> teamPlayers = redTeam.get(world);
                            teamPlayers.add(player);
                            redTeam.put(world, teamPlayers);
                        }
                    }

                    teleportPlayerTeam(world, player);

                    givePlayerKit(player);
                }
            }

            number.getAndDecrement();
        }, 0, 20));
    }

    public static void endGame(World world) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            for (Player player : numberPlayers.get(world))
                CTSLeave.leaveGame(player, world);
        }, Settings.getInstance().getGameEndGameTime());
    }

    public static void givePlayerKit(Player player) {
        YamlConfiguration kitsConfig = FileManager.getValues().get(Files.Kits);

        int emplacement = 0;
        for (Object kit : kitsConfig.getList(PlayersManager.getKit(player) + ".Content")) {
            if (!(kit == null)) {
                player.getInventory().setItem(emplacement, (ItemStack) kit);
            }
            emplacement++;
        }
        final List<ItemStack> itemStackList = new ArrayList<>();
        for (Object kit : kitsConfig.getList(PlayersManager.getKit(player) + ".ArmorContent")) {
            itemStackList.add((ItemStack) kit);
        }
        ItemStack[] itemStacks = itemStackList.toArray(new ItemStack[0]);
        player.getInventory().setArmorContents(itemStacks);
    }

    public static void teleportPlayerTeam(World world, Player player) {
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

        if (blueTeam.get(world).contains(player)) {
            Location blueLoc = new Location(world
                    , ((Location) spawnConfig.get("blue")).getX()
                    , ((Location) spawnConfig.get("blue")).getY()
                    , ((Location) spawnConfig.get("blue")).getZ());

            player.teleport(blueLoc);
        } else {
            Location redLoc = new Location(world
                    , ((Location) spawnConfig.get("red")).getX()
                    , ((Location) spawnConfig.get("red")).getY()
                    , ((Location) spawnConfig.get("red")).getZ());

            player.teleport(redLoc);
        }
    }

    public static void stopGame(World world) {
        games.remove(world);
        numberPlayers.put(world, new HashSet<>());
        redTeam.remove(world);
        blueTeam.remove(world);

        SheepManager.removeWorld(world);
    }

    public static HashMap<World, Set<Player>> getNumberPlayers() {
        return numberPlayers;
    }

    public static Set<World> getGames() {
        return games;
    }

    public static HashMap<World, Set<Player>> getRedTeam() {
        return redTeam;
    }

    public static HashMap<World, Set<Player>> getBlueTeam() {
        return blueTeam;
    }

    public static Set<Player> getInGamePlayers() {
        return inGamePlayers;
    }

    public static Set<Player> getInvinciblePlayers() {
        return invinciblePlayers;
    }
}
