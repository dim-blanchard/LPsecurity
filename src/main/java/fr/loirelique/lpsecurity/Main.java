package fr.loirelique.lpsecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

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

        int online = 0;
        int ban = 0;
        String uuid = p_event.getUniqueId().toString();
        try (Connection connection_register = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            // -2 Fait une ou plusieure requete connection au jeux

            String requet_Select_sql2 = "SELECT * FROM pf8kr9g9players WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_register
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setObject(1, uuid);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        online = resultat_requete_select.getInt(6);
                        ban = resultat_requete_select.getInt(7);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (online == 1 | ban == 1) {
            p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    "Joueur deja en ligne ou Bannie");
        }

    }

    /**
     * EVENT PLAYER JOIN EVENT
     */

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent p_event) {
        final Player p = p_event.getPlayer();
        String uuid = p.getUniqueId().toString();
        String uuidfrombdd = "";
        try (Connection connection_register = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            // -2 Fait une ou plusieure requete connection au jeux

            String requet_Select_sql2 = "SELECT * FROM pf8kr9g9players WHERE uuid=?";
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
            System.out.println("Le joueur est dans la bdd.");
            setTaskBlockSpawn(p);
            setTaskLoginTime(p);
            ConfigMessage.sendLogin(p);

            try (Connection connection_update = DriverManager.getConnection(
                    ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                            + "/"
                            + ConfigBdd.getDatabase1()
                            + "?characterEncoding=latin1&useConfigs=maxPerformance",
                    ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                // -2 Fait une ou plusieure requete connection au jeux
                String requet_Select_sql2 = "SELECT * FROM pf8kr9g9players WHERE uuid=?";
                try (PreparedStatement statement2_select =connection_update.prepareStatement(requet_Select_sql2)) {
                    uuid = p.getUniqueId().toString();
                    statement2_select.setString(1, uuid);
                    //
                    try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                        if (resultat_requete_select.next()) {
                            String requet_Update_sql3 = "UPDATE pf8kr9g9players SET online=1 , ip=? WHERE uuid=?";
                            try (PreparedStatement statement3_update = connection_update
                                    .prepareStatement(requet_Update_sql3)) { 
                                statement3_update.setString(1,p.getAddress().toString());
                                statement3_update.setString(2, resultat_requete_select.getString("uuid"));
                                statement3_update.executeUpdate();
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Le joueur n'est pas dans la bdd.");
            setTaskBlockSpawn(p);
            setTaskRegisterTime(p);
            ConfigMessage.sendRegister(p);
        }

    }

    @EventHandler
    public void playerQuitServer(PlayerQuitEvent p_event) {
        final Player p = p_event.getPlayer();

        String uuid = p.getUniqueId().toString();
        try (Connection connection_update = DriverManager.getConnection(
            ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort()
                    + "/"
                    + ConfigBdd.getDatabase1()
                    + "?characterEncoding=latin1&useConfigs=maxPerformance",
            ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
        // -2 Fait une ou plusieure requete connection au jeux
        String requet_Select_sql2 = "SELECT * FROM pf8kr9g9players WHERE uuid=?";
        try (PreparedStatement statement2_select =connection_update.prepareStatement(requet_Select_sql2)) {
            uuid = p.getUniqueId().toString();
            statement2_select.setString(1, uuid);
            //
            try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                if (resultat_requete_select.next()) {
                    String requet_Update_sql3 = "UPDATE pf8kr9g9players SET online=0  WHERE uuid=?";
                    try (PreparedStatement statement3_update = connection_update
                            .prepareStatement(requet_Update_sql3)) { 
                        statement3_update.setString(1, resultat_requete_select.getString("uuid"));
                        statement3_update.executeUpdate();
                    }
                }
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
        if (listTacheRegister.get(p.getName()) != null) {
            Bukkit.getScheduler().cancelTask(getTaskRegisterTime(p));
        }
        if (listTacheLogin.get(p.getName()) != null) {
            Bukkit.getScheduler().cancelTask(getTaskLoginTime(p));
        }
        if (listTacheSpawnBlock.get(p.getName()) != null) {
            Bukkit.getScheduler().cancelTask(getTaskBlockSpawn(p));
        }

    }

    /**
     * GETTER ET SETTER DE TACHE
     */
    private HashMap<String, Integer> listTacheRegister = new HashMap<String, Integer>();
    private HashMap<String, Integer> listTacheLogin = new HashMap<String, Integer>();
    private HashMap<String, Integer> listTacheSpawnBlock = new HashMap<String, Integer>();

    public Integer getTaskRegisterTime(Player p) {
        return listTacheRegister.get(p.getName());
    }

    public void setTaskRegisterTime(Player p) {

        BukkitTask tache = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

            int time_run1 = ConfigMessage.getRegistertemps();

            @Override
            public void run() {

                System.out.println(" Kick Register Actif Temps: " + time_run1);

                if (time_run1 == 0) {

                    p.kickPlayer(ConfigMessage.getKick());

                }
                time_run1--;
            }

        }, 20, 20);

        int idtache = tache.getTaskId();

        listTacheRegister.put(p.getName(), idtache);

    }

    //////////////////////////////////////////////////
    public Integer getTaskLoginTime(Player p) {
        return listTacheLogin.get(p.getName());
    }

    public void setTaskLoginTime(Player p) {

        BukkitTask tache = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

            int time_run1 = ConfigMessage.getRegistertemps();

            @Override
            public void run() {

                System.out.println(" Kick Login Actif Temps: " + time_run1);

                if (time_run1 == 0) {

                    p.kickPlayer(ConfigMessage.getKick());

                }
                time_run1--;
            }

        }, 20, 20);

        int idtache = tache.getTaskId();

        listTacheLogin.put(p.getName(), idtache);

    }

    ////////////////////////////////////////////
    public Integer getTaskBlockSpawn(Player p) {
        return listTacheSpawnBlock.get(p.getName());
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

    /**
     * Methode des diffrente connection à la bdd
     */

    public void connectionRegister(Player p) {
        ConfigMessage.sendRegister(p);
        setTaskRegisterTime(p);
        try (Connection connection_addPlayer = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            // -2 Fait une ou plusieure requete connection au jeux

            String requet_insert_sql2 = "INSERT INTO pf8kr9g9players (uuid,pseudo,ip,password) VALUES(?,?,?,?)";
            try (PreparedStatement statement2_insert = connection_addPlayer.prepareStatement(requet_insert_sql2)) {
                String uuid = p.getUniqueId().toString();
                String pseudo = p.getName();
                String ip = p.getAddress().toString();
                String pass = "temporaire";

                statement2_insert.setString(1, uuid);
                statement2_insert.setString(2, pseudo);
                statement2_insert.setString(3, ip);
                statement2_insert.setString(4, pass);
                statement2_insert.executeUpdate();
                //
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String jouerDansLaBdd(Player p) {

        try (Connection connection_register = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            // -2 Fait une ou plusieure requete connection au jeux

            String requet_Select_sql2 = "SELECT * FROM pf8kr9g9players WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_register
                    .prepareStatement(requet_Select_sql2)) {
                String uuid = p.getUniqueId().toString();
                statement2_select.setObject(1, uuid);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    String pseudo;
                    while (resultat_requete_select.next()) {
                        pseudo = resultat_requete_select.getString(4);
                        return pseudo;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
