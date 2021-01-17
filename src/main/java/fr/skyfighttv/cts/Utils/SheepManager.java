package fr.skyfighttv.cts.Utils;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Sheep;

import java.util.HashMap;

public class SheepManager {
    private static final HashMap<World, HashMap<String, Sheep>> sheepGames = new HashMap<>();

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