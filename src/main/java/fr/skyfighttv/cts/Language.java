package fr.skyfighttv.cts;

import fr.mrcubee.annotation.spigot.config.Config;

import java.util.Arrays;
import java.util.List;

public class Language {
    private static Language Instance;

    @Config(path = "NotFullCommandPlayer", color = true)
    private String NotFullCommandPlayer = "/cts <play/leave/kits/stats>";

    @Config(path = "NotFullCommandStaff", color = true)
    private String NotFullCommandStaff = "/cts <play/setspawn/setsheep/leave/setkit/kits/stats>";

    @Config(path = "NotFullCommandSetSpawn", color = true)
    private String NotFullCommandSetSpawn = "/cts setspawn <numéro du spawn>";

    @Config(path = "NotFullCommandSetKit", color = true)
    private String NotFullCommandSetKit = "/cts setkit <Nom du Kit>";

    @Config(path = "NotFullCommandSetSheep", color = true)
    private String NotFullCommandSetSheep = "/cts setsheep <Blue/Red>";

    @Config(path = "NotFullCommandSetZone", color = true)
    private String NotFullCommandSetZone = "/cts setzone <Blue/Red>";

    @Config(path = "NoPermission", color = true)
    private String NoPermission = "Tu n'as pas la permission d'éxecuter cette commande !";

    @Config(path = "AlreadyOnGame", color = true)
    private String AlreadyOnGame = "Tu es déjà en jeux !";

    @Config(path = "JoinGame", color = true)
    private String JoinGame = "Tu viens de rejoindre une partie";

    @Config(path = "AllGamesFull", color = true)
    private String AllGamesFull = "Toutes les parties sont pleines !";

    @Config(path = "CommandDisabled", color = true)
    private String CommandDisabled = "Cette commande est désactivé !";

    @Config(path = "FailedSetZone", color = true)
    private String FailedSetZone = "Merci de rentrer un nom valide !";

    @Config(path = "FailedSetSpawn", color = true)
    private String FailedSetSpawn = "Merci de rentrer un nom valide !";

    @Config(path = "FailedSetSheep", color = true)
    private String FailedSetSheep = "Merci de rentrer un nom valide !";

    @Config(path = "FailedGameStart", color = true)
    private String FailedGameStart = "La partie n'a pas pu se lancer car il n'y a plus assez de joueur !";

    @Config(path = "CantLeave", color = true)
    private String CantLeave = "Tu es actuellement dans aucune partie !";

    @Config(path = "PlayerScore", color = true)
    private String PlayerScore = "%player% vient de marqué !";

    @Config(path = "SuccessSetSpawn", color = true)
    private String SuccessSetSpawn = "Tu viens d'ajouter un spawn !";

    @Config(path = "SuccessSetSheep", color = true)
    private String SuccessSetSheep = "Tu viens d'ajouter un sheep !";

    @Config(path = "SuccessSetKit", color = true)
    private String SuccessSetKit = "Tu viens d'ajouter un kit !";

    @Config(path = "SuccessSetZone", color = true)
    private String SuccessSetZone = "Tu viens de set une zone";

    @Config(path = "SuccessSelectKit", color = true)
    private String SuccessSelectKit = "Tu viens avec succès de sélectionner ton kit !";

    @Config(path = "SuccessReload", color = true)
    private String SuccessReload = "Tu viens de reload le plugin !";

    @Config(path = "SuccessChooseTeam", color = true)
    private String SuccessChooseTeam = "Tu viens de rejoindre l'équipe %team% !";

    @Config(path = "TeamsName.Red", color = true)
    private String TeamsNameRed = "Rouge";

    @Config(path = "TeamsName.Blue", color = true)
    private String TeamsNameBlue = "Bleu";

    @Config(path = "StatsMessage", color = true)
    private List<String> StatsMessage = Arrays.asList(
            "---------------------",
            "Morts : %death%",
            "Kills : %kill%",
            "Ratio : %ratio%",
            "---------------------");

    @Config(path = "ScoreBoard.Title", color = true)
    private String ScoreBoardTitle = "Title";

    @Config(path = "ScoreBoard.Lines", color = true)
    private List<String> ScoreBoardLines = Arrays.asList(
            "---------------------",
            "Morts : %death%",
            "Kills : %kill%",
            "Ratio : %ratio%",
            "---------------------");

    public Language() {
        Instance = this;
    }

    public String getNotFullCommandPlayer() {
        return NotFullCommandPlayer;
    }

    public String getNotFullCommandStaff() {
        return NotFullCommandStaff;
    }

    public String getNotFullCommandSetSpawn() {
        return NotFullCommandSetSpawn;
    }

    public String getNotFullCommandSetKit() {
        return NotFullCommandSetKit;
    }

    public String getNotFullCommandSetSheep() {
        return NotFullCommandSetSheep;
    }

    public String getNotFullCommandSetZone() {
        return NotFullCommandSetZone;
    }

    public String getNoPermission() {
        return NoPermission;
    }

    public String getAlreadyOnGame() {
        return AlreadyOnGame;
    }

    public String getJoinGame() {
        return JoinGame;
    }

    public String getAllGamesFull() {
        return AllGamesFull;
    }

    public String getCommandDisabled() {
        return CommandDisabled;
    }

    public String getFailedSetZone() {
        return FailedSetZone;
    }

    public String getFailedSetSpawn() {
        return FailedSetSpawn;
    }

    public String getFailedSetSheep() {
        return FailedSetSheep;
    }

    public String getFailedGameStart() {
        return FailedGameStart;
    }

    public String getCantLeave() {
        return CantLeave;
    }

    public String getPlayerScore() {
        return PlayerScore;
    }

    public String getSuccessSetSpawn() {
        return SuccessSetSpawn;
    }

    public String getSuccessSetSheep() {
        return SuccessSetSheep;
    }

    public String getSuccessSetKit() {
        return SuccessSetKit;
    }

    public String getSuccessSetZone() {
        return SuccessSetZone;
    }

    public String getSuccessSelectKit() {
        return SuccessSelectKit;
    }

    public String getSuccessReload() {
        return SuccessReload;
    }

    public String getSuccessChooseTeam() {
        return SuccessChooseTeam;
    }

    public String getTeamsNameRed() {
        return TeamsNameRed;
    }

    public String getTeamsNameBlue() {
        return TeamsNameBlue;
    }

    public List<String> getStatsMessage() {
        return StatsMessage;
    }

    public String getScoreBoardTitle() {
        return ScoreBoardTitle;
    }

    public List<String> getScoreBoardLines() {
        return ScoreBoardLines;
    }

    public static Language getInstance() {
        return Instance;
    }
}
