package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CTSSetSpawn {
    public static void init(Player player, String spawn) {
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);
        YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);

        if (spawn.equalsIgnoreCase("Lobby")) {
            spawnConfig.set("Lobby", player.getLocation());
            FileManager.save(Files.Spawn);

            player.sendMessage(langConfig.getString("SuccessSetSpawn"));
        } else if (spawn.equalsIgnoreCase("Blue")) {
            spawnConfig.set("Blue", player.getLocation());
            FileManager.save(Files.Spawn);

            player.sendMessage(langConfig.getString("SuccessSetSpawn"));
        } else if (spawn.equalsIgnoreCase("Red")) {
            spawnConfig.set("Red", player.getLocation());
            FileManager.save(Files.Spawn);

            player.sendMessage(langConfig.getString("SuccessSetSpawn"));
        } else if (spawn.equalsIgnoreCase("Wait")) {
            spawnConfig.set("Wait", player.getLocation());
            FileManager.save(Files.Spawn);

            player.sendMessage(langConfig.getString("SuccessSetSpawn"));
        } else {
            player.sendMessage(langConfig.getString("FailedSetSpawn"));
        }
    }
}
