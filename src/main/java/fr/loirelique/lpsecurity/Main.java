package fr.loirelique.lpsecurity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

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
    public void playerJoinServer(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        getExecution1(player);
        player.sendTitle(getTitreMessage(), getSoustitreMessage());
        setTask1(player);
        
        

        try (Connection connection_addPlayer = DriverManager.getConnection(url, username, password)) {
            // -2 Fait une ou plusieure requete connection au jeux

            String requet_insert_sql2 = "INSERT INTO players (uuid,pseudo,ip) VALUES(?,?,?)";
            try (PreparedStatement statement2_insert = connection_addPlayer.prepareStatement(requet_insert_sql2)) {
                String uuid = player.getUniqueId().toString();
                String pseudo = player.getName();
                String ip = player.getAddress().toString();

                statement2_insert.setString(1, uuid);
                statement2_insert.setString(2, pseudo);
                statement2_insert.setString(3, ip);
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

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        // Commandes
        CommandExecutor commandRegister = new commandRegister();
        getCommand("register").setExecutor(commandRegister);
        CommandExecutor commandLogin = new commandLogin();
        getCommand("login").setExecutor(commandLogin);
        CommandExecutor commandBanish = new commandBanish();
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
     * GETTER DE CONFIG MESSAGE
     */
    public int getRegistertemps() {
        int registertemps = Integer.parseInt(getConfig().getString("Connection.temps_enregistrement"));
        return registertemps;
    }

    public String getSoustitreMessage() {
        String soustitre = getConfig().getString("Connection.soustitre");
        return soustitre;
    }

    public String getTitreMessage() {
        String titre = getConfig().getString("Connection.titre");
        return titre;
    }

    public String getKickMessage() {
        String kick = getConfig().getString("Connection.message_kick");
        return kick;
    }

    /** 
     * GETTER DE RUNNABLE
     */

    public Runnable getRun1(Player event) {
        Runnable run1 = new Runnable() {

            double x = 0;
            double y = 64;
            double z = 0;

            World player_world = event.getWorld();
            Location player_tp = new Location(player_world, x, y, z);

            int time_run1 = getRegistertemps();

            @Override
            public void run() {

                event.teleport(player_tp);

                System.out.println("Temps: " + time_run1);

                if (time_run1 == 0) {
                    getTask1().cancel();
                    event.kickPlayer(getKickMessage());
                }
                time_run1--;
            }
        };

        return run1;
    }

    public Runnable getRun2() {
        Runnable run2 = new Runnable() {

            @Override
            public void run() {

            }

        };
        return run2;
    }

    public Runnable getRun3() {
        Runnable run3 = new Runnable() {

            @Override
            public void run() {

            }

        };
        return run3;
    }

    /**
     * GETTER ET SETTER DE TACHE
     */
    private BukkitTask tache1;

    public BukkitTask getTask1() {
        return tache1;
    }

    public void setTask1(Player event) {

        BukkitTask tache1 = Bukkit.getScheduler().runTaskTimer(this, getRun1(event), 20, 20);
        this.tache1 = tache1;
    }

    private BukkitTask tache2;

    public BukkitTask getTask2() {
        return tache2;
    }

    public void setTask2() {
        BukkitTask tache2 = Bukkit.getScheduler().runTaskTimer(this, getRun2(), 20, 20);
        this.tache2 = tache2;
    }

    private BukkitTask tache3;

    public BukkitTask getTask3() {
        return tache3;
    }

    public void setTask3() {
        BukkitTask tache3 = Bukkit.getScheduler().runTaskTimer(this, getRun3(), 20, 20);
        this.tache3 = tache3;
    }

    /**
     * GETTER DE COMMANDE
     */
    public Boolean getExecution1(Player event) {
        String player_name = event.getName();
        Boolean commande1 = Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + getRegistertemps() * 20 + " 10 ");
        return commande1;
    }

}
