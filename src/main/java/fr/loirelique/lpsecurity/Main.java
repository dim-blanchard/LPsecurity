package fr.loirelique.lpsecurity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


    
    public static HashMap<String,ArrayList<String>> listArrays=new HashMap<String,ArrayList<String>>();
    
    public static void getIpOfPlayerBeforeLogin(String ip , String name){
        if(listArrays.get(ip)!= null){
            (listArrays.get(ip)).add(name);
      
        }else{
            listArrays.put(ip,new ArrayList<String>());
            (listArrays.get(ip)).add(name);
        }   
    }

    @EventHandler
    public void playerBeforeJoinServer(AsyncPlayerPreLoginEvent p_event) {

        // SELECT count(ip) FROM pf8kr9g9players WHERE ip="127.0.0.1";

        //getIpOfPlayerBeforeLogin(p_event.getAddress().getHostName(), p_event.getName());

        int online = 0;
        int ban = 0;
        String uuid = p_event.getUniqueId().toString();

        long startTime = System.nanoTime();

        try (Connection connection_register = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            // -2 Fait une ou plusieure requete connection au jeux

            String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_register
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setObject(1, uuid);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        online = resultat_requete_select.getInt(6);
                        ban = resultat_requete_select.getInt("ban");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (online == 1) {
            p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    ConfigMessage.getKickOnline());
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
        String uuid = p.getUniqueId().toString();
        String uuidfrombdd = "";

        long startTime = System.nanoTime();
        listPlayer.put(p.getName(), p);
        try (Connection connection_register = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            // -2 Fait une ou plusieure requete connection au jeux

            String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_register
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setObject(1, uuid);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        uuidfrombdd = resultat_requete_select.getString(2);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (uuid.equals(uuidfrombdd)) {
            // System.out.println("Le joueur est dans la bdd.");
            setTaskBlockSpawn(p);
            setTaskLoginTime(p);
            ConfigMessage.sendLogin(p);

            try (Connection connection_update = DriverManager.getConnection(
                    ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                            + "/"
                            + ConfigBdd.getDatabase1()
                            + "?characterEncoding=latin1&useConfigs=maxPerformance",
                    ConfigBdd.getUser1(), ConfigBdd.getPass1())) {

                // UPDATE pf8kr9g9players SET online=0 , ip=0 WHERE pseudo="LoiRelique";
                // -2 Fait une ou plusieure requete connection au jeux
                String requet_Select_sql2 = "UPDATE " + ConfigBdd.getTable1() + " SET online=? , ip=? WHERE uuid=?";
                try (PreparedStatement statement2_select = connection_update.prepareStatement(requet_Select_sql2)) {
                    statement2_select.setInt(1, 1);
                    statement2_select.setString(2, p.getAddress().getHostName().toString());
                    statement2_select.setString(3, uuid);
                    statement2_select.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

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
        String uuid = p.getUniqueId().toString();
        long startTime = System.nanoTime();
        try (Connection connection_update = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                        + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {

            // UPDATE pf8kr9g9players SET online=0 , ip=0 WHERE pseudo="LoiRelique";
            // -2 Fait une ou plusieure requete connection au jeux
            String requet_Select_sql2 = "UPDATE " + ConfigBdd.getTable1() + " SET online=? WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_update.prepareStatement(requet_Select_sql2)) {
                statement2_select.setInt(1, 0);
                statement2_select.setString(2, uuid);
                statement2_select.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * try (Connection connection_update = DriverManager.getConnection(
         * ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
         * ConfigBdd.getPort()
         * + "/"
         * + ConfigBdd.getDatabase1()
         * + "?characterEncoding=latin1&useConfigs=maxPerformance",
         * ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
         * // -2 Fait une ou plusieure requete connection au jeux
         * String requet_Select_sql2 = "SELECT * FROM " + ConfigBdd.getTable1() +
         * " WHERE uuid=?";
         * try (PreparedStatement statement2_select =
         * connection_update.prepareStatement(requet_Select_sql2)) {
         * uuid = p.getUniqueId().toString();
         * statement2_select.setString(1, uuid);
         * //
         * try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
         * if (resultat_requete_select.next()) {
         * String requet_Update_sql3 = "UPDATE " + ConfigBdd.getTable1() +
         * " SET online=0  WHERE uuid=?";
         * try (PreparedStatement statement3_update = connection_update
         * .prepareStatement(requet_Update_sql3)) {
         * statement3_update.setString(1, resultat_requete_select.getString("uuid"));
         * statement3_update.executeUpdate();
         * }
         * }
         * }
         * }
         * 
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */

        if (listTacheRegister.get(p.getName()) != null) {
            Bukkit.getScheduler().cancelTask(getTaskRegisterTime(p));
            getTaskRegisterTimeRemove(p);

        }
        if (listTacheLogin.get(p.getName()) != null) {
            Bukkit.getScheduler().cancelTask(getTaskLoginTime(p));
            getTaskLoginTimeRemove(p);
        }
        if (listTacheSpawnBlock.get(p.getName()) != null) {
            Bukkit.getScheduler().cancelTask(getTaskBlockSpawn(p));
            getTaskBlockSpawnRemove(p);
        }
        if (listPlayer.get(p.getName()) != null) {
            getListPlayerRemove(p.getName());
        }

        long endTime = System.nanoTime();
        System.out.println("Test de vitesse quit : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
    }

    /**
     * GETTER ET SETTER DE TACHE
     */

    public Integer getTaskRegisterTime(Player p) {
        return listTacheRegister.get(p.getName());
    }

    public void getTaskRegisterTimeRemove(Player p) {
        listTacheRegister.remove(p.getName());
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

        listTacheRegister.put(p.getName(), idtache);

    }

    public Integer getTaskLoginTime(Player p) {
        return listTacheLogin.get(p.getName());
    }

    public void getTaskLoginTimeRemove(Player p) {
        listTacheLogin.remove(p.getName());
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

        listTacheLogin.put(p.getName(), idtache);

    }

    public Integer getTaskBlockSpawn(Player p) {
        return listTacheSpawnBlock.get(p.getName());
    }

    public void getTaskBlockSpawnRemove(Player p) {
        listTacheSpawnBlock.remove(p.getName());
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

        listTacheSpawnBlock.put(p.getName(), idtache);

    }

    public String getHash(String password) throws NoSuchAlgorithmException {
        String pass = ConfigMessage.getSel() + password;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(pass.getBytes(StandardCharsets.UTF_8));
        String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();

        return sha256;
    }

    public void getListPlayerRemove(String pseudo) {
        listPlayer.remove(pseudo);
    }

    public Player getListPlayer(String pseudo) {
        return listPlayer.get(pseudo);
    }
}