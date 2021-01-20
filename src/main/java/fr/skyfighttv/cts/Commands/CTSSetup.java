package fr.skyfighttv.cts.Commands;

import fr.skyfighttv.cts.Commands.SubCommands.*;
import fr.skyfighttv.cts.Game;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.PlayersManager;
import fr.skyfighttv.cts.Utils.WorldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CTSSetup implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Game game;
        YamlConfiguration langConfig;
        YamlConfiguration config;
        Player player;
        String errorMessage;

        if (!(sender instanceof Player))
            return false;
        game = Game.getInstance();
        langConfig = game.getLangConfig();
        config = game.getConfig();
        player = (Player) sender;
        if (args.length == 0) {
            errorMessage = player.hasPermission("CTS.staff") ? langConfig.getString("NotFullCommandStaff")
                    : langConfig.getString("NotFullCommandPlayer");
            if (errorMessage != null)
                player.sendMessage(errorMessage);
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "play":
                CTSPlay.init(player);
                break;
            case "leave":
                CTSLeave.init(player);
                break;
            case "kits":
                if (!config.getBoolean("CTS.Kits")) {
                    errorMessage = langConfig.getString("NoPermission");
                    if (errorMessage != null)
                        player.sendMessage(errorMessage);
                    break;
                }
                CTSKits.init(player);
                break;
            case "setspawn":
                if (!player.hasPermission("CTS.setspawn")) {
                    errorMessage = langConfig.getString("NoPermission");
                    if (errorMessage != null)
                        player.sendMessage(errorMessage);
                    break;
                }
                if (args.length < 2) {
                    errorMessage = langConfig.getString("NotFullCommandSetSpawn");
                    if (errorMessage != null)
                        player.sendMessage(errorMessage);
                }
                CTSSetSpawn.init(player, args[1]);
                break;
            case "setsheep":
                if (!player.hasPermission("CTS.setsheep")) {
                    errorMessage = langConfig.getString("NoPermission");
                    if (errorMessage != null)
                        player.sendMessage(errorMessage);
                    break;
                }
                if (args.length < 2) {
                    errorMessage = langConfig.getString("NotFullCommandSetSheep");
                    if (errorMessage != null)
                        player.sendMessage(errorMessage);
                    break;
                }
                CTSSetSheep.init(player, args[1]);
                break;
            case "setzone":
                if (!player.hasPermission("CTS.setzone")) {
                    errorMessage = langConfig.getString("NoPermission");
                    if (errorMessage != null)
                        player.sendMessage(errorMessage);
                    break;
                }
                if (args.length < 2) {
                    errorMessage = langConfig.getString("NotFullCommandSetZone");
                    if (errorMessage != null)
                        player.sendMessage(errorMessage);
                    break;
                }
                CTSSetZone.init(player, args[1]);
                break;
            case "reload":
                if (!player.hasPermission("CTS.reload")) {
                    errorMessage = langConfig.getString("NoPermission");
                    if (errorMessage != null)
                        player.sendMessage(errorMessage);
                    break;
                }
                FileManager.reloadAll();
                try {
                    PlayersManager.saveAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WorldManager.reload();
                errorMessage = langConfig.getString("SuccessReload");
                if (errorMessage != null)
                    player.sendMessage(errorMessage);
                break;
            case "stats":
                CTSStats.init(player);
                break;
            default:
                errorMessage = player.hasPermission("CTS.staff") ? langConfig.getString("NotFullCommandStaff")
                        : langConfig.getString("NotFullCommandPlayer");
                if (errorMessage != null)
                    player.sendMessage(errorMessage);
                break;
        }
        return false;
    }
}
