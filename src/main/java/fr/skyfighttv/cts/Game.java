package fr.skyfighttv.cts;

import fr.ChadOW.cinventory.citem.ItemCreator;
import fr.skyfighttv.cts.Utils.FileManager;
import fr.skyfighttv.cts.Utils.Files;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private static Game instance;

    private YamlConfiguration langConfig;
    private YamlConfiguration config;
    private Set<Player> players;
    private Set<Player> invinciblePlayers;

    protected Game() {
        this.langConfig = FileManager.getValues().get(Files.Lang);
        this.config = FileManager.getValues().get(Files.Config);
        this.players = new HashSet<Player>();
        this.invinciblePlayers = new HashSet<Player>();
        Game.instance = this;
    }

    public void setLobbyInventory(Player player) {
        if (player == null)
            return;
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[0]);
        for (String items : this.config.getConfigurationSection("LobbyItems").getKeys(false)) {
            player.getInventory().setItem(this.config.getInt("LobbyItems." + items + ".Location"),
                    new ItemCreator(Material.getMaterial(this.config.getString("LobbyItems." + items + ".Material")), 0)
                            .setName(this.config.getString("LobbyItems." + items + ".Title"))
                            .setLores(this.config.getStringList("LobbyItems." + items + ".Lore"))
                            .getItem());
        }
    }

    public YamlConfiguration getLangConfig() {
        return this.langConfig;
    }

    public YamlConfiguration getConfig() {
        return this.config;
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public Set<Player> getInvinciblePlayers() {
        return this.invinciblePlayers;
    }

    public static Game getInstance() {
        return instance;
    }
}
