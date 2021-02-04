package fr.skyfighttv.cts.utils;

import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.*;

public class ScoreBoardManager {
    private static Scoreboard scoreboard;
    private static Objective objective;
    private static List<Score> scoreList;

    private static Set<Player> autoUpdatePlayers;
    private static HashMap<Player, Integer> autoUpdatePlayersTaskId;

    public ScoreBoardManager() {
        autoUpdatePlayers = new HashSet<>();
        autoUpdatePlayersTaskId = new HashMap<>();

        final ScoreboardManager scoreBoardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreBoardManager.getNewScoreboard();
        objective = scoreboard.registerNewObjective("main", "dummy");

        objective.setDisplayName(Language.getInstance().getScoreBoardTitle());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for (String line : Language.getInstance().getScoreBoardLines()) {
            scoreList.add(objective.getScore(line
                    .replaceAll("%kill%", "0")
                    .replaceAll("%death%", "0")
                    .replaceAll("%ratio%", "0")));
            scoreList.get(scoreList.size() - 1).setScore(scoreList.size());
        }
    }

    public static void create(Player player) {
        player.setScoreboard(scoreboard);

        autoUpdatePlayers.add(player);

        autoUpdatePlayersTaskId.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (!autoUpdatePlayers.contains(player)) {
                Bukkit.getScheduler().cancelTask(autoUpdatePlayersTaskId.get(player));
                autoUpdatePlayersTaskId.remove(player);
            }

            update(player);
        }, Settings.getInstance().getGameScoreBoardUpdate() * 20L, Settings.getInstance().getGameScoreBoardUpdate() * 20L));
    }

    private static void update(Player player) {
        for (String line : Language.getInstance().getScoreBoardLines()) {
            scoreList.add(objective.getScore(line
                    .replaceAll("%kill%", "0")
                    .replaceAll("%death%", "0")
                    .replaceAll("%ratio%", "0")));
            scoreList.get(scoreList.size() - 1).setScore(scoreList.size());
        }

        player.setScoreboard(scoreboard);
    }

    public static void forceUpdate(Player player) {
        if (autoUpdatePlayers.contains(player))
            update(player);
    }

    public static void remove(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        autoUpdatePlayers.remove(player);
    }
}
