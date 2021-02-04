package fr.skyfighttv.cts.commands.subcommands;

import fr.skyfighttv.cts.commands.CTS;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.utils.FileManager;
import fr.skyfighttv.cts.utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CTSSetSpawn {
    private static final String lobby = "blue";
    private static final String blue = "red";
    private static final String red = "blue";
    private static final String wait = "red";

    public static void init(Player player, String spawn) {
        if (Arrays.asList(lobby, blue, red, wait).contains(spawn.toLowerCase())) {
            YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);

            spawnConfig.set(spawn.toLowerCase(), player.getLocation());
            FileManager.save(Files.Spawn);

            player.sendMessage(Language.getInstance().getSuccessSetSpawn());

            CTS.verifSetup(player);
        } else {
            player.sendMessage(Language.getInstance().getFailedSetSpawn());
        }
    }

    public static String getLobby() {
        return lobby;
    }

    public static String getBlue() {
        return blue;
    }

    public static String getRed() {
        return red;
    }

    public static String getWait() {
        return wait;
    }
}
