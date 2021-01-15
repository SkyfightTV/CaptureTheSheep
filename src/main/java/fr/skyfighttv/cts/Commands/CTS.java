package fr.skyfighttv.cts.Commands;

import fr.skyfighttv.cts.Commands.SubCommands.*;
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
import java.util.ArrayList;
import java.util.List;

public class CTS implements CommandExecutor {
    public static List<Player> inGamePlayers = new ArrayList<>();
    public static List<Player> invinciblePlayers = new ArrayList<>();

    public static void setLobbyInventory(Player player) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);
            YamlConfiguration config = FileManager.getValues().get(Files.Config);

            if (args[0].equalsIgnoreCase("play")) {
                CTSPlay.init(player);
            }
            else if (args[0].equalsIgnoreCase("leave")) {
                CTSLeave.init(player);
            }
            else if (args[0].equalsIgnoreCase("kits")) {
                if (!config.getBoolean("CTS.Kits")) {
                    player.sendMessage(langConfig.getString("CommandDisabled"));
                    return false;
                }

                CTSKits.init(player);
            }
            else if (args[0].equalsIgnoreCase("setkit")) {
                if (!player.hasPermission("CTS.setkit")) {
                    player.sendMessage(langConfig.getString("NoPermission"));
                    return false;
                }
                if (args.length == 1) {
                    player.sendMessage(langConfig.getString("NotFullCommandSetKit"));
                    return false;
                }

                CTSSetKit.init(player, args[1]);
            }
            else if (args[0].equalsIgnoreCase("setspawn")) {
                if (!player.hasPermission("CTS.setspawn")) {
                    player.sendMessage(langConfig.getString("NoPermission"));
                    return false;
                }
                if (args.length == 1) {
                    player.sendMessage(langConfig.getString("NotFullCommandSetSpawn"));
                    return false;
                }

                CTSSetSpawn.init(player, args[1]);
            } else if (args[0].equalsIgnoreCase("setsheep")) {
                if (!player.hasPermission("CTS.setsheep")) {
                    player.sendMessage(langConfig.getString("NoPermission"));
                    return false;
                }
                if (args.length == 1) {
                    player.sendMessage(langConfig.getString("NotFullCommandSetSheep"));
                    return false;
                }

                CTSSetSheep.init(player, args[1]);
            } else if (args[0].equalsIgnoreCase("reload")) {
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
            } else if (args[0].equalsIgnoreCase("stats")) {
            }
        }
        return false;
    }
}
