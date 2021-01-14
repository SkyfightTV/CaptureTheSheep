package fr.skyfighttv.cts.Commands.SubCommands;

import fr.skyfighttv.cts.Commands.CTS;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import fr.skyfighttv.cts.Utils.GameManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CTSPlay {
    public static void init(Player player) {
        YamlConfiguration spawnConfig = FileManager.getValues().get(Files.Spawn);
        FileManager.reload(Files.Kits);
        YamlConfiguration kitsConfig = FileManager.getValues().get(Files.Kits);
        YamlConfiguration langConfig = FileManager.getValues().get(Files.Lang);
        YamlConfiguration config = FileManager.getValues().get(Files.Config);

        if (!CTS.inGamePlayers.contains(player)) {
            if (!spawnConfig.contains("Wait")) {
                player.sendMessage("Please setup the wait spawn before !");
                return;
            }

            if (GameManager.joinGame(player)) {
                player.sendMessage(langConfig.getString("JoinGame"));
            } else {
                player.sendMessage(langConfig.getString("AllGamesFull"));
            }

            /*int emplacement = 0;
            for (Object kit : kitsConfig.getList(PlayersManager.getKit(player) + ".Content")) {
                if(!(kit == null)) {
                    player.getInventory().setItem(emplacement, (ItemStack) kit);
                }
                emplacement++;
            }
            final List<ItemStack> itemStackList = new ArrayList<>();
            for (Object kit : kitsConfig.getList(PlayersManager.getKit(player) + ".ArmorContent")) {
                itemStackList.add((ItemStack) kit);
            }
            ItemStack[] itemStacks = itemStackList.toArray(new ItemStack[0]);
            player.getInventory().setArmorContents(itemStacks);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> CTS.invinciblePlayers.remove(player), (config.getInt("Game.Invincibility") * 20));*/
        } else {
            player.sendMessage(langConfig.getString("AlreadyOnGame"));
        }
    }
}
