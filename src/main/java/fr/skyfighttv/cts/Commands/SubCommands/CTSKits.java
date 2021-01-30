package fr.skyfighttv.cts.Commands.SubCommands;

import fr.ChadOW.cinventory.CInventory;
import fr.ChadOW.cinventory.CItem;
import fr.ChadOW.cinventory.ItemCreator;
import fr.skyfighttv.cts.Language;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.PlayersManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CTSKits {
    public static void init(Player player) {
        YamlConfiguration kitsConfig = FileManager.getValues().get(Files.Kits);
        YamlConfiguration config = FileManager.getValues().get(Files.Config);

        CInventory inventory = new CInventory(config.getInt("GUI.Kits.Size"), config.getString("GUI.Kits.Title"));

        for (String kits : kitsConfig.getKeys(false)) {
            CItem item = new CItem(new ItemCreator(Material.getMaterial(kitsConfig.getString(kits + ".ItemMenu")), 0)
                    .setName(kits)
                    .setLores(kitsConfig.getStringList(kits + ".Lore")));
            item.addEvent((cInventory, cItem, player1, clickContext) -> {
                if (kitsConfig.getKeys(false).contains(cItem.getName())) {
                    if (kitsConfig.getString(cItem.getName() + ".Permission").equals("N/A")
                            || player.hasPermission(kitsConfig.getString(cItem.getName() + ".Permission"))) {
                        try {
                            PlayersManager.setKit(player, cItem.getName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        player.sendMessage(Language.getInstance().getSuccessSelectKit());

                        inventory.close(player);
                    }
                }
            });
            inventory.addElement(item);
        }
        player.closeInventory();
        inventory.open(player);
    }
}
