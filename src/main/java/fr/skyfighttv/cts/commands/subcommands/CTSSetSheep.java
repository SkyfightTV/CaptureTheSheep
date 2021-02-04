package fr.skyfighttv.cts.commands.subcommands;

import fr.skyfighttv.cts.commands.CTS;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.utils.FileManager;
import fr.skyfighttv.cts.utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CTSSetSheep {
    private static final String blue = "blue";
    private static final String red = "red";

    public static void init(Player player, String sheep) {
        if (Arrays.asList(blue, red).contains(sheep.toLowerCase())) {
            YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);

            sheepConfig.set(sheep.toLowerCase(), player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Sheep);

            player.sendMessage(Language.getInstance().getSuccessSetSheep());

            CTS.verifSetup(player);
        } else {
            player.sendMessage(Language.getInstance().getFailedSetSheep());
        }
    }

    public static String getBlue() {
        return blue;
    }

    public static String getRed() {
        return red;
    }
}
