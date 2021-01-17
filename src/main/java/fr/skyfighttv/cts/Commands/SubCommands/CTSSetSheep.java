package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CTSSetSheep {
    public static void init(Player player, String sheep) {
        YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);
        YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);

        if (sheep.equalsIgnoreCase("Blue")) {
            sheepConfig.set("Blue", player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Sheep);

            player.sendMessage(langConfig.getString("SuccessSetSheep"));
        } else if (sheep.equalsIgnoreCase("Red")) {
            sheepConfig.set("Red", player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Sheep);

            player.sendMessage(langConfig.getString("SuccessSetSheep"));
        } else {
            player.sendMessage(langConfig.getString("FailedSetSheep"));
        }
    }
}
