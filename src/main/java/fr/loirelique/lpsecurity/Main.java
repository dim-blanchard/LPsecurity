package fr.loirelique.lpsecurity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import fr.loirelique.lpsecurity.Command.CommandBanish;
import fr.loirelique.lpsecurity.Command.CommandLogin;
import fr.loirelique.lpsecurity.Command.CommandRegister;
import fr.loirelique.lpsecurity.String.MessagePerso;

/**
 * Information sur la class!LPSECURITY
 *
 */

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;
    private String driver = getConfigBdd("bdd.driver");
    private String host = getConfigBdd("bdd.host");
    private String port = getConfigBdd("bdd.port");
    private String data = getConfigBdd("bdd.database");

    private String username = getConfigBdd("bdd.user");
    private String password = getConfigBdd("bdd.pass");

    private String url = driver + "://" + host + ":" + port + "/" + data
            + "?characterEncoding=latin1&useConfigs=maxPerformance";
 


    /**
     * EVENT PLAYER JOIN EVENT
     */
    @EventHandler
    public void playerJoinServer(PlayerJoinEvent p_event) {
        final Player p = p_event.getPlayer();

        getExecution1(p);
        p.sendTitle(MessagePerso.getTitre(), MessagePerso.getSoustitre());
        setTaskRegisterTime(p);

        try (Connection connection_addPlayer = DriverManager.getConnection(url, username, password)) {
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
                catch (Exception e) 
                {
                    e.printStackTrace();
                }

            }
            catch (Exception e) 
                {
                    e.printStackTrace();
                }
    }


    @EventHandler
    public void playerQuitServer(PlayerQuitEvent p_event){
       final Player p = p_event.getPlayer();
       Bukkit.getScheduler().cancelTask(getTaskRegisterTime(p));

    }

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
        // Liaison

        System.out.println("Chargement plugin LPsecurity... ===> OK");
    }

    @Override
    public void onDisable() {
        System.out.println("Arret du plugin LPsecurity... ===> OK");
    }

    /*
     * 
     * 
     * @EventHandler
     * public void playerBeforeJoinServer(AsyncPlayerPreLoginEvent p) {
     * connection1.connect();
     * /*
     * UUID player_uuid = p.getUniqueId();
     * try {
     * // si joueur dans la bdd
     * if (liaison.isAccount(player_uuid) == true) {
     * 
     * // si joueur et en ligne
     * if (condition) {
     * p.disallow(Result.KICK_OTHER, "Joueur deja en ligne");
     * }
     * 
     * // si joueur et bannie
     * if (condition) {
     * p.disallow(Result.KICK_OTHER, "Tu es bannie du serveur");
     * }
     * }
     * } catch (Exception e) {
     * // TODO: handle exception
     * }
     * https://bukkit.fandom.com/wiki/Plugin_Tutorial/fr#Les_commandes_2
     * 
     * }
     */

    /**
     * GETTER DE CONFIGURATION DE LA BDD
     */
    public String getConfigBdd(String config) {
        String configBdd = getConfig().getString(config);
        return configBdd;
    }


    /** 
     * GETTER DE RUNNABLE
     */

    public Runnable getRun1(Player p) {
        Runnable run1 = new Runnable() {

            double x = 0;
            double y = 64;
            double z = 0;

            World player_world = p.getWorld();
            Location player_tp = new Location(player_world, x, y, z);

            int time_run1 = MessagePerso.getRegistertemps();

            @Override
            public void run() {

               p.teleport(player_tp);

                System.out.println("Temps: " + time_run1);

                if (time_run1 == 0) {

                    p.kickPlayer(MessagePerso.getKick());
                   

                }
                time_run1--;
            }
        };

        return run1;
    }



    /**
     * GETTER ET SETTER DE TACHE
     */
    private HashMap<String,Integer> listTache = new HashMap<String,Integer>();

    public Integer getTaskRegisterTime(Player p) {        
        return listTache.get(p.getName());
    }

    public void setTaskRegisterTime(Player p) {


        BukkitTask tache = Bukkit.getScheduler().runTaskTimer(this, getRun1(p), 20, 20);

        int idtache = tache.getTaskId();

        listTache.put(p.getName(), idtache);

       
    }


    /**
     * GETTER DE COMMANDE
     */
    public Boolean getExecution1(Player event) {
        String player_name = event.getName();
        Boolean commande1 = Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + MessagePerso.getRegistertemps() * 20 + " 10 ");
        return commande1;
    }

}
