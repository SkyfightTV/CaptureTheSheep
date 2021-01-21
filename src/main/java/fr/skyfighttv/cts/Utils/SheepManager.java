package fr.skyfighttv.cts.Utils;

import fr.skyfighttv.cts.Main;
import fr.skyfighttv.cts.Settings;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class SheepManager {
    private static HashMap<World, HashMap<String, Sheep>> sheepGames;
    private static HashMap<Player, Integer> sheedPassengerId;

    public SheepManager() {
        sheedPassengerId = new HashMap<>();
        sheepGames = new HashMap<>();
    }

    public static void create(World world, String sheepTeam) {
        YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);

        sheepGames.putIfAbsent(world, new HashMap<>());

        try {
            Sheep sheep = world.spawn((Location) sheepConfig.get(sheepTeam), Sheep.class);
            sheep.setAI(false);
            sheep.setAdult();
            sheep.setCollidable(false);
            sheep.setInvulnerable(true);
            sheep.setSheared(false);
            sheep.setGravity(false);
            sheep.setBreed(false);
            sheep.setColor(DyeColor.valueOf(sheepTeam.toUpperCase()));

            sheepGames.get(world).put(sheepTeam, sheep);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void setPassenger(Player player, Sheep sheep) {
        World world = player.getWorld();

        if (sheepGames.containsKey(world)
                && sheepGames.get(world).containsValue(sheep)) {

            String sheepTeam;
            String zoneObjective;

            if (sheepGames.get(world).get("Blue").equals(sheep)) {
                sheepTeam = "Blue";
                zoneObjective = "Red";
            } else if (sheepGames.get(world).get("Red").equals(sheep)) {
                sheepTeam = "Red";
                zoneObjective = "Blue";
            } else return;

            if ((sheepTeam.equals("Blue")
                    && GameManager.getBlueTeam().get(world).contains(player))
                    || (sheepTeam.equals("Red")
                    && GameManager.getRedTeam().get(world).contains(player)))
                return;

            if (sheep.getPassenger() == null) {
                sheep.setPassenger(player);

                YamlConfiguration zoneConfig = FileManager.getValues().get(Files.Zone);

                Location zoneLoc = (Location) zoneConfig.get(zoneObjective);
                assert zoneLoc != null;

                int size = Settings.getGUITeamsSize();

                int x = zoneLoc.getBlockX() + size;
                int _x = zoneLoc.getBlockX() - size;
                int y = zoneLoc.getBlockY() + size;
                int _y = zoneLoc.getBlockY() - size;
                int z = zoneLoc.getBlockZ() + size;
                int _z = zoneLoc.getBlockZ() - size;

                sheedPassengerId.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                    if (sheep.getPassenger() == null
                            || sheep.isDead()) {
                        YamlConfiguration sheepConfig = FileManager.getValues().get(Files.Sheep);

                        Location sheepSpawn = new Location(world
                                , ((Location) sheepConfig.get(sheepTeam)).getX()
                                , ((Location) sheepConfig.get(sheepTeam)).getY()
                                , ((Location) sheepConfig.get(sheepTeam)).getZ());

                        sheep.teleport(sheepSpawn);

                        Bukkit.getScheduler().cancelTask(sheedPassengerId.get(player));
                        sheedPassengerId.remove(player);
                    }

                    if (x > sheep.getLocation().getBlockX()
                            && sheep.getLocation().getBlockX() > _x
                            && y > sheep.getLocation().getBlockY()
                            && sheep.getLocation().getBlockY() > _y
                            && z > sheep.getLocation().getBlockZ()
                            && sheep.getLocation().getBlockZ() > _z) {
                        sheep.remove();

                        GameManager.endGame(world);
                    }

                    Vector vector = player.getLocation().getDirection();
                    vector.setY(0);

                    sheep.getLocation().add(vector.toLocation(world));
                }, 0, 10));
            }
        }
    }

    public static void removeWorld(World world) {
        if (sheepGames.containsKey(world)) {
            for (Sheep sheep : sheepGames.get(world).values())
                sheep.remove();

            sheepGames.remove(world);
        }
    }

    public static void removeAll() {
        if (!sheepGames.isEmpty())
            for (World world : sheepGames.keySet())
                removeWorld(world);
    }

    public static HashMap<World, HashMap<String, Sheep>> getSheepGames() {
        return sheepGames;
    }
}