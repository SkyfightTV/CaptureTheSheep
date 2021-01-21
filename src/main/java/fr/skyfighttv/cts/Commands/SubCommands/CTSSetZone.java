package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CTSSetZone {
    public static void init(Player player, String team) {
        if (Arrays.asList("blue", "red").contains(team.toLowerCase())) {
            YamlConfiguration zoneConfig = FileManager.getValues().get(Files.Zone);

            zoneConfig.set(team.toLowerCase(), player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Zone);

            player.sendMessage(Language.getSuccessSetZone());

            CTS.verifSetup(player);
        } else {
            player.sendMessage(Language.getFailedSetZone());
        }
    }
}
