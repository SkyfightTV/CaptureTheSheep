package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorldManager {
    private static List<World> worlds;

    public WorldManager() {
        worlds = new ArrayList<>();

        reload();
    }

    public static void reload() {
        YamlConfiguration config = FileManager.getValues().get(Files.Config);

        int createdNumber = 0;
        int number = 0;
        for (int i = 1; i <= Settings.getNumberOfWorld(); i++) {
            String worldName = Settings.getWorldTitle() + i;
            File world = new File(worldName);

            if (!world.exists()) {
                WorldCreator worldCreator = new WorldCreator(worldName);

                try {
                    worldCreator.copy(Bukkit.getWorld(Settings.getCopiedWorldName()));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                worlds.add(worldCreator.createWorld());
                createdNumber++;
            } else {
                worlds.add(Bukkit.getWorld(worldName));
            }
            number++;
        }
        System.out.println(Main.ANSI_GREEN + createdNumber + " worlds created and " + number + " worlds loaded." + Main.ANSI_RESET);
    }

    public static List<World> getWorlds() {
        return worlds;
    }
}
