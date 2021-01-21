package fr.skyfighttv.cts;

import fr.mrcubee.annotation.spigot.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    private static Settings Instance;

    @Config(path = "IsSetup")
    private boolean IsSetup = false;

    @Config(path = "DebugMode")
    private boolean DebugMode = false;

    @Config(path = "ColorConsole")
    private boolean ColorConsole = true;

    @Config(path = "Worlds.Number")
    private int WorldsNumber = 2;

    @Config(path = "Worlds.Increase")
    private int WorldsIncrease = 2;

    @Config(path = "Worlds.Title")
    private String WorldsTitle = "CTS-";

    @Config(path = "Worlds.Copy")
    private String WorldsCopy = "world";

    @Config(path = "CTS.Play")
    private boolean CTSPlay = true;

    @Config(path = "CTS.Kits")
    private boolean CTSKits = true;

    @Config(path = "Game.MaxPlayers")
    private int GameMaxPlayers = 16;

    @Config(path = "Game.MinPlayers")
    private int GameMinPlayers = 14;

    @Config(path = "Game.Invincibility")
    private int GameInvincibility = 5;

    @Config(path = "Game.TeamSizeZone")
    private int GameTeamSizeZone = 5;

    @Config(path = "Game.EndGameTime")
    private int GameEndGameTime = 10;

    @Config(path = "Game.WaitTime")
    private int GameWaitTime = 30;

    @Config(path = "GUI.Kits.Title")
    private String GUIKitsTitle = "Menu des kits";

    @Config(path = "GUI.Kits.Size")
    private int GUIKitsSize = 27;

    @Config(path = "GUI.Teams.Title")
    private String GUITeamsTitle = "Choisi ta team";

    @Config(path = "GUI.Teams.Size")
    private int GUITeamsSize = 9;

    @Config(path = "AutoDelete.Blocks")
    private List<String> AutoDeleteBlocks = new ArrayList<>();

    @Config(path = "AutoDelete.Time")
    private int AutoDeleteTime = 10;

    public Settings() {
        Instance = this;
    }

    public static Settings getInstance() {
        return Instance;
    }

    public boolean isSetup() {
        return IsSetup;
    }

    public boolean isDebugMode() {
        return DebugMode;
    }

    public boolean isColorConsole() {
        return ColorConsole;
    }

    public int getWorldsNumber() {
        return WorldsNumber;
    }

    public int getWorldsIncrease() {
        return WorldsIncrease;
    }

    public String getWorldsTitle() {
        return WorldsTitle;
    }

    public String getWorldsCopy() {
        return WorldsCopy;
    }

    public boolean isCTSPlay() {
        return CTSPlay;
    }

    public boolean isCTSKits() {
        return CTSKits;
    }

    public int getGameMaxPlayers() {
        return GameMaxPlayers;
    }

    public int getGameMinPlayers() {
        return GameMinPlayers;
    }

    public int getGameInvincibility() {
        return GameInvincibility;
    }

    public int getGameTeamSizeZone() {
        return GameTeamSizeZone;
    }

    public int getGameEndGameTime() {
        return GameEndGameTime;
    }

    public int getGameWaitTime() {
        return GameWaitTime;
    }

    public String getGUIKitsTitle() {
        return GUIKitsTitle;
    }

    public int getGUIKitsSize() {
        return GUIKitsSize;
    }

    public String getGUITeamsTitle() {
        return GUITeamsTitle;
    }

    public int getGUITeamsSize() {
        return GUITeamsSize;
    }

    public List<String> getAutoDeleteBlocks() {
        return AutoDeleteBlocks;
    }

    public int getAutoDeleteTime() {
        return AutoDeleteTime;
    }
}
