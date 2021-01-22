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
    public static boolean verifSetup(Player player) {
        if (!Settings.getInstance().isSetup()) {
            if (!player.hasPermission("CTS.setup")) {
                player.sendMessage(Language.getInstance().getNoPermission());
                return true;
            }

            CTSSetup.init(player);
            return true;
        }
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                notFullCommand(player);
                return false;
            }

            switch (args[0].toLowerCase()) {
                case "play":
                    if (!Settings.getInstance().isCTSPlay()) {
                        player.sendMessage(Language.getInstance().getCommandDisabled());
                        return false;
                    }

                    if (verifSetup(player)) return false;

                    CTSPlay.init(player);
                    break;
                case "leave":
                    if (verifSetup(player)) return false;

                    CTSLeave.init(player);
                    break;
                case "kits":
                    if (!Settings.getInstance().isCTSKits()) {
                        player.sendMessage(Language.getInstance().getCommandDisabled());
                        return false;
                    }

                    if (verifSetup(player)) return false;

                    CTSKits.init(player);
                    break;
                case "setkit":
                    if (!player.hasPermission("CTS.setkit")) {
                        player.sendMessage(Language.getInstance().getNoPermission());
                        return false;
                    }
                    if (args.length == 1) {
                        player.sendMessage(Language.getInstance().getNotFullCommandSetKit());
                        return false;
                    }

                    CTSSetKit.init(player, args[1]);
                    break;
                case "setspawn":
                    if (!player.hasPermission("CTS.setspawn")) {
                        player.sendMessage(Language.getInstance().getNoPermission());
                        return false;
                    }
                    if (args.length == 1) {
                        player.sendMessage(Language.getInstance().getNotFullCommandSetSpawn());
                        return false;
                    }

                    CTSSetSpawn.init(player, args[1]);
                    break;
                case "setsheep":
                    if (!player.hasPermission("CTS.setsheep")) {
                        player.sendMessage(Language.getInstance().getNoPermission());
                        return false;
                    }
                    if (args.length == 1) {
                        player.sendMessage(Language.getInstance().getNotFullCommandSetSheep());
                        return false;
                    }

                    CTSSetSheep.init(player, args[1]);
                    break;
                case "setzone":
                    if (!player.hasPermission("CTS.setzone")) {
                        player.sendMessage(Language.getInstance().getNoPermission());
                        return false;
                    }
                    if (args.length == 1) {
                        player.sendMessage(Language.getInstance().getNotFullCommandSetZone());
                        return false;
                    }

                    CTSSetZone.init(player, args[1]);
                    break;
                case "reload":
                    if (!player.hasPermission("CTS.reload")) {
                        player.sendMessage(Language.getInstance().getNoPermission());
                        return false;
                    }

                    FileManager.reloadAll();
                    try {
                        PlayersManager.saveAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    WorldManager.reload();

                    player.sendMessage(Language.getInstance().getSuccessReload());
                    break;
                case "stats":
                    if (verifSetup(player)) return false;

                    CTSStats.init(player);
                    break;
                default:
                    notFullCommand(player);
                    return false;
            }
        }
        return false;
    }

    private void notFullCommand(Player player) {
        if (!Settings.getInstance().isSetup()) {
            if (!player.hasPermission("CTS.setup")) {
                player.sendMessage(Language.getInstance().getNoPermission());
                return;
            }

            CTSSetup.init(player);
        } else {
            if (player.hasPermission("CTS.staff"))
                player.sendMessage(Language.getInstance().getNotFullCommandStaff());
            else
                player.sendMessage(Language.getInstance().getNotFullCommandPlayer());
        }
    }
}
