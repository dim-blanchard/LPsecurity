package fr.loirelique.lpsecurity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
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

    /*
     * 
     * 
     * private static Liaison connection1 = new
     * Liaison(ConfigurationBdd.getDriver(), ConfigurationBdd.getHost(),
     * ConfigurationBdd.getPort(),ConfigurationBdd.getData(),
     * ConfigurationBdd.getUser(), ConfigurationBdd.getPass());
     */

    /*
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
     * 
     * 
     * }
     */
    // Attribut de la classe main

    // Get Config Base de données
    private String getDriver() {
        String driver = getConfig().getString("bdd.driver");
        return driver;
    }

   

    private String getHost() {
        String host = getConfig().getString("bdd.host");
        return host;
    }

    private String getPort() {
        String port = getConfig().getString("bdd.port");
        return port;
    }

    private String getData() {
        String data = getConfig().getString("bdd.database");
        return data;
    }

    private String getUser() {
        String user = getConfig().getString("bdd.user");
        return user;
    }

    private String getPass() {
        String pass = getConfig().getString("bdd.pass");
        return pass;
    }

    // Get config message
    private int getRegisterTemps() {
        int registertemps = Integer.parseInt(getConfig().getString("Connection.temps_enregistrement"));
        return registertemps;
    }

    private String getSoustitre() {
        String soustitre = getConfig().getString("Connection.soustitre");
        return soustitre;
    }

    private String getTitre() {
        String titre = getConfig().getString("Connection.titre");
        return titre;
    }

    private String getMessKick() {
        String kick = getConfig().getString("Connection.message_kick");
        return kick;
    }

    /// Get Runnable
    private Runnable getRunnable1(Player event) {
        Runnable run1 = new Runnable() {

            double x = 0;
            double y = 66;
            double z = 0;

            World player_world = event.getWorld();
            Location player_tp = new Location(player_world, x, y, z);

            int time_run1 = getRegisterTemps();


            @Override
            public void run() {

                event.teleport(player_tp);

                System.out.println("Temps: " + time_run1);


                if (time_run1 == 0) {
                    getTask1().cancel();
                    event.kickPlayer(getMessKick());
                }
                time_run1--;
            }
        };
        return run1;

    }

    private Runnable getRunnable2() {
        Runnable run2 = new Runnable() {

            @Override
            public void run() {

            }

        };
        return run2;
    }

    private Runnable getRunnable3() {
        Runnable run3 = new Runnable() {

            @Override
            public void run() {

            }

        };
        return run3;
    }

    // Getter et setter de tache

    private BukkitTask tache1;
    public BukkitTask getTask1() {
        return tache1;
    }
    public void setTask1(Player event) { 
        BukkitTask tache1 = Bukkit.getScheduler().runTaskTimer(this, getRunnable1(event), 20, 20);
        this.tache1 = tache1;
    }



    // Getter et setter de tache
    private BukkitTask getTask2() {
        BukkitTask tache2 = Bukkit.getScheduler().runTaskTimer(this, getRunnable2(), 20, 20);
        return tache2;
    }

    // Getter et setter de tache
    private BukkitTask getTask3() {
        BukkitTask tache3 = Bukkit.getScheduler().runTaskTimer(this, getRunnable3(), 20, 20);
        return tache3;
    }

    // Getter de commande
    private Boolean getCommande1(Player p) {
        String player_name = p.getName();
        Boolean commande1 = Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + getRegisterTemps() * 20 + " 10 ");
        return commande1;
    }

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent event) {

        final Player player = event.getPlayer();
        final String player_name = player.getName();

        getCommande1(player);
        player.sendTitle(getTitre(), getSoustitre());

        //getTask1(player);

        setTask1(player);
        

    }

    @Override
    public void onEnable() {

        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        saveDefaultConfig();

        System.out.println("Chargement plugin LPsecurity... ===> OK");
    }

    @Override
    public void onDisable() {
        System.out.println("Arret du plugin LPsecurity... ===> OK");
    }

}
