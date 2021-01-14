package fr.skyfighttv.cts;

import fr.mrcubee.annotation.spigot.config.Config;

public class Settings {

    @Config(path = "Worlds.Number")
    private static final int numberOfWorld = 2;

    @Config(path = "Worlds.Title")
    private static String worldTitle;

    @Config(path = "Worlds.Copy")
    private static String copiedWorldName;

    public static int getNumberOfWorld() {
        return numberOfWorld;
    }

    public static String getWorldTitle() {
        return worldTitle;
    }

    public static String getCopiedWorldName() {
        return copiedWorldName;
    }
}
