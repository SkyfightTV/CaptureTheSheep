package fr.skyfighttv.cts.Commands;

import fr.skyfighttv.cts.Commands.SubCommands.*;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Settings;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.PlayersManager;
import fr.skyfighttv.cts.Utils.WorldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CTS implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Settings.isSetup()) {
                if (args.length == 0) {
                    if (player.hasPermission("CTS.staff"))
                        player.sendMessage(Language.getNotFullCommandStaff());
                    else
                        player.sendMessage(Language.getNotFullCommandPlayer());
                    return false;
                }

                switch (args[0].toLowerCase()) {
                    case "play":
                        if (!Settings.isCTSPlay()) {
                            player.sendMessage(Language.getCommandDisabled());
                            return false;
                        }

                        CTSPlay.init(player);
                    case "leave":
                        CTSLeave.init(player);
                    case "kits":
                        if (!Settings.isCTSKits()) {
                            player.sendMessage(Language.getCommandDisabled());
                            return false;
                        }

                        CTSKits.init(player);
                    case "setkit":
                        if (!player.hasPermission("CTS.setkit")) {
                            player.sendMessage(Language.getNoPermission());
                            return false;
                        }
                        if (args.length == 1) {
                            player.sendMessage(Language.getNotFullCommandSetKit());
                            return false;
                        }

                        CTSSetKit.init(player, args[1]);
                    case "setspawn":
                        if (!player.hasPermission("CTS.setspawn")) {
                            player.sendMessage(Language.getNoPermission());
                            return false;
                        }
                        if (args.length == 1) {
                            player.sendMessage(Language.getNotFullCommandSetSpawn());
                            return false;
                        }

                        CTSSetSpawn.init(player, args[1]);
                    case "setsheep":
                        if (!player.hasPermission("CTS.setsheep")) {
                            player.sendMessage(Language.getNoPermission());
                            return false;
                        }
                        if (args.length == 1) {
                            player.sendMessage(Language.getNotFullCommandSetSheep());
                            return false;
                        }

                        CTSSetSheep.init(player, args[1]);
                    case "setzone":
                        if (!player.hasPermission("CTS.setzone")) {
                            player.sendMessage(Language.getNoPermission());
                            return false;
                        }
                        if (args.length == 1) {
                            player.sendMessage(Language.getNotFullCommandSetZone());
                            return false;
                        }

                        CTSSetZone.init(player, args[1]);
                    case "reload":
                        if (!player.hasPermission("CTS.reload")) {
                            player.sendMessage(Language.getNoPermission());
                            return false;
                        }

                        FileManager.reloadAll();
                        try {
                            PlayersManager.saveAll();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        WorldManager.reload();

                        player.sendMessage(Language.getSuccessReload());
                    case "stats":
                        CTSStats.init(player);
                    default:
                        if (player.hasPermission("CTS.staff"))
                            player.sendMessage(Language.getNotFullCommandStaff());
                        else
                            player.sendMessage(Language.getNotFullCommandPlayer());
                }
            } else {
                if (!player.hasPermission("CTS.setup")) {
                    player.sendMessage(Language.getNoPermission());
                    return false;
                }

                CTSSetup.init(player);
            }
        }
        return false;
    }
}
