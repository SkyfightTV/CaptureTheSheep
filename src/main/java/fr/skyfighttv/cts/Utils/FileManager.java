package fr.skyfighttv.cts.Utils;

import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;

public class FileManager {
    private static HashMap<Files, YamlConfiguration> values;

    public FileManager() throws IOException {
        values = new HashMap<>();
        new Settings();
        new Language();

        int number = 0;
        int createdNumber = 0;
        for (Files files : Files.values()) {
            File fileRessource = new File(Main.getInstance().getDataFolder() + "/" + files.getName() + ".yml");
            if (!fileRessource.exists()) {
                InputStream fileStream = Main.getInstance().getResource(files.getName() + ".yml");
                byte[] buffer = new byte[fileStream.available()];
                fileStream.read(buffer);

                fileRessource.createNewFile();
                OutputStream outStream = new FileOutputStream(fileRessource);
                outStream.write(buffer);
                createdNumber++;
            }
            values.put(files, YamlConfiguration.loadConfiguration(fileRessource));
            number++;
        }
        System.out.println(Main.ANSI_GREEN + createdNumber + " files created and " + number + " files loaded." + Main.ANSI_RESET);

        loadClass();

        new TempManager();
    }

    public static void loadClass() {
        ConfigAnnotation.loadClass(values.get(Files.Config), Settings.class);
        ConfigAnnotation.loadClass(values.get(Files.Lang), Language.class);
    }

    public static void save(Files files) {
        File file = new File(Main.getInstance().getDataFolder() + "/" + files.getName() + ".yml");
        if (!file.exists()) {
            System.out.println("ERROR : File " + files.getName() + " not found !");
            return;
        }
        try {
            values.get(files).save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        values.put(files, YamlConfiguration.loadConfiguration(file));

        loadClass();
    }

    public static void saveAll() {
        for (Files files : Files.values())
            save(files);
    }

    public static void reload(Files files) {
        File file = new File(Main.getInstance().getDataFolder() + "/" + files.name() + ".yml");

        if (!file.exists()) {
            System.out.println("ERROR : File " + files.name() + " not found !");
            return;
        }

        values.put(files, YamlConfiguration.loadConfiguration(file));

        loadClass();
    }

    public static void reloadAll() {
        for (Files files : Files.values())
            reload(files);
    }

    public static HashMap<Files, YamlConfiguration> getValues() {
        return values;
    }
}
