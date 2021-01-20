package fr.skyfighttv.cts.Commands;

import fr.skyfighttv.cts.Commands.SubCommands.CTSSetup;
import fr.skyfighttv.cts.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CTS implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        YamlConfiguration langConfig;
        Player player;
        String langMessage;

        if (!(sender instanceof Player))
            return false;
        player = (Player) sender;
        langConfig = Game.getInstance().getLangConfig();
        if (args.length == 0) {
            langMessage = player.hasPermission("CTS.staff") ? langConfig.getString("NotFullCommandStaff")
                    : langConfig.getString("NotFullCommandPlayer");
            if (langMessage != null)
                player.sendMessage(langMessage);
            return true;
        }
        if (!args[0].equalsIgnoreCase("setup")) {
            langMessage = langConfig.getString("SetupBefore");
            if (langMessage != null)
                player.sendMessage(langMessage);
            return true;
        }
        if (!player.hasPermission("CTS.setup")) {
            langMessage = langConfig.getString("NoPermission");
            if (langMessage != null)
                player.sendMessage(langMessage);
            return true;
        }
        CTSSetup.init(player);
        return false;
    }
}
