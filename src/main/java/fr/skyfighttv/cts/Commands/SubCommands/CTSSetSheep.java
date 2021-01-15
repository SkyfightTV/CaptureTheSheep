package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CTSSetSheep {
    public static void init(Player player, String sheep) {
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);
        YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);

        if (sheep.equalsIgnoreCase("Blue")) {
            spawnConfig.set("BlueSheep", player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Spawn);

            player.sendMessage(langConfig.getString("SuccessSetSheep"));
        } else if (sheep.equalsIgnoreCase("Red")) {
            spawnConfig.set("RedSheep", player.getLocation().getBlock().getLocation());
            FileManager.save(Files.Spawn);

            player.sendMessage(langConfig.getString("SuccessSetSheep"));
        } else {
            player.sendMessage(langConfig.getString("FailedSetSheep"));
        }
    }
}
