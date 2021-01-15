package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.PlayersManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CTSStats {
    public static void init(Player player) {
        YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);
        StringBuilder message = new StringBuilder();

        for (String msg : langConfig.getStringList("StatsMessage")) {
            message.append(msg
                    .replaceAll("%death%", PlayersManager.getDeaths(player) + "")
                    .replaceAll("%kill%", PlayersManager.getKills(player) + "")
                    .replaceAll("%ratio%", String.valueOf((PlayersManager.getKills(player) + 1) / (PlayersManager.getDeaths(player) + 1))));
            message.append("\n");
        }

        String messageSend = message.toString();
        player.sendMessage(messageSend.replace("\n", ""));
    }
}
