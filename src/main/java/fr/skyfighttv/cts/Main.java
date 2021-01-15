package fr.skyfighttv.cts;

import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Commands.CTSTab;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.GameManager;
import fr.skyfighttv.cts.Utils.PlayersManager;
import fr.skyfighttv.cts.Utils.WorldManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Salut ça va
 * <br/> dqdqd
 *
 * @author SkyF1ghtTV
 * @since 0.1
 */
public class Main extends JavaPlugin {

    public static String ANSI_RESET = "";
    public static String ANSI_BLACK = "";
    public static String ANSI_RED = "";
    public static String ANSI_GREEN = "";
    public static String ANSI_YELLOW = "";
    public static String ANSI_BLUE = "";
    public static String ANSI_PURPLE = "";
    public static String ANSI_CYAN = "";
    public static String ANSI_WHITE = "";
    private static Main Instance;

    private final List<Listener> listeners = new ArrayList<>(Arrays.asList(

    ));

    @Override
    public void onLoad() {
        saveDefaultConfig();
        ConfigAnnotation.loadClass(getConfig(), Settings.class);
    }

    @Override
    public void onEnable() {
        Instance = this;

        if (getConfig().getBoolean("ColorConsole")) {
            ANSI_RESET = "\u001B[0m";
            ANSI_BLACK = "\u001B[30m";
            ANSI_RED = "\u001B[31m";
            ANSI_GREEN = "\u001B[32m";
            ANSI_YELLOW = "\u001B[33m";
            ANSI_BLUE = "\u001B[34m";
            ANSI_PURPLE = "\u001B[35m";
            ANSI_CYAN = "\u001B[36m";
            ANSI_WHITE = "\u001B[37m";
        }

        System.out.println(ANSI_BLUE + "  ____  " + ANSI_WHITE + " _____  " + ANSI_RED + " ____" + ANSI_RESET);
        System.out.println(ANSI_BLUE + " / ___| " + ANSI_WHITE + "|_   _| " + ANSI_RED + "/ ___|" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "| |     " + ANSI_WHITE + "  | |   " + ANSI_RED + "\\___ \\" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "| |___  " + ANSI_WHITE + "  | |   " + ANSI_RED + " ___) |" + ANSI_RESET);
        System.out.println(ANSI_BLUE + " \\____|" + ANSI_WHITE + "  |_|   " + ANSI_RED + "|____/" + ANSI_RESET);
        System.out.println(" ");
        getLogger().info(ANSI_CYAN + "Loading current player data in progress ..." + ANSI_RESET);
        new PlayersManager();

        getLogger().info(ANSI_CYAN + "Loading configuration files in progress ..." + ANSI_RESET);
        try {
            new FileManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLogger().info(ANSI_CYAN + "Loading worlds in progress ..." + ANSI_RESET);
        new WorldManager();

        getLogger().info(ANSI_CYAN + "Loading games in progress ..." + ANSI_RESET);
        new GameManager();

        getLogger().info(ANSI_CYAN + "Finalization of the loading of the plugin in progress ..." + ANSI_RESET);
        getCommand("CaptureTheSheep").setExecutor(new CTS());
        getCommand("CaptureTheSheep").setTabCompleter(new CTSTab());

        for (Listener listener : listeners)
            getServer().getPluginManager().registerEvents(listener, this);
        getLogger().info(ANSI_CYAN + "Loading of the finalized plugin." + ANSI_RESET);
        System.out.println(" ");
    }

    public static Main getInstance() {
        return Instance;
    }
}
