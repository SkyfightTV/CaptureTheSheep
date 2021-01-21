package fr.skyfighttv.cts.Commands;

import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import fr.skyfighttv.cts.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;

public class CTSTab implements TabCompleter {
    public static List<String> a(String[] var0, String... var1) {
        return a(var0, Arrays.asList(var1));
    }

    public static List<String> a(String[] var0, Collection<?> var1) {
        String var2 = var0[var0.length - 1];
        ArrayList var3 = Lists.newArrayList();
        if (!var1.isEmpty()) {
            Iterator var4 = Iterables.transform(var1, Functions.toStringFunction()).iterator();

            while (var4.hasNext()) {
                String var5 = (String) var4.next();
                if (a(var2, var5)) {
                    var3.add(var5);
                }
            }

            if (var3.isEmpty()) {
                var4 = var1.iterator();

                while (var4.hasNext()) {
                    Object var6 = var4.next();
                    if (a(var2, (String) var6)) {
                        var3.add(String.valueOf(var6));
                    }
                }
            }
        }

        return var3;
    }

    public static boolean a(String var0, String var1) {
        return var1.regionMatches(true, 0, var0, 0, var0.length());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("CTS.staff")) {
                List<String> tab = new ArrayList<>(Arrays.asList("setSpawn", "setSheep", "setZone", "setKit", "reload"));

                return setupTab(args, tab);
            } else {
                List<String> tab = new ArrayList<>();

                return setupTab(args, tab);
            }
        } else if (args[0].equalsIgnoreCase("setSpawn")) {
            return a(args, "Lobby", "Blue", "Red", "Wait");
        } else if (args[0].equalsIgnoreCase("setSheep")) {
            return a(args, "Blue", "Red");
        }
        return new ArrayList<>();
    }

    private List<String> setupTab(String[] args, List<String> tab) {
        if (Settings.isSetup()) {
            tab.addAll(Arrays.asList("Stats", "Leave"));

            if (Settings.isCTSPlay())
                tab.add("Play");

            if (Settings.isCTSKits())
                tab.add("Kits");
        }

        Collections.reverse(tab);

        return a(args, tab);
    }
}
