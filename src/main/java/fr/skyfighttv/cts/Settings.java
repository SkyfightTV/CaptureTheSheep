package fr.skyfighttv.cts;

import fr.mrcubee.annotation.spigot.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    @Config(path = "IsSetup")
    private static boolean IsSetup = false;

    @Config(path = "DebugMode")
    private static boolean DebugMode = false;

    @Config(path = "ColorConsole")
    private static boolean ColorConsole = true;

    @Config(path = "Worlds.Number")
    private static int WorldsNumber = 2;

    @Config(path = "Worlds.Increase")
    private static int WorldsIncrease = 2;

    @Config(path = "Worlds.Title")
    private static String WorldsTitle = "CTS-";

    @Config(path = "Worlds.Copy")
    private static String WorldsCopy = "world";

    @Config(path = "CTS.Play")
    private static boolean CTSPlay = true;

    @Config(path = "CTS.Kits")
    private static boolean CTSKits = true;

    @Config(path = "Game.MaxPlayers")
    private static int GameMaxPlayers = 16;

    @Config(path = "Game.MinPlayers")
    private static int GameMinPlayers = 14;

    @Config(path = "Game.Invincibility")
    private static int GameInvincibility = 5;

    @Config(path = "Game.TeamSizeZone")
    private static int GameTeamSizeZone = 5;

    @Config(path = "Game.EndGameTime")
    private static int GameEndGameTime = 10;

    @Config(path = "Game.WaitTime")
    private static int GameWaitTime = 30;

    @Config(path = "GUI.Kits.Title")
    private static String GUIKitsTitle = "Menu des kits";

    @Config(path = "GUI.Kits.Size")
    private static int GUIKitsSize = 27;

    @Config(path = "GUI.Teams.Title")
    private static String GUITeamsTitle = "Choisi ta team";

    @Config(path = "GUI.Teams.Size")
    private static int GUITeamsSize = 9;

    @Config(path = "AutoDelete.Blocks")
    private static List<String> AutoDeleteBlocks = new ArrayList<>();

    @Config(path = "AutoDelete.Time")
    private static int AutoDeleteTime = 10;

    public static boolean isSetup() {
        return IsSetup;
    }

    public static boolean isDebugMode() {
        return DebugMode;
    }

    public static boolean isColorConsole() {
        return ColorConsole;
    }

    public static int getWorldsNumber() {
        return WorldsNumber;
    }

    public static int getWorldsIncrease() {
        return WorldsIncrease;
    }

    public static String getWorldsTitle() {
        return WorldsTitle;
    }

    public static String getWorldsCopy() {
        return WorldsCopy;
    }

    public static boolean isCTSPlay() {
        return CTSPlay;
    }

    public static boolean isCTSKits() {
        return CTSKits;
    }

    public static int getGameMaxPlayers() {
        return GameMaxPlayers;
    }

    public static int getGameMinPlayers() {
        return GameMinPlayers;
    }

    public static int getGameInvincibility() {
        return GameInvincibility;
    }

    public static int getGameTeamSizeZone() {
        return GameTeamSizeZone;
    }

    public static int getGameEndGameTime() {
        return GameEndGameTime;
    }

    public static int getGameWaitTime() {
        return GameWaitTime;
    }

    public static String getGUIKitsTitle() {
        return GUIKitsTitle;
    }

    public static int getGUIKitsSize() {
        return GUIKitsSize;
    }

    public static String getGUITeamsTitle() {
        return GUITeamsTitle;
    }

    public static int getGUITeamsSize() {
        return GUITeamsSize;
    }

    public static List<String> getAutoDeleteBlocks() {
        return AutoDeleteBlocks;
    }

    public static int getAutoDeleteTime() {
        return AutoDeleteTime;
    }
}
