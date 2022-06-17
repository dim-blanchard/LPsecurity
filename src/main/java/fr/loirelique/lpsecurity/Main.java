package fr.loirelique.lpsecurity;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

import java.text.Normalizer;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import fr.loirelique.lpsecurity.Command.CommandBan;
import fr.loirelique.lpsecurity.Command.CommandHistorique;
import fr.loirelique.lpsecurity.Command.CommandKick;
import fr.loirelique.lpsecurity.Command.CommandLogin;
import fr.loirelique.lpsecurity.Command.CommandMute;
import fr.loirelique.lpsecurity.Command.CommandRegister;
import fr.loirelique.lpsecurity.Command.CommandResetHistorique;

import fr.loirelique.lpsecurity.Command.CommandTempban;
import fr.loirelique.lpsecurity.Command.CommandTempmute;
import fr.loirelique.lpsecurity.Command.CommandUnban;
import fr.loirelique.lpsecurity.Command.CommandUnmute;
import fr.loirelique.lpsecurity.Command.CommandWarn;
import fr.loirelique.lpsecurity.List.CommandCreateSupport;
import fr.loirelique.lpsecurity.List.CommandJoinSupport;
import fr.loirelique.lpsecurity.List.CommandListSupport;
import fr.loirelique.lpsecurity.List.CommandRemovesupport;
import fr.loirelique.lpsecurity.List.ListWarningDegresAndMotifs;
import fr.loirelique.lpsecurity.String.ConfigBdd;
import fr.loirelique.lpsecurity.String.MessageKick;
import fr.loirelique.lpsecurity.String.MessageLogin;
import fr.loirelique.lpsecurity.String.MessageRegister;
import fr.loirelique.lpsecurity.Usefull.DateAndTime;
import fr.loirelique.lpsecurity.Usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.Usefull.DataFolder;
import fr.loirelique.lpsecurity.Usefull.DataListIp;
import fr.loirelique.lpsecurity.Usefull.DataListPlayers;

/**
 * Information sur la class!LPSECURITY
 *
 */

public class Main extends JavaPlugin implements Listener {

    // Chemain des fichies ressource.
    public String dataPlayer = "/DataPlayer";
    public String dataList = "/DataList";
    public String dataListSupport = "/DataList/Support";
    public String dataListIp = "/DataList/Ip";
    public String dataListPlayers = "/DataList/Players";

    //
    public static Main plugin;
    // Liste des taches joueurs
    private HashMap<String, Integer> listTacheRegister = new HashMap<String, Integer>();
    private HashMap<String, Integer> listTacheLogin = new HashMap<String, Integer>();
    private HashMap<String, Integer> listTacheSpawnBlock = new HashMap<String, Integer>();

    // Liste des joueurs. Liste d'ip similaire en fonction du joueur. Liste des
    // joueurs en Ligne.
    private HashMap<String, Player> listPlayer = new HashMap<String, Player>();
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

        CommandExecutor commandHistorique = new CommandHistorique();
        getCommand("historique").setExecutor(commandHistorique);

        CommandExecutor commandResetHistorique = new CommandResetHistorique();
        getCommand("resethistorique").setExecutor(commandResetHistorique);

        CommandExecutor commandMute = new CommandMute();
        getCommand("mute").setExecutor(commandMute);

        CommandExecutor commandTempmute = new CommandTempmute();
        getCommand("tempmute").setExecutor(commandTempmute);

        CommandExecutor commandUnmute = new CommandUnmute();
        getCommand("unmute").setExecutor(commandUnmute);

        CommandExecutor commandKick = new CommandKick();
        getCommand("kick").setExecutor(commandKick);

        TabExecutor commandWarn = new CommandWarn();
        getCommand("warn").setExecutor(commandWarn);
        getCommand("warn").setTabCompleter(commandWarn);

        CommandExecutor commandCreateSupport = new CommandCreateSupport();
        getCommand("createsupport").setExecutor(commandCreateSupport);

        CommandExecutor commandRemovesupport = new CommandRemovesupport();
        getCommand("removesupport").setExecutor(commandRemovesupport);

        CommandExecutor commandListSupport = new CommandListSupport();
        getCommand("listsupport").setExecutor(commandListSupport);

        CommandExecutor commandJoinSupport = new CommandJoinSupport();
        getCommand("joinsupport").setExecutor(commandJoinSupport);

        ListWarningDegresAndMotifs.initializeList();
        DataFolder.create(dataPlayer);
        DataFolder.create(dataList);
        DataFolder.create(dataListSupport);
        DataFolder.create(dataListIp);
        DataFolder.create(dataListPlayers);

        Bukkit.getConsoleSender().sendMessage("     §4__   __");
        Bukkit.getConsoleSender().sendMessage("§4|   |__) (    §l§2LPsecurity §l§4v1.0 §l§8(by LoiRelique)");
        Bukkit.getConsoleSender().sendMessage("§4|__ |   __)   §l§8Running on Spigot 1.8.8");
        Bukkit.getConsoleSender().sendMessage("");

    }

    /**
     * EVENT ON DISABLE PLUGIN
     */
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§4[Disable] §l§8LPsecurity §l§4v1.0");
        Bukkit.getConsoleSender().sendMessage("§4[STOP SERVER] Don't reload with §l§8LPsecurity §l§4v1.0");
    }

    /**
     * EVENT PLAYER BEFORE JOIN EVENT
     */
    @EventHandler
    public void playerBeforeJoinServer(AsyncPlayerPreLoginEvent p_event) {
        String uuidPlayers = getUuidHash(p_event);
        String ipPlayers = p_event.getAddress().getHostAddress();

        int ban = 2;
        String motif_ban = "null";
        String motif_unban = "null";

        String temp_ban = "null";
        String motif_tempban = "null";

        int mute = 2;
        String motif_mute = "null";
        String motif_unmute = "null";

        String temp_mute = "null";
        String motif_tempmute = "null";

        String motif_kick = "null";

        int warn = 2;
        String motif_warn = "null";

        DataPlayersFiles.create(uuidPlayers, dataPlayer);

        long startTime = System.nanoTime();
        // On fait une requet dans la base de donnée qui retourne la valeur de la
        // colomne "ban" en fonction de la colomne "uuid".
        try (Connection connection_register = DriverManager.getConnection(
                ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" + ConfigBdd.getPort() + "/"
                        + ConfigBdd.getDatabase1()
                        + "?characterEncoding=latin1&useConfigs=maxPerformance",
                ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
            String requet_Select_sql2 = "SELECT  historique_sanctions->>'$.motif_kick',historique_sanctions->>'$.motif_warn',historique_sanctions->>'$.warn', historique_sanctions->>'$.ban', historique_sanctions->>'$.temp_ban', historique_sanctions->>'$.motif_tempban',historique_sanctions->>'$.motif_unban' ,historique_sanctions->>'$.motif_ban', historique_sanctions->>'$.mute', historique_sanctions->>'$.motif_unmute', historique_sanctions->>'$.motif_mute', historique_sanctions->>'$.motif_tempmute', historique_sanctions->>'$.temp_mute' FROM "
                    + ConfigBdd.getTable1() + " WHERE uuid=?";
            try (PreparedStatement statement2_select = connection_register
                    .prepareStatement(requet_Select_sql2)) {
                statement2_select.setString(1, uuidPlayers);

                try (ResultSet resultat_requete_select = statement2_select.executeQuery()) {
                    while (resultat_requete_select.next()) {
                        ban = resultat_requete_select.getInt("historique_sanctions->>'$.ban'");
                        motif_ban = resultat_requete_select.getString("historique_sanctions->>'$.motif_ban'");
                        motif_unban = resultat_requete_select.getString("historique_sanctions->>'$.motif_unban'");

                        temp_ban = resultat_requete_select.getString("historique_sanctions->>'$.temp_ban'");
                        motif_tempban = resultat_requete_select.getString("historique_sanctions->>'$.motif_tempban'");

                        mute = resultat_requete_select.getInt("historique_sanctions->>'$.mute'");
                        motif_mute = resultat_requete_select.getString("historique_sanctions->>'$.motif_mute'");
                        motif_unmute = resultat_requete_select.getString("historique_sanctions->>'$.motif_unmute'");

                        temp_mute = resultat_requete_select.getString("historique_sanctions->>'$.temp_mute'");
                        motif_tempmute = resultat_requete_select.getString("historique_sanctions->>'$.motif_tempmute'");

                        warn = resultat_requete_select.getInt("historique_sanctions->>'$.warn'");
                        motif_warn = resultat_requete_select.getString("historique_sanctions->>'$.motif_warn'");

                        motif_kick = resultat_requete_select.getString("historique_sanctions->>'$.motif_kick'");

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    "La base de donné n'est pas en ligne merci de reitérer plus tard.");
        }

        DataPlayersFiles.updateHistoriqueSanctions(uuidPlayers, ban, motif_ban, motif_unban, temp_ban, motif_tempban,
                mute, motif_mute, motif_unmute, temp_mute, motif_tempmute, motif_kick, warn, motif_warn, dataPlayer);

        if (ban == 0) {
            // On test si le joueur et deja en ligne avec le même uuid(Générer avec le
            // pseudo + un préfixe de salage en MD5).

            if (DataPlayersFiles.getIsOnline(uuidPlayers, dataPlayer) == true) {
                p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                        MessageKick.getKickOnline());
            } else {
                DataPlayersFiles.setIsOnline(uuidPlayers, true, dataPlayer);
                // On vérifie si le nombre d'ip similaire connecter ne depasse pas la
                // configuration donner.
                // Cree le fichier verifie sil existe compare la taille kick le joueur en
                // fonction du nombre d'ip similaire utiliser
                DataListIp.setFile(uuidPlayers, ipPlayers, p_event);
            }
        }

        if (temp_ban.equals("null") == true && ban == 1) {
            p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    motif_ban);
        } else if (temp_ban.equals("null") == false && ban == 1) {

            Calendar dateTimeNow = Calendar.getInstance();
            Date dateTimeZone = dateTimeNow.getTime();

            DateAndTime dateAndTime = new DateAndTime();
            Date dateTime_temp_ban = dateAndTime.getDateFromBddToCompare(temp_ban);

            if (dateTimeZone.after(dateTime_temp_ban)) {
                ban = 0;
                motif_tempban = "null";
                temp_ban = "null";
                try (Connection connection_update = DriverManager.getConnection(
                        ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                                ConfigBdd.getPort()
                                + "/"
                                + ConfigBdd.getDatabase1()
                                + "?characterEncoding=latin1&useConfigs=maxPerformance",
                        ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                    String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                            " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')),, historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')),historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) WHERE uuid=?";
                    try (PreparedStatement statement2_select = connection_update
                            .prepareStatement(requet_Update_sql2)) {
                        statement2_select.setString(1, "ban");
                        statement2_select.setInt(2, ban);
                        statement2_select.setString(3, "motif_tempban");
                        statement2_select.setString(4, motif_tempban);
                        statement2_select.setString(5, "temp_ban");
                        statement2_select.setString(6, temp_ban);
                        statement2_select.setString(7, uuidPlayers);
                        statement2_select.executeUpdate();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String dateTemp_ban = dateAndTime.getDateForPlayer(dateAndTime.getDateFromBddToCompare(temp_ban));
                p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                        "Bannie jusqu'au: " + dateTemp_ban + " Raison: " + motif_tempban);
            }

        }

        if (temp_mute.equals("null") == true && mute == 1) {
            DataPlayersFiles.setMuteAndMotif(uuidPlayers, mute, motif_mute, dataPlayer);
        } else if (temp_mute.equals("null") == false && mute == 1) {
            Calendar dateTimeNow1 = Calendar.getInstance();
            Date dateTimeZone1 = dateTimeNow1.getTime();

            DateAndTime dateAndTime1 = new DateAndTime();
            Date dateTime_temp_mute1 = dateAndTime1.getDateFromBddToCompare(temp_mute);

            if (dateTimeZone1.after(dateTime_temp_mute1)) {
                mute = 0;
                motif_tempmute = "null";
                temp_mute = "null";
                try (Connection connection_update = DriverManager.getConnection(
                        ConfigBdd.getDriver() + "://" + ConfigBdd.getHost() + ":" +
                                ConfigBdd.getPort()
                                + "/"
                                + ConfigBdd.getDatabase1()
                                + "?characterEncoding=latin1&useConfigs=maxPerformance",
                        ConfigBdd.getUser1(), ConfigBdd.getPass1())) {
                    String requet_Update_sql2 = "UPDATE " + ConfigBdd.getTable1() +
                            " SET historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')),, historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')),historique_sanctions=JSON_SET(historique_sanctions, CONCAT('$.',?), CONCAT('',?,'')) WHERE uuid=?";
                    try (PreparedStatement statement2_select = connection_update
                            .prepareStatement(requet_Update_sql2)) {
                        statement2_select.setString(1, "mute");
                        statement2_select.setInt(2, mute);
                        statement2_select.setString(3, "motif_tempmute");
                        statement2_select.setString(4, motif_tempmute);
                        statement2_select.setString(5, "temp_mute");
                        statement2_select.setString(6, temp_mute);
                        statement2_select.setString(7, uuidPlayers);
                        statement2_select.executeUpdate();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                DataPlayersFiles.setMuteTempMuteAndMotif(uuidPlayers, mute, temp_mute, motif_tempmute, dataPlayer);
            }

            long endTime = System.nanoTime();
            System.out.println("Test de vitesse before : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
        }

    }

    /**
     * EVENT PLAYER JOIN EVENT
     */
    @EventHandler
    public void playerJoinServer(PlayerJoinEvent p_event) {
        // Variable utile
        final Player p = p_event.getPlayer();
        String uuidPlayers = getUuidHash(p);
        String uuidRequet = "";

        // Début test de vitesse
        long startTime = System.nanoTime();
        // Ajoue Player à la listePlayer
        DataListPlayers.setFile(uuidPlayers, p);
        listPlayer.put(uuidPlayers, p);

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
                statement1_select.setString(1, uuidPlayers);

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
        if (uuidPlayers.equals(uuidRequet)) {
            setTaskBlockSpawn(p);
            setTaskLoginTime(p);
            MessageLogin.sendLogin(p);

        } // Si l'uuid du joueur n'est pas égale à un uuid deja enregistrer.
        else {
            setTaskBlockSpawn(p);
            setTaskRegisterTime(p);
            MessageRegister.sendRegister(p);
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
        String uuidPlayers = getUuidHash(p);
        String ipPlayers = p.getAddress().getHostString();
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
        // List vidage cache
        if (listPlayer.get(uuid) != null) {
            getListPlayerRemove(uuid);
        }

        // Enleve l'ip du joueur de la List
        DataListIp.removeFileOrPlayers(uuidPlayers, ipPlayers);
        // If player is online
        if (DataPlayersFiles.getIsOnline(uuidPlayers, dataPlayer) == true) {
            DataPlayersFiles.setIsOnline(uuidPlayers, false, dataPlayer);
        }
        if (DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer) == true) {
            DataPlayersFiles.setIsLogin(uuidPlayers, false, dataPlayer);
        }
        // List tentative pass
        DataPlayersFiles.setNumberTentativeLogin(uuidPlayers, 0, Main.plugin.dataPlayer);
        // List mute

        if (DataListPlayers.getObjectPlayers(uuidPlayers) != null) {
            DataListPlayers.removeObjectPlayers(uuidPlayers);
        }
        // Fin test de vitesse
        long endTime = System.nanoTime();
        System.out.println("Test de vitesse quit : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
    }

    // Suspend
    @EventHandler
    public void onChat(AsyncPlayerChatEvent p_envent) {
        final Player player = p_envent.getPlayer();
        String uuidPlayers = getUuidHash(player);
        
        if(DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer) == false){
            p_envent.setCancelled(true);
            player.sendMessage("Identifie toi pour parler.");
        }else if(DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer)==true){
            if(DataPlayersFiles.getMute(uuidPlayers, dataPlayer) == 1) {
                String message = "null";
                p_envent.setCancelled(true);
                if (DataPlayersFiles.getMotifMute(uuidPlayers, dataPlayer).equals("null")) {
                    message = DataPlayersFiles.getMotifTempMute(uuidPlayers, dataPlayer);
                } else {
                    message = DataPlayersFiles.getMotifMute(uuidPlayers, dataPlayer);
                }
                player.sendMessage("Mute: " + message);
            } else {
                p_envent.setCancelled(false);
            }
        } 



    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent p_envent) {
        Player p = p_envent.getPlayer();
        String uuidPlayers = getUuidHash(p); 
        String message = p_envent.getMessage();
        String []messageSplit = message.split(" ");
        String messageSplit0 = messageSplit[0];
        if (DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer)==false){
            if(messageSplit0.equals("/login")==true|messageSplit0.equals("/register")==true){
                p.sendMessage("condition /login");
                p_envent.setCancelled(false);
            }else{
                p_envent.setCancelled(true);
                p.sendMessage("Identifie toi pour entrer une commande.");
            }
        }else if (DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer)==true){
            p_envent.setCancelled(false);
            if (messageSplit0.equals("/stop")) {
                final File folder = new File(getDataFolder().toString(), dataPlayer);
                DataPlayersFiles.defaultInfosPlayers(folder);}
        }
    
    }

    @EventHandler
    public void ServerCommandEvent(ServerCommandEvent event) {
        if (event.getCommand().equals("stop")) {
            final File folder = new File(getDataFolder().toString(), dataPlayer);
            DataPlayersFiles.defaultInfosPlayers(folder);
        }

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
            int time_run1 = MessageRegister.getRegisterTime();

            @Override
            public void run() {

                System.out.println(" Kick Register Actif Temps: " + time_run1);

                if (time_run1 == 0) {

                    p.kickPlayer(MessageKick.getKickOvertime());

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
            int time_run1 = MessageLogin.getLoginTime();

            @Override
            public void run() {

                System.out.println(" Kick Login Actif Temps: " + time_run1);

                if (time_run1 == 0) {

                    p.kickPlayer(MessageKick.getKickOvertime());

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
            String selMot = ConfigBdd.getSel() + pseudo;
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
            String selMot = ConfigBdd.getSel() + pseudo;
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
            String selMot = ConfigBdd.getSel() + pseudo;
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
            String selMot = ConfigBdd.getSel() + mot;
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

}
