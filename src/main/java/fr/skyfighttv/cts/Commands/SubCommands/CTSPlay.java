package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.entity.Player;

public class CTSPlay {
    public static void init(Player player) {
        if (!GameManager.getInGamePlayers().contains(player))
            if (GameManager.joinGame(player))
                player.sendMessage(Language.getInstance().getJoinGame());
            else
                player.sendMessage(Language.getInstance().getAllGamesFull());
        else
            player.sendMessage(Language.getInstance().getAlreadyOnGame());
    }
}