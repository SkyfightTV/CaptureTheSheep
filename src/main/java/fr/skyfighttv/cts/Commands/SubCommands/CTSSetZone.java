package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CTSSetZone {
    public static void init(Player player, String team) {
        YamlConfiguration zoneConfig = FileManager.getValues().get(Files.Zone);
        YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);

        if (team.equalsIgnoreCase("Blue")) {
            zoneConfig.set("Blue", player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Zone);

            player.sendMessage(langConfig.getString("SuccessSetZone"));
        } else if (team.equalsIgnoreCase("Red")) {
            zoneConfig.set("Red", player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Zone);

            player.sendMessage(langConfig.getString("SuccessSetZone"));
        } else {
            player.sendMessage(langConfig.getString("FailedSetZone"));
        }
    }
}
