package fr.skyfighttv.cts.Commands;

import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.PlayersManager;
import fr.skyfighttv.cts.Utils.WorldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CTS implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);

            if (args[0].equalsIgnoreCase("play")) {

            }
            else if (args[0].equalsIgnoreCase("leave")) {

            }
            else if (args[0].equalsIgnoreCase("kits")) {

            }
            else if (args[0].equalsIgnoreCase("setkit")) {

            }
            else if (args[0].equalsIgnoreCase("setspawn")) {

            }
            else if (args[0].equalsIgnoreCase("reload")) {
                if (!player.hasPermission("CTS.reload")) {
                    player.sendMessage(langConfig.getString("NoPermission"));
                    return false;
                }

                FileManager.reloadAll();
                try {
                    PlayersManager.saveAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WorldManager.reload();

                player.sendMessage(langConfig.getString("SuccessReload"));
            }
            else if (args[0].equalsIgnoreCase("stats")) {
            }
        }
        return false;
    }
}
