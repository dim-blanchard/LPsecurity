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
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

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

import fr.loirelique.lpsecurity.Command.CommandBan;
import fr.loirelique.lpsecurity.Command.CommandLogin;
import fr.loirelique.lpsecurity.Command.CommandRegister;
import fr.loirelique.lpsecurity.Command.CommandTempban;
import fr.loirelique.lpsecurity.Command.CommandUnban;
import fr.loirelique.lpsecurity.String.ConfigBdd;
import fr.loirelique.lpsecurity.String.ConfigMessage;

/**
 * Information sur la class!LPSECURITY
 *
 */

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;
    // Liste historique des sanctions et des temps;
    /* private HashMap<String, String> str = new HashMap<String, String>(); */
    // Liste des taches joueurs
    private HashMap<String, Integer> listTacheRegister = new HashMap<String, Integer>();
    private HashMap<String, Integer> listTacheLogin = new HashMap<String, Integer>();
    private HashMap<String, Integer> listTacheSpawnBlock = new HashMap<String, Integer>();

    // Liste des joueurs. Liste d'ip similaire en fonction du joueur. Liste des
    // joueurs en Ligne.
    private HashMap<String, Player> listPlayer = new HashMap<String, Player>();
    private static HashMap<String, ArrayList<String>> listIpPlayer = new HashMap<String, ArrayList<String>>();
    private static HashMap<String, Integer> listOnlinePlayer = new HashMap<String, Integer>();

    /**
     * EVENT ON ENBALBLE PLUGIN
     */
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

        CommandExecutor commandBan = new CommandBan();
        getCommand("ban").setExecutor(commandBan);

        CommandExecutor commandUnban = new CommandUnban();
        getCommand("unban").setExecutor(commandUnban);

        CommandExecutor commandTempban = new CommandTempban();
        getCommand("tempban").setExecutor(commandTempban);

        System.out.println("Chargement plugin LPsecurity... ===> OK");
    }

    /**
     * EVENT ON DISABLE PLUGIN
     */
    @Override
    public void onDisable() {
        System.out.println("Arret du plugin LPsecurity... ===> OK");
    }

    /**
     * EVENT PLAYER BEFORE JOIN EVENT
     */
    @EventHandler
    public void playerBeforeJoinServer(AsyncPlayerPreLoginEvent p_event) {

        String uuid = getUuidHash(p_event);
        String ip = p_event.getAddress().getHostAddress();
        int ban = 0;
        String temp_ban = "";
        String motif_tempban = "";
        String motif_ban = "";
        long startTime = System.nanoTime();
        // On fait une requet dans la base de donnée qui retourne la valeur de la
        // colomne "ban" en fonction de la colomne "uuid".
        try (Connection connection_register = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql2 = "SELECT uuid,ban, historique_sanctions->>'$.motif_tempban', historique_sanctions->>'$.temp_ban', historique_sanctions->>'$.motif_ban'  FROM "
                    + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_register
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setString(1, uuid);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        ban = resultat_requete_select.getInt("ban");
                        temp_ban = resultat_requete_select.getString("historique_sanctions->>'$.temp_ban'");
                        motif_tempban = resultat_requete_select.getString("historique_sanctions->>'$.motif_tempban'");
                        motif_ban = resultat_requete_select.getString("historique_sanctions->>'$.motif_ban'");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error block ban ");
        }
        System.out.println(temp_ban);
        System.out.println(motif_ban);
        System.out.println(motif_tempban);

        if (ban == 0) {
            // On test si le joueur et deja en ligne avec le même uuid(Générer avec le
            // pseudo + un préfixe de salage en MD5).

            try {
                if (listOnlinePlayer.get(uuid) != null) {
                    p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                            ConfigMessage.getKickOnline());
                } else {
                    listOnlinePlayer.put(uuid, 1);

                    // On vérifie si le nombre d'ip similaire connecter ne depasse pas la
                    // configuration donner.
                    try {
                        if (listIpPlayer.get(ip) != null) {
                            if (listIpPlayer.size() == ConfigMessage.getKickOverIp()) {
                                p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                                        ConfigMessage.getKickIp());
                            } else {
                                (listIpPlayer.get(ip)).add(uuid);
                            }

                        } else {
                            listIpPlayer.put(ip, new ArrayList<String>());
                            (listIpPlayer.get(ip)).add(uuid);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("error block Ip player ");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error block online ");
            }

        }

        if (temp_ban.equals("null") == true && ban == 1) {
            p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    motif_ban);
        } else if (temp_ban.equals("null") == false && ban == 1) {
            LocalDateTime dateTime_temp_ban = LocalDateTime.parse(temp_ban);

            ZonedDateTime dateTimeNow = ZonedDateTime.now(ZoneId.of("Europe/Paris"));

            LocalDateTime dateTimeZone = dateTimeNow.toLocalDateTime();
            System.out.println(dateTimeZone);

            if (dateTimeZone.isAfter(dateTime_temp_ban)) {
                try (Connection connection_update = DriverManager.getConnection(
                    ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                            ConfigBdd.getPort()
                            + "/"
                            + ConfigBdd.getDatabase1()
                            + "?characterEncoding=latin1&useConfigs=maxPerformance",
                    ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                        " SET ban=?, historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')),historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) WHERE uuid=?";
                try (PreparedStatement statement2_select = connection_update
                        .prepareStatement(requet_Update_sql2)) {
                    statement2_select.setInt(1, 0);
                    statement2_select.setString(2, "motif_tempban");
                    statement2_select.setString(3, "null");
                    statement2_select.setString(4, "temp_ban");
                    statement2_select.setString(5, "null");
                    statement2_select.setString(6, uuid);                                              
                    statement2_select.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            } else {

                LocalDateTime dateTemp_ban = LocalDateTime.parse(temp_ban);

                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd à HH:mm");

                p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                        "Bannie jusqu'au: " + dtf2.format(dateTemp_ban) + " Raison: " + motif_tempban);
            }
        
        }

        long endTime = System.nanoTime();
        System.out.println("Test de vitesse before : " + (endTime - startTime) * Math.pow(10, -6) + " ms");

    }

    /**
     * EVENT PLAYER JOIN EVENT
     */
    @EventHandler
    public void playerJoinServer(PlayerJoinEvent p_event) {
        // Variable utile
        final Player p = p_event.getPlayer();
        String uuid = getUuidHash(p);
        String uuidRequet = "";

        // Début test de vitesse
        long startTime = System.nanoTime();
        // Ajoue Player à la listePlayer
        listPlayer.put(uuid, p);

        // On fait un requet qui récupère l'uuid du joueur et on le cherche dans la base
        // de donnée.
        try (Connection connection_select = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql1 = "SELECT * FROM " + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement1_select = connection_select
                    .prepareStatement(requet_Select_sql1)) {
                statement1_select.setString(1, uuid);

                try (ResultSet resultat_requete_select = statement1_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        uuidRequet = resultat_requete_select.getString("uuid");

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Si l'uuid du joueur est égale à un uuid deja enregistrer.
        if (uuid.equals(uuidRequet)) {
            setTaskBlockSpawn(p);
            setTaskLoginTime(p);
            ConfigMessage.sendLogin(p);

        } // Si l'uuid du joueur n'est pas égale à un uuid deja enregistrer.
        else {
            setTaskBlockSpawn(p);
            setTaskRegisterTime(p);
            ConfigMessage.sendRegister(p);
        }

        // Fin test de vitesse
        long endTime = System.nanoTime();
        System.out.println("Test de vitesse Join : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
    }

    /**
     * Player Quit Evnet
     */
    @EventHandler
    public void playerQuitServer(PlayerQuitEvent p_event) {

        // Variable utile
        final Player p = p_event.getPlayer();
        String uuid = getUuidHash(p);
        String ip = p.getAddress().getHostString();
        // Début test de vitesse
        long startTime = System.nanoTime();

        // Permet de supprimer les entrées Liste des différents joueurs du serveur en
        // fonction de l'uuid.
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

        if (listIpPlayer.get(ip) != null) {
            listIpPlayer.get(ip).remove(uuid);
            if (listIpPlayer.get(ip).size() == 0) {
                listIpPlayer.remove(ip);
            }
        }

        if (listOnlinePlayer.get(uuid) != null) {
            listOnlinePlayer.remove(uuid);
        }

        System.out.println(listPlayer.get(uuid) + " " + listIpPlayer.get(ip));

        // Fin test de vitesse
        long endTime = System.nanoTime();
        System.out.println("Test de vitesse quit : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
    }

    /**
     * Getter de tache register.
     * On recupère l'id de la tache register en fonction de l'uuid du joueur.
     */
    public Integer getTaskRegisterTime(Player p) {
        return listTacheRegister.get(getUuidHash(p));
    }

    /**
     * Methode de tache register.
     * On supprime le joueur de la list tache register et ca donnée.
     */
    public void getTaskRegisterTimeRemove(Player p) {
        listTacheRegister.remove(getUuidHash(p));
    }

    /**
     * Methode de tache register.
     * Tache de temps d'enregistrement au serveur.
     */
    public void setTaskRegisterTime(Player p) {

        BukkitTask tache = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            // Au dela du delais donner dépasser le joueur est kick.
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
        // On ajoute un joueur à la liste avec une id de tache.
        listTacheRegister.put(getUuidHash(p), idtache);

    }

    /**
     * Getter de tache Login.
     * On recupère l'id de la tache login en fonction de l'uuid du joueur.
     */
    public Integer getTaskLoginTime(Player p) {
        return listTacheLogin.get(getUuidHash(p));
    }

    /**
     * Methode de tache Login.
     * On supprime le joueur de la list tache login et ca donnée.
     */
    public void getTaskLoginTimeRemove(Player p) {
        listTacheLogin.remove(getUuidHash(p));
    }

    /**
     * Methode de tache Login.
     * Tache de temps d'identification au serveur.
     */
    public void setTaskLoginTime(Player p) {

        BukkitTask tache = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            // Au dela du delais donner dépasser le joueur est kick.
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
        // On ajoute un joueur à la liste avec une id de tache.
        listTacheLogin.put(getUuidHash(p), idtache);

    }

    /**
     * Getter de tache spawn.
     * On recupère l'id de la tache login en fonction de l'uuid du joueur.
     */
    public Integer getTaskBlockSpawn(Player p) {
        return listTacheSpawnBlock.get(getUuidHash(p));
    }

    /**
     * Methode de tache spawn.
     * On supprime le joueur de la list tache spawn et ca donnée.
     */
    public void getTaskBlockSpawnRemove(Player p) {
        listTacheSpawnBlock.remove(getUuidHash(p));
    }

    /**
     * Methode de tache spawn.
     * Tache de teleportation au spawn temps que des tache login ou register ne sont
     * pas finie ou validé.
     */
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

    /**
     * Getter ajout Player à la listePlayer en fonction du l'UUID.
     */
    public Player getListPlayer(String uuid) {
        return listPlayer.get(uuid);
    }

    /**
     * Methode de suppression Player à la listePlayer en fonction du l'UUID.
     */
    public void getListPlayerRemove(String uuid) {
        listPlayer.remove(uuid);
    }

    /**
     * Getter création d'un UUID Player.
     */
    public String getUuidHash(Player p) {
        String pseudo = p.getName();
        pseudo = pseudo.replaceAll("\\s", "");
        pseudo = pseudo.toLowerCase();
        String pseudoMD5 = "";
        try {
            String selMot = ConfigMessage.getSel() + pseudo;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(selMot.getBytes(StandardCharsets.UTF_8));
            pseudoMD5 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pseudoMD5;
    }

    /**
     * Getter création d'un UUID AsyncPlayerPreLoginEvent.
     */
    public String getUuidHash(AsyncPlayerPreLoginEvent p) {
        String pseudo = p.getName();
        pseudo = pseudo.replaceAll("\\s", "");
        pseudo = pseudo.toLowerCase();
        String pseudoMD5 = "";
        try {
            String selMot = ConfigMessage.getSel() + pseudo;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(selMot.getBytes(StandardCharsets.UTF_8));
            pseudoMD5 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pseudoMD5;
    }

    /**
     * Getter création d'un UUID en fonction du pseudo.
     */
    public String getUuidHash(String p_name) {
        String pseudo = p_name;
        pseudo = pseudo.replaceAll("\\s", "");
        pseudo = pseudo.toLowerCase();
        String pseudoMD5 = "";
        try {
            String selMot = ConfigMessage.getSel() + pseudo;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(selMot.getBytes(StandardCharsets.UTF_8));
            pseudoMD5 = DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pseudoMD5;
    }

    /**
     * Getter création d'un mot de passe hash en sha256 avec un sel.
     */
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

    public String sansAccent(String s) {
        String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(strTemp).replaceAll("");
    }

    public String getListOnlinePlayer(String uuid) {
        String test = null;

        if (listOnlinePlayer.get(uuid) != null) {
            test = "1";
        } else {
            test = null;
        }
        return test;
    }

}
