package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Main;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import java.util.HashMap;

public class SheepManager {
    private static final HashMap<World, HashMap<String, Sheep>> sheepGames = new HashMap<>();
    private static final HashMap<Player, Integer> sheedPassengerId = new HashMap<>();

    public static void create(World world, String sheepTeam) {
        YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);

        sheepGames.putIfAbsent(world, new HashMap<>());

        try {
            Sheep sheep = world.spawn((Location) sheepConfig.get(sheepTeam), Sheep.class);
            sheep.setAI(false);
            sheep.setAdult();
            sheep.setCollidable(false);
            sheep.setInvulnerable(true);
            sheep.setSheared(false);
            sheep.setGravity(false);
            sheep.setBreed(false);
            sheep.setColor(DyeColor.valueOf(sheepTeam.toUpperCase()));

            sheepGames.get(world).put(sheepTeam, sheep);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void setPassenger(Player player, Sheep sheep) {
        World world = player.getWorld();

        if (sheepGames.containsKey(world)
                && sheepGames.get(world).containsValue(sheep)) {

            String sheepTeam;

            if (sheepGames.get(world).get("Blue").equals(sheep))
                sheepTeam = "Blue";
            else if (sheepGames.get(world).get("Red").equals(sheep))
                sheepTeam = "Red";
            else return;

            if ((sheepTeam.equals("Blue")
                    && GameManager.getBlueTeam().get(world).contains(player))
                    || (sheepTeam.equals("Red")
                    && GameManager.getRedTeam().get(world).contains(player)))
                return;

            if (sheep.getPassenger() == null) {
                sheep.setPassenger(player);

                sheedPassengerId.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                    if (sheep.getPassenger() == null) {
                        YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);

                        Location sheepSpawn = new Location(world
                                , ((Location) sheepConfig.get(sheepTeam)).getX()
                                , ((Location) sheepConfig.get(sheepTeam)).getY()
                                , ((Location) sheepConfig.get(sheepTeam)).getZ());

                        sheep.teleport(sheepSpawn);

                        Bukkit.getScheduler().cancelTask(sheedPassengerId.get(player));
                        sheedPassengerId.remove(player);
                    }
                }, 0, 10));
            }
        }
    }

    public static void removeWorld(World world) {
        if (sheepGames.containsKey(world)) {
            for (Sheep sheep : sheepGames.get(world).values())
                sheep.remove();

            sheepGames.remove(world);
        }
    }

    public static void removeAll() {
        for (World world : sheepGames.keySet())
            removeWorld(world);
    }

    public static HashMap<World, HashMap<String, Sheep>> getSheepGames() {
        return sheepGames;
    }
}