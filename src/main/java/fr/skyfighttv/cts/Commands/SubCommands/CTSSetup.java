package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CTSSetup {
    private static final String doneColor = "§a";
    private static final String undoneColor = "§c";

    public static void init(Player player) {
        YamlConfiguration config = FileManager.getValues().get(Files.Config);
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);
        YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);
        YamlConfiguration kitsConfig = FileManager.getValues().get(Files.Kits);
        YamlConfiguration zoneConfig = FileManager.getValues().get(Files.Zone);

        if (!Settings.isSetup()) {
            StringBuilder message = new StringBuilder();

            String spawnLobby = booleanToColor(spawnConfig.contains("Lobby"));
            String spawnBlue = booleanToColor(spawnConfig.contains("Blue"));
            String spawnRed = booleanToColor(spawnConfig.contains("Red"));
            String spawnWait = booleanToColor(spawnConfig.contains("Wait"));

            String zoneBlue = booleanToColor(zoneConfig.contains("Blue"));
            String zoneRed = booleanToColor(zoneConfig.contains("Red"));

            String sheepBlue = booleanToColor(sheepConfig.contains("Blue"));
            String sheepRed = booleanToColor(sheepConfig.contains("Red"));

            String kitDefault = booleanToColor(kitsConfig.contains("default"));

            message.append("------------ CTS Setup ------------")
                    .append("\n");

            if (spawnBlue.equals(doneColor)
                    && spawnLobby.equals(doneColor)
                    && spawnRed.equals(doneColor)
                    && spawnWait.equals(doneColor)
                    && zoneBlue.equals(doneColor)
                    && zoneRed.equals(doneColor)
                    && sheepBlue.equals(doneColor)
                    && sheepRed.equals(doneColor)
                    && kitDefault.equals(doneColor)) {

                message.append("§aPlugin initialization completed.");

                config.set("IsSetup", true);
                FileManager.save(Files.Config);

                Bukkit.getPluginManager().disablePlugin(Main.getInstance());
                Bukkit.getPluginManager().enablePlugin(Main.getInstance());
            } else {
                message.append("1) Places spawns (/cts setspawn) : ")
                        .append(spawnBlue)
                        .append("Blue§f, ")
                        .append(spawnRed)
                        .append("Red§f, ")
                        .append(spawnWait)
                        .append("Wait §fand ")
                        .append(spawnLobby)
                        .append("Lobby§f.")
                        .append("\n");

                message.append("2) Set the zones (/cts setzone) : ")
                        .append(zoneBlue)
                        .append("Blue §fand ")
                        .append(zoneRed)
                        .append("Red§f.")
                        .append("\n");

                message.append("3) Set up the sheep (/cts setsheep) : ")
                        .append(sheepBlue)
                        .append("Blue §fand ")
                        .append(sheepRed)
                        .append("Red§f.")
                        .append("\n");

                message.append("4) Set the default kit (/cts setkit) : ")
                        .append(kitDefault)
                        .append("default§f.")
                        .append("\n");
            }

            message.append("-----------------------------------");

            player.sendMessage(message.toString());
        }
    }

    private static String booleanToColor(Boolean a) {
        if (a)
            return doneColor;
        else
            return undoneColor;
    }
}
