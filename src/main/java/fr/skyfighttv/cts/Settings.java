package fr.skyfighttv.cts;

import fr.mrcubee.annotation.spigot.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    @Config(path = "IsSetup")
    private static final boolean IsSetup = false;

    @Config(path = "DebugMode")
    private static final boolean DebugMode = false;

    @Config(path = "ColorConsole")
    private static final boolean ColorConsole = true;

    @Config(path = "Worlds.Number")
    private static final int WorldsNumber = 2;

    @Config(path = "Worlds.Increase")
    private static final int WorldsIncrease = 2;

    @Config(path = "Worlds.Title")
    private static final String WorldsTitle = "CTS-";

    @Config(path = "Worlds.Copy")
    private static final String WorldsCopy = "world";

    @Config(path = "CTS.Play")
    private static final boolean CTSPlay = true;

    @Config(path = "CTS.Kits")
    private static final boolean CTSKits = true;

    @Config(path = "Game.MaxPlayers")
    private static final int GameMaxPlayers = 16;

    @Config(path = "Game.MinPlayers")
    private static final int GameMinPlayers = 14;

    @Config(path = "Game.Invincibility")
    private static final int GameInvincibility = 5;

    @Config(path = "Game.TeamSizeZone")
    private static final int GameTeamSizeZone = 5;

    @Config(path = "Game.EndGameTime")
    private static final int GameEndGameTime = 10;

    @Config(path = "Game.WaitTime")
    private static final int GameWaitTime = 30;

    @Config(path = "GUI.Kits.Title")
    private static final String GUIKitsTitle = "Menu des kits";

    @Config(path = "GUI.Kits.Size")
    private static final int GUIKitsSize = 27;

    @Config(path = "GUI.Teams.Title")
    private static final String GUITeamsTitle = "Choisi ta team";

    @Config(path = "GUI.Teams.Size")
    private static final int GUITeamsSize = 9;

    @Config(path = "AutoDelete.Blocks")
    private static final List<String> AutoDeleteBlocks = new ArrayList<>();

    @Config(path = "AutoDelete.Time")
    private static final int AutoDeleteTime = 10;

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
