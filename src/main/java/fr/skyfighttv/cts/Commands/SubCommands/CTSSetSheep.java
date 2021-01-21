package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CTSSetSheep {
    public static void init(Player player, String sheep) {
        if (Arrays.asList("blue", "red").contains(sheep.toLowerCase())) {
            YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);

            sheepConfig.set(sheep.toLowerCase(), player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Sheep);

            player.sendMessage(Language.getSuccessSetSheep());

            CTS.verifSetup(player);
        } else {
            player.sendMessage(Language.getFailedSetSheep());
        }
    }
}
