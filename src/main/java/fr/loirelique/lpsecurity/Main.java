package fr.loirelique.lpsecurity;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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

import fr.loirelique.lpsecurity.command.CommandBan;
import fr.loirelique.lpsecurity.command.CommandHistorique;
import fr.loirelique.lpsecurity.command.CommandKick;
import fr.loirelique.lpsecurity.command.CommandLogin;
import fr.loirelique.lpsecurity.command.CommandMute;
import fr.loirelique.lpsecurity.command.CommandRegister;
import fr.loirelique.lpsecurity.command.CommandResetHistorique;
import fr.loirelique.lpsecurity.command.CommandTempban;
import fr.loirelique.lpsecurity.command.CommandTempmute;
import fr.loirelique.lpsecurity.command.CommandUnban;
import fr.loirelique.lpsecurity.command.CommandUnmute;
import fr.loirelique.lpsecurity.command.CommandWarn;
import fr.loirelique.lpsecurity.list.CommandCreateSupport;
import fr.loirelique.lpsecurity.list.CommandJoinSupport;
import fr.loirelique.lpsecurity.list.CommandListSupport;
import fr.loirelique.lpsecurity.list.CommandRemovesupport;
import fr.loirelique.lpsecurity.list.ListWarningDegresAndMotifs;
import fr.loirelique.lpsecurity.sqlrequest.RequestDatabase;
import fr.loirelique.lpsecurity.string.ConfigBdd;
import fr.loirelique.lpsecurity.string.MessageKick;
import fr.loirelique.lpsecurity.string.MessageLogin;
import fr.loirelique.lpsecurity.string.MessageRegister;
import fr.loirelique.lpsecurity.string.MessageTempban;
import fr.loirelique.lpsecurity.string.MessageTempmute;
import fr.loirelique.lpsecurity.usefull.DataFolder;
import fr.loirelique.lpsecurity.usefull.DataListIp;
import fr.loirelique.lpsecurity.usefull.DataListPlayers;
import fr.loirelique.lpsecurity.usefull.DataPlayersFiles;
import fr.loirelique.lpsecurity.usefull.DateAndTime;

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

        TabExecutor commandTempban = new CommandTempban();
        getCommand("tempban").setExecutor(commandTempban);
        getCommand("tempban").setTabCompleter(commandTempban);

        CommandExecutor commandHistorique = new CommandHistorique();
        getCommand("historique").setExecutor(commandHistorique);

        CommandExecutor commandResetHistorique = new CommandResetHistorique();
        getCommand("resethistorique").setExecutor(commandResetHistorique);

        CommandExecutor commandMute = new CommandMute();
        getCommand("mute").setExecutor(commandMute);

        TabExecutor commandTempmute = new CommandTempmute();
        getCommand("tempmute").setExecutor(commandTempmute);
        getCommand("tempmute").setTabCompleter(commandTempmute);

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
        DateAndTime.initializeList();
        MessageTempban.initializelist();
        MessageTempmute.initializelist();

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
        //Object Request.
        RequestDatabase requet = new RequestDatabase();
        if (RequestDatabase.isOnline()==false) {
            p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    "La base de donné n'est pas en ligne merci de reitérer plus tard.");
                    exitDefaultDataPlayers(uuidPlayers, ipPlayers);
        }else if(RequestDatabase.isOnline()==true){
            //Request Sql Select Historique sanctions.
            requet.getHS(uuidPlayers);
            int ban = requet.getBan();
            String motif_ban = requet.getMotif_ban();
            String motif_unban = requet.getMotif_unban();
            String temp_ban = requet.getTempban();
            String motif_tempban = requet.getMotif_tempban();
            int mute = requet.getMute();
            String motif_mute = requet.getMotif_mute();
            String motif_unmute = requet.getMotif_unmute();
            String temp_mute = requet.getTempmute();
            String motif_tempmute = requet.getMotif_tempmute();
            String motif_kick = requet.getMotif_kick();
            int warn = requet.getWarn();
            String motif_warn = requet.getMotif_warn();
            long startTime = System.nanoTime();
            DataPlayersFiles.create(uuidPlayers, dataPlayer);
            DataPlayersFiles.setHistoriqueSanctions(uuidPlayers, ban, motif_ban, motif_unban, temp_ban, motif_tempban,mute, motif_mute, motif_unmute, temp_mute, motif_tempmute, motif_kick, warn, motif_warn);
            if (DataPlayersFiles.getIsOnline(uuidPlayers, dataPlayer) == true) {p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,MessageKick.getKickOnline()); exitDefaultDataPlayers(uuidPlayers, ipPlayers);}
            else{DataPlayersFiles.setIsOnline(uuidPlayers, true, dataPlayer);DataListIp.setFile(uuidPlayers, ipPlayers, p_event);}
            
            if (temp_ban.equals("null") == true && ban == 1) {p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,motif_ban);exitDefaultDataPlayers(uuidPlayers, ipPlayers);}
            else if(temp_ban.equals("null") == false && ban == 1) {           
                Calendar dateTimeNow = Calendar.getInstance();
                Date dateTimeZone = dateTimeNow.getTime();
                Date dateTime_temp_ban = DateAndTime.setDateFromBddToCompare(temp_ban);
                if (dateTimeZone.after(dateTime_temp_ban)) {RequestDatabase.upHS(uuidPlayers,"motif_tempban" , "null");RequestDatabase.upHS(uuidPlayers,"tempban" , "null");RequestDatabase.upHS(uuidPlayers,"ban" , 0);DataPlayersFiles.setUnbanTempBanAndMotif(uuidPlayers);} 
                else{p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,"Bannie jusqu'au: " + DateAndTime.getDateFormatToString(DateAndTime.setDateFromBddToCompare(temp_ban)) + " Raison: " + motif_tempban);exitDefaultDataPlayers(uuidPlayers, ipPlayers);}
            }
            if (temp_mute.equals("null") == true && mute == 1) {DataPlayersFiles.setMuteAndMotif(uuidPlayers, motif_mute);} 
            else if (temp_mute.equals("null") == false && mute == 1) {
                Calendar dateTimeNow1 = Calendar.getInstance();
                Date dateTimeZone1 = dateTimeNow1.getTime();
                Date dateTime_temp_mute1 = DateAndTime.setDateFromBddToCompare(temp_mute);
                if (dateTimeZone1.after(dateTime_temp_mute1)) {RequestDatabase.upHS(uuidPlayers,"motif_tempmute" , "null");RequestDatabase.upHS(uuidPlayers,"tempmute" , "null");RequestDatabase.upHS(uuidPlayers,"mute" , 0);;DataPlayersFiles.setUnmuteTempMuteAndMotif(uuidPlayers);}
                long endTime = System.nanoTime();
                System.out.println("Test de vitesse before : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
            }
        }
    }

    /**
     * EVENT PLAYER JOIN EVENT
     */
    @EventHandler @Deprecated
    public void playerJoinServer(PlayerJoinEvent p_event) {
        final Player p = p_event.getPlayer();
        String uuidPlayers = getUuidHash(p);
        // Ajoue Player à la listePlayer
        DataListPlayers.setFile(uuidPlayers, p);    
        // Début test de vitesse
        long startTime = System.nanoTime();
        RequestDatabase requet = new RequestDatabase();
         // On fait un requet qui récupère l'uuid du joueur et on le cherche dans la base de donnée.
        requet.getColumn(uuidPlayers);
        String uuidPlayersBdd = requet.getUuidPlayers();
        // Si l'uuid du joueur est égale à un uuid deja enregistrer.
        if (uuidPlayers.equals(uuidPlayersBdd)) {setTaskBlockSpawn(p);setTaskLoginTime(p);MessageLogin.sendLogin(p);} 
        // Si l'uuid du joueur n'est pas égale à un uuid deja enregistrer.
        else {setTaskBlockSpawn(p);setTaskRegisterTime(p);MessageRegister.sendRegister(p);}
        // Fin test de vitesse
        long endTime = System.nanoTime();
        System.out.println("Test de vitesse Join : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
    }
    /**
     * Player Quit Evnet
     */
    @EventHandler
    public void playerQuitServer(PlayerQuitEvent p_event) {
        final Player p = p_event.getPlayer();
        String uuidPlayers = getUuidHash(p);
        String ipPlayers = p.getAddress().getHostString();
        // Début test de vitesse
        long startTime = System.nanoTime();
        // Permet de supprimer les entrées Liste des différents joueurs du serveur enfonction de l'uuid.
        if (listTacheRegister.get(uuidPlayers) != null) {Bukkit.getScheduler().cancelTask(getTaskRegisterTime(p));getTaskRegisterTimeRemove(p);}
        if (listTacheLogin.get(uuidPlayers) != null) {Bukkit.getScheduler().cancelTask(getTaskLoginTime(p));getTaskLoginTimeRemove(p);}
        if (listTacheSpawnBlock.get(uuidPlayers) != null) {Bukkit.getScheduler().cancelTask(getTaskBlockSpawn(p));getTaskBlockSpawnRemove(p);}
        //Fermeture des infos jouers.
        exitDefaultDataPlayers(uuidPlayers, ipPlayers);
        // Fin test de vitesse
        long endTime = System.nanoTime();
        System.out.println("Test de vitesse quit : " + (endTime - startTime) * Math.pow(10, -6) + " ms");
    }

    // Suspend
    @EventHandler
    public void onChat(AsyncPlayerChatEvent p_envent) {
        final Player player = p_envent.getPlayer();
        String uuidPlayers = getUuidHash(player); 
        if(DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer) == false){p_envent.setCancelled(true);player.sendMessage("Identifie toi pour parler.");
        }else if(DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer)==true){
            if(DataPlayersFiles.getMute(uuidPlayers, dataPlayer) == 1) {
                String message = "null";
                p_envent.setCancelled(true);
                if (DataPlayersFiles.getMotifMute(uuidPlayers, dataPlayer).equals("null")) { message = DataPlayersFiles.getMotifTempMute(uuidPlayers, dataPlayer);} 
                else {message = DataPlayersFiles.getMotifMute(uuidPlayers, dataPlayer);}
                player.sendMessage("Mute: " + message);
            } else {p_envent.setCancelled(false);}
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
            if(messageSplit0.equals("/login")==true|messageSplit0.equals("/register")==true){p_envent.setCancelled(false);}
            else{p_envent.setCancelled(true);p.sendMessage("Identifie toi pour entrer une commande.");}
        }else if (DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer)==true){p_envent.setCancelled(false);
            if (messageSplit0.equals("/stop")) {final File folder = new File(getDataFolder().toString(), dataPlayer);DataPlayersFiles.defaultInfosPlayers(folder);}
        }
    }

    @EventHandler
    public void ServerCommandEvent(ServerCommandEvent event) {
        if (event.getCommand().equals("stop")) {final File folder = new File(getDataFolder().toString(), dataPlayer);DataPlayersFiles.defaultInfosPlayers(folder);}
    }

    /**
     * Getter de tache register.
     * On recupère l'id de la tache register en fonction de l'uuid du joueur.
     */
    public Integer getTaskRegisterTime(Player p) {return listTacheRegister.get(getUuidHash(p));}
    /**
     * Methode de tache register.
     * On supprime le joueur de la list tache register et ca donnée.
     */
    public void getTaskRegisterTimeRemove(Player p) {listTacheRegister.remove(getUuidHash(p));}
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

    public void exitDefaultDataPlayers(String uuidPlayers, String ipPlayers) {

        // Enleve l'ip du joueur de la List
        if (DataListIp.getFileIp(ipPlayers)!=null) {
            DataListIp.removeFileOrPlayers(uuidPlayers, ipPlayers);
        }    
        // If player is online
        if (DataPlayersFiles.getIsOnline(uuidPlayers, dataPlayer) == true) {
            DataPlayersFiles.setIsOnline(uuidPlayers, false, dataPlayer);
        }
        if (DataPlayersFiles.getIsLogin(uuidPlayers, dataPlayer) == true) {
            DataPlayersFiles.setIsLogin(uuidPlayers, false, dataPlayer);
        }
        // List tentative pass
        DataPlayersFiles.setNumberTentativeLogin(uuidPlayers, 0, dataPlayer);
        // List mute

        if (DataListPlayers.getObjectPlayers(uuidPlayers) != null) {
            DataListPlayers.removeObjectPlayers(uuidPlayers);
        }
    }

}
