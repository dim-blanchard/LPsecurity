package fr.loirelique.lpsecurity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import fr.loirelique.lpsecurity.Command.CommandBanish;
import fr.loirelique.lpsecurity.Command.CommandLogin;
import fr.loirelique.lpsecurity.Command.CommandRegister;
import fr.loirelique.lpsecurity.String.ConfigBdd;
import fr.loirelique.lpsecurity.String.ConfigMessage;

/**
 * Information sur la class!LPSECURITY
 *
 */

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;
    private HashMap<String, Integer> listTacheRegister = new HashMap<String, Integer>();
    private HashMap<String, Integer> listTacheLogin = new HashMap<String, Integer>();
    private HashMap<String, Integer> listTacheSpawnBlock = new HashMap<String, Integer>();

    private HashMap<String, Player> listPlayer = new HashMap<String, Player>();
    private static HashMap<String, ArrayList<String>> listIpPlayer = new HashMap<String, ArrayList<String>>();
    private static HashMap<String, Integer> listOnlinePlayer = new HashMap<String, Integer>();

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        // Commandes
        CommandExecutor commandRegister = new CommandRegister();
        getCommand("register").setExecutor(commandRegister);
        CommandExecutor commandLogin = new CommandLogin();
        getCommand("login").setExecutor(commandLogin);
        CommandExecutor commandBanish = new CommandBanish();
        getCommand("banish").setExecutor(commandBanish);

        System.out.println("Chargement plugin LPsecurity... ===> OK");
    }

    @Override
    public void onDisable() {
        System.out.println("Arret du plugin LPsecurity... ===> OK");
    }

    @EventHandler
    public void playerBeforeJoinServer(AsyncPlayerPreLoginEvent p_event) {

        String uuid = getHash(p_event.getName());

        String ip = p_event.getAddress().getHostAddress();

        int ban = 0;

        long startTime = System.nanoTime();

        getIpOfPlayerLoginAndTestIp(ip, uuid, p_event);

        getPlayerIsOnline(uuid, p_event);

        try (Connection connection_register = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_register
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setString(1, uuid);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        ban = resultat_requete_select.getInt("ban");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ban == 1) {
            p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    ConfigMessage.getKickBan());
        }

        long endTime = System.nanoTime();
        System.out.println("Test de vitesse before : " + (endTime - startTime) * Math.pow(10, -6) + " ms");

    }

    /**
     * EVENT PLAYER JOIN EVENT
     */

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent p_event) {
        final Player p = p_event.getPlayer();

        String uuid = getUuidHash(p);
        String uuidfrombdd = "";

        long startTime = System.nanoTime();

        addListPlayer(uuid, p);

        try (Connection connection_select = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            // -2 Fait une ou plusieure requete connection au jeux

            String requet_Select_sql1 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement1_select = connection_select
                    .prepareStatement(requet_Select_sql1)) {
                statement1_select.setObject(1, uuid);

                try (ResultSet resultat_requete_select = statement1_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        uuidfrombdd = resultat_requete_select.getString("uuid");

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (uuid.equals(uuidfrombdd)) {
            setTaskBlockSpawn(p);
            setTaskLoginTime(p);
            ConfigMessage.sendLogin(p);

           /*  try (Connection connection_update = DriverManager.getConnection(
                    ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                            + "/"
                            + ConfigBdd.getDatabase1()
                            + "?characterEncoding=latin1&useConfigs=maxPerformance",
                    ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                String requet_Update_sql1 = "UPDATE " + ConfigBdd.getTable1() + " SET online=? WHERE uuid=?";
                try (PreparedStatement statement2_select = connection_update.prepareStatement(requet_Update_sql1)) {
                    statement2_select.setInt(1, 1);
                    statement2_select.setString(2, uuid);
                    statement2_select.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } */

        } else {
            setTaskBlockSpawn(p);
            setTaskRegisterTime(p);
            ConfigMessage.sendRegister(p);
        }
        long endTime = System.nanoTime();
        System.out.println("Test de vitesse Join : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
    }

    @EventHandler
    public void playerQuitServer(PlayerQuitEvent p_event) {
        final Player p = p_event.getPlayer();
        String uuid = getUuidHash(p);
        long startTime = System.nanoTime();
       /*  try (Connection connection_update = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                        + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() + " SET online=? WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_update.prepareStatement(requet_Update_sql2)) {
                statement2_select.setInt(1, 0);
                statement2_select.setString(2, uuid);
                statement2_select.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
 */
        if (listTacheRegister.get(uuid) != null) {
            Bukkit.getScheduler().cancelTask(getTaskRegisterTime(p));
            getTaskRegisterTimeRemove(p);

        }
        if (listTacheLogin.get(uuid) != null) {
            Bukkit.getScheduler().cancelTask(getTaskLoginTime(p));
            getTaskLoginTimeRemove(p);
        }
        if (listTacheSpawnBlock.get(uuid) != null) {
            Bukkit.getScheduler().cancelTask(getTaskBlockSpawn(p));
            getTaskBlockSpawnRemove(p);
        }
        if (listPlayer.get(uuid) != null) {
            getListPlayerRemove(uuid);
        }
        getListIpPlayerRemove(p.getAddress().getHostString(), uuid);
        getListOnlinePlayerRemove(uuid);

        long endTime = System.nanoTime();
        System.out.println("Test de vitesse quit : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
    }

    /**
     * GETTER ET SETTER DE TACHE
     */

    public static void getIpOfPlayerLoginAndTestIp(String ip, String uuid, AsyncPlayerPreLoginEvent p_event) {
        try {
            if (listIpPlayer.get(ip) != null) {
                if (getSizeOfListIpPlayer(ip) == 2) {
                    p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                            "TROP IP");
                } else {
                    (listIpPlayer.get(ip)).add(uuid);
                }

            } else {
                listIpPlayer.put(ip, new ArrayList<String>());
                (listIpPlayer.get(ip)).add(uuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getSizeOfListIpPlayer(String ip) {

        int taille = listIpPlayer.get(ip).size();
        return taille;
    }

    public static void getListIpPlayerRemove(String ip, String uuid) {
        listIpPlayer.get(ip).remove(uuid);
    }

    public static void getPlayerIsOnline(String uuid, AsyncPlayerPreLoginEvent p_event) {
        try {
            if (listOnlinePlayer.get(uuid) != null) {
                p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                        ConfigMessage.getKickOnline());
            } else {
                listOnlinePlayer.put(uuid, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getListOnlinePlayerRemove(String uuid) {
        listOnlinePlayer.remove(uuid);
    }

    public Integer getTaskRegisterTime(Player p) {
        return listTacheRegister.get(getUuidHash(p));
    }

    public void getTaskRegisterTimeRemove(Player p) {
        listTacheRegister.remove(getUuidHash(p));
    }

    public void setTaskRegisterTime(Player p) {

        BukkitTask tache = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

            int time_run1 = ConfigMessage.getRegisterTime();

            @Override
            public void run() {

                System.out.println(" Kick Register Actif Temps: " + time_run1);

                if (time_run1 == 0) {

                    p.kickPlayer(ConfigMessage.getKickOvertime());

                }
                time_run1--;
            }

        }, 20, 20);

        int idtache = tache.getTaskId();

        listTacheRegister.put(getUuidHash(p), idtache);

    }

    public Integer getTaskLoginTime(Player p) {
        return listTacheLogin.get(getUuidHash(p));
    }

    public void getTaskLoginTimeRemove(Player p) {
        listTacheLogin.remove(getUuidHash(p));
    }

    public void setTaskLoginTime(Player p) {

        BukkitTask tache = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

            int time_run1 = ConfigMessage.getLoginTime();

            @Override
            public void run() {

                System.out.println(" Kick Login Actif Temps: " + time_run1);

                if (time_run1 == 0) {

                    p.kickPlayer(ConfigMessage.getKickOvertime());

                }
                time_run1--;
            }

        }, 20, 20);

        int idtache = tache.getTaskId();

        listTacheLogin.put(getUuidHash(p), idtache);

    }

    public Integer getTaskBlockSpawn(Player p) {
        return listTacheSpawnBlock.get(getUuidHash(p));
    }

    public void getTaskBlockSpawnRemove(Player p) {
        listTacheSpawnBlock.remove(getUuidHash(p));
    }

    public void setTaskBlockSpawn(Player p) {

        BukkitTask tache = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

            double x = 0;
            double y = 73;
            double z = 0;

            World player_world = p.getWorld();
            Location player_tp = new Location(player_world, x, y, z);

            @Override
            public void run() {
                p.teleport(player_tp);
                System.out.println(p.getName() + " Spawn block Actif");
            }

        }, 10, 10);

        int idtache = tache.getTaskId();

        listTacheSpawnBlock.put(getUuidHash(p), idtache);

    }

    public String getHash(String mot) {
        String motSha256 = "";
        try {
            String selMot = ConfigMessage.getSel() + mot;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(selMot.getBytes(StandardCharsets.UTF_8));
            motSha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return motSha256;
    }

    public void getListPlayerRemove(String uuid) {
        listPlayer.remove(uuid);
    }

    public Player getListPlayer(String uuid) {
        return listPlayer.get(uuid);
    }

    public void addListPlayer(String uuid, Player p){
        listPlayer.put(uuid, p);
    }

    public String getUuidHash(Player p){
        String uuid = getHash(p.getName());
        return uuid;
    }
}
