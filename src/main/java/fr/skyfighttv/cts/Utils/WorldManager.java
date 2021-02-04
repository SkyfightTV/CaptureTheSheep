package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorldManager {
    private static List<World> worlds;

    public WorldManager() {
        worlds = new ArrayList<>();

        reload();

        worlds.removeIf(Objects::isNull);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            if (worlds.isEmpty()) GameManager.load();
        }, 20);
    }

    public static void reload() {
        int createdNumber = 0;
        int number = 0;
        for (int i = 1; i <= TempManager.getWorldsNumber(); i++) {
            String worldName = Settings.getInstance().getWorldsTitle() + i;

            WorldCreator worldCreator = new WorldCreator(worldName);

            try {
                worldCreator.copy(Objects.requireNonNull(Bukkit.getWorld(Settings.getInstance().getWorldsCopy())));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            worlds.add(worldCreator.createWorld());
            createdNumber++;

            number++;
        }
        System.out.println(Main.ANSI_GREEN + createdNumber + " worlds created and " + number + " worlds loaded." + Main.ANSI_RESET);
    }

    public static List<World> getWorlds() {
        return worlds;
    }
}
