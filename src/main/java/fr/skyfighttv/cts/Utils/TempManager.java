package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class TempManager {
    private static File file;
    private static YamlConfiguration configuration;

    private static boolean isSetup = false;
    private static int WorldsNumber = FileManager.getValues().get(Files.Config).getInt("Worlds.DefaultNumber");

    public TempManager() throws IOException {
        file = new File(Main.getInstance().getDataFolder() + "/temp.yml");

        if (!file.exists()) {
            InputStream fileStream = Main.getInstance().getResource("temp.yml");
            byte[] buffer = new byte[fileStream.available()];
            fileStream.read(buffer);

            file.createNewFile();
            OutputStream outStream = new FileOutputStream(file);
            outStream.write(buffer);
        }

        configuration = YamlConfiguration.loadConfiguration(file);

        if (configuration.contains("isSetup"))
            isSetup = configuration.getBoolean("isSetup");

        if (configuration.contains("Worlds.Number")
                && configuration.getInt("Worlds.Number") > WorldsNumber)
            WorldsNumber = configuration.getInt("Worlds.Number");
    }

    public static void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public static YamlConfiguration getConfiguration() {
        return configuration;
    }

    public static boolean isSetup() {
        return isSetup;
    }

    public static int getWorldsNumber() {
        return WorldsNumber;
    }
}
