package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CTSSetSpawn {
    public static void init(Player player, String spawn) {
        if (Arrays.asList("lobby", "blue", "red", "wait").contains(spawn.toLowerCase())) {
            YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

            spawnConfig.set(spawn.toLowerCase(), player.getLocation());
            FileManager.save(Files.Spawn);

            player.sendMessage(Language.getSuccessSetSpawn());
        } else {
            player.sendMessage(Language.getFailedSetSpawn());
        }
    }
}
