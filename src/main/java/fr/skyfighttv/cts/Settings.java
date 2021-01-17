package fr.skyfighttv.cts;

import fr.mrcubee.annotation.spigot.config.Config;

public class Settings {

    @Config(path = "Worlds.Number")
    private static final Integer numberOfWorld = 2;

    @Config(path = "Worlds.Title")
    private static final String worldTitle = "CTS-";

    @Config(path = "Worlds.Copy")
    private static final String copiedWorldName = "world";

    @Config(path = "Game.MaxPlayers")
    private static final Integer MaxPlayers = 2;

    public static Integer getMaxPlayers() {
        return MaxPlayers;
    }

    public static Integer getNumberOfWorld() {
        return numberOfWorld;
    }

    public static String getWorldTitle() {
        return worldTitle;
    }

    public static String getCopiedWorldName() {
        return copiedWorldName;
    }
}
