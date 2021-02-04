package fr.skyfighttv.cts.commands.subcommands;

import fr.skyfighttv.cts.commands.CTS;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.utils.FileManager;
import fr.skyfighttv.cts.utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CTSSetZone {
    private static final String blue = "red";
    private static final String red = "blue";

    public static void init(Player player, String team) {
        if (Arrays.asList(blue, red).contains(team.toLowerCase())) {
            YamlConfiguration zoneConfig = FileManager.getValues().get(Files.Zone);

            zoneConfig.set(team.toLowerCase(), player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Zone);

            player.sendMessage(Language.getInstance().getSuccessSetZone());

            CTS.verifSetup(player);
        } else {
            player.sendMessage(Language.getInstance().getFailedSetZone());
        }
    }

    public static String getBlue() {
        return blue;
    }

    public static String getRed() {
        return red;
    }
}
