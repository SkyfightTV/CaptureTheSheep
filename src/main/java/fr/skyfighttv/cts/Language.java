package fr.skyfighttv.cts;

import fr.mrcubee.annotation.spigot.config.Config;

import java.util.Arrays;
import java.util.List;

public class Language {
    @Config(path = "NotFullCommandPlayer", color = true)
    private static final String NotFullCommandPlayer = "/cts <play/leave/kits/stats>";

    @Config(path = "NotFullCommandStaff", color = true)
    private static final String NotFullCommandStaff = "/cts <play/setspawn/setsheep/leave/setkit/kits/stats>";

    @Config(path = "NotFullCommandSetSpawn", color = true)
    private static final String NotFullCommandSetSpawn = "/cts setspawn <numéro du spawn>";

    @Config(path = "NotFullCommandSetKit", color = true)
    private static final String NotFullCommandSetKit = "/cts setkit <Nom du Kit>";

    @Config(path = "NotFullCommandSetSheep", color = true)
    private static final String NotFullCommandSetSheep = "/cts setsheep <Blue/Red>";

    @Config(path = "NotFullCommandSetZone", color = true)
    private static final String NotFullCommandSetZone = "/cts setzone <Blue/Red>";

    @Config(path = "NoPermission", color = true)
    private static final String NoPermission = "Tu n'as pas la permission d'éxecuter cette commande !";

    @Config(path = "AlreadyOnGame", color = true)
    private static final String AlreadyOnGame = "Tu es déjà en jeux !";

    @Config(path = "JoinGame", color = true)
    private static final String JoinGame = "Tu viens de rejoindre une partie";

    @Config(path = "AllGamesFull", color = true)
    private static final String AllGamesFull = "Toutes les parties sont pleines !";

    @Config(path = "NoneKits", color = true)
    private static final String NoneKits = "Il n'y a aucun kits !";

    @Config(path = "CommandDisabled", color = true)
    private static final String CommandDisabled = "Cette commande est désactivé !";

    @Config(path = "FailedSetZone", color = true)
    private static final String FailedSetZone = "Merci de rentrer un nom valide !";

    @Config(path = "FailedSetSpawn", color = true)
    private static final String FailedSetSpawn = "Merci de rentrer un nom valide !";

    @Config(path = "FailedSetSheep", color = true)
    private static final String FailedSetSheep = "Merci de rentrer un nom valide !";

    @Config(path = "FailedGameStart", color = true)
    private static final String FailedGameStart = "La partie n'a pas pu se lancer car il n'y a plus assez de joueur !";

    @Config(path = "CantLeave", color = true)
    private static final String CantLeave = "Tu es actuellement dans aucune partie !";

    @Config(path = "PlayerScore", color = true)
    private static final String PlayerScore = "%player% vient de marqué !";

    @Config(path = "SuccessSetSpawn", color = true)
    private static final String SuccessSetSpawn = "Tu viens d'ajouter un spawn !";

    @Config(path = "SuccessSetSheep", color = true)
    private static final String SuccessSetSheep = "Tu viens d'ajouter un sheep !";

    @Config(path = "SuccessSetKit", color = true)
    private static final String SuccessSetKit = "Tu viens d'ajouter un kit !";

    @Config(path = "SuccessSetZone", color = true)
    private static final String SuccessSetZone = "Tu viens de set une zone";

    @Config(path = "SuccessSetLobby", color = true)
    private static final String SuccessSetLobby = "Tu viens de set le nouveau lobby !";

    @Config(path = "SuccessSelectKit", color = true)
    private static final String SuccessSelectKit = "Tu viens avec succès de sélectionner ton kit !";

    @Config(path = "SuccessReload", color = true)
    private static final String SuccessReload = "Tu viens de reload le plugin !";

    @Config(path = "SuccessChooseTeam", color = true)
    private static final String SuccessChooseTeam = "Tu viens de rejoindre l'équipe %team% !";

    @Config(path = "TeamsName.Red", color = true)
    private static final String TeamsNameRed = "Rouge";

    @Config(path = "TeamsName.Blue", color = true)
    private static final String TeamsNameBlue = "Bleu";

    @Config(path = "StatsMessage", color = true)
    private static final List<String> StatsMessage = Arrays.asList(
            "---------------------",
            "Morts : %death%",
            "Kills : %kill%",
            "Ratio : %ratio%",
            "---------------------");

    public static String getNotFullCommandPlayer() {
        return NotFullCommandPlayer;
    }

    public static String getNotFullCommandStaff() {
        return NotFullCommandStaff;
    }

    public static String getNotFullCommandSetSpawn() {
        return NotFullCommandSetSpawn;
    }

    public static String getNotFullCommandSetKit() {
        return NotFullCommandSetKit;
    }

    public static String getNotFullCommandSetSheep() {
        return NotFullCommandSetSheep;
    }

    public static String getNotFullCommandSetZone() {
        return NotFullCommandSetZone;
    }

    public static String getNoPermission() {
        return NoPermission;
    }

    public static String getAlreadyOnGame() {
        return AlreadyOnGame;
    }

    public static String getJoinGame() {
        return JoinGame;
    }

    public static String getAllGamesFull() {
        return AllGamesFull;
    }

    public static String getNoneKits() {
        return NoneKits;
    }

    public static String getCommandDisabled() {
        return CommandDisabled;
    }

    public static String getFailedSetZone() {
        return FailedSetZone;
    }

    public static String getFailedSetSpawn() {
        return FailedSetSpawn;
    }

    public static String getFailedSetSheep() {
        return FailedSetSheep;
    }

    public static String getFailedGameStart() {
        return FailedGameStart;
    }

    public static String getCantLeave() {
        return CantLeave;
    }

    public static String getPlayerScore() {
        return PlayerScore;
    }

    public static String getSuccessSetSpawn() {
        return SuccessSetSpawn;
    }

    public static String getSuccessSetSheep() {
        return SuccessSetSheep;
    }

    public static String getSuccessSetKit() {
        return SuccessSetKit;
    }

    public static String getSuccessSetZone() {
        return SuccessSetZone;
    }

    public static String getSuccessSetLobby() {
        return SuccessSetLobby;
    }

    public static String getSuccessSelectKit() {
        return SuccessSelectKit;
    }

    public static String getSuccessReload() {
        return SuccessReload;
    }

    public static String getSuccessChooseTeam() {
        return SuccessChooseTeam;
    }

    public static String getTeamsNameRed() {
        return TeamsNameRed;
    }

    public static String getTeamsNameBlue() {
        return TeamsNameBlue;
    }

    public static List<String> getStatsMessage() {
        return StatsMessage;
    }
}
