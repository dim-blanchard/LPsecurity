package fr.loirelique.lpsecurity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

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

    

    /**
     * 
     * GETTER ET SETTER DE CONFIGURATION DE LA BDD
     * 
     * 
     */

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


     /**
     * 
     * GETTER ET SETTER DE CONFIG MESSAGE
     * 
     * 
     */   

    private int registertemps;
    public int getRegistertemps() {
        return registertemps;
    }

    public void setRegistertemps() {
        int registertemps = Integer.parseInt(getConfig().getString("Connection.temps_enregistrement"));
        this.registertemps = registertemps;
    }
    ////////////////////////
    private String soustitre;
    public String getSoustitreMessage(){
        return soustitre;
    }
    public void setSoustitreMessage() {
        String soustitre = getConfig().getString("Connection.soustitre");
        this.soustitre = soustitre;
    }
    ////////////////////////
    private String titre;
    public String getTitreMessage() {
        return titre;
    }
    public void setTitreMessage() {
        String titre = getConfig().getString("Connection.titre");
        this.titre = titre;
    }
    /////////////////////////
    private String kick;
    public String getKickMessage() {
        return kick;
    }
    public void setKickMessage() {
        String kick = getConfig().getString("Connection.message_kick");
        this.kick = kick;
    }

    /**
     * 
     * GETTER ET SETTER DE RUNNABLE
     * 
     * 
     */

    private Runnable run1;
    public Runnable getRun1() {
        return run1;
    }

    public void setRun1(Player event) {
        Runnable run1 = new Runnable() {

            double x = 0;
            double y = 66;
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
        this.run1 = run1;
    }

    private Runnable run2;
    public Runnable getRun2() {
        return run2;
    }
    public void setRun2() {
        Runnable run2 = new Runnable() {

            @Override
            public void run() {

            }

        }; 
        this.run2 = run2;
    }
   
    private Runnable run3;
    public Runnable getRun3() {
        return run3;
    }
    public void setRun3() {
        Runnable run3 = new Runnable() {

            @Override
            public void run() {

            }

        }; 
        this.run3 = run3;
    }
    /**
     * 
     * GETTER ET SETTER DE TACHE
     * 
     * 
     */
    private BukkitTask tache1;
    public BukkitTask getTask1() {
        return tache1;
    }

    public void setTask1(Player event) {
        BukkitTask tache1 = Bukkit.getScheduler().runTaskTimer(this, getRun1(), 20, 20);
        this.tache1 = tache1;
    }

    private BukkitTask tache2;
    public BukkitTask getTask2() {
        return tache2;
    }

    public void setTask2(Player event) {
        BukkitTask tache2 = Bukkit.getScheduler().runTaskTimer(this, getRun2(), 20, 20);
        this.tache2 = tache2;
    }

    private BukkitTask tache3;
    public BukkitTask getTask3() {
        return tache3;
    }

    public void setTask3(Player event) {
        BukkitTask tache3 = Bukkit.getScheduler().runTaskTimer(this, getRun3(), 20, 20);
        this.tache3 = tache3;
    }


    /**
     * 
     * GETTER ET SETTER DE COMMANDE
     * 
     * 
     */

    private Boolean commande1;
    public Boolean getCommande1() {
        return commande1;
    }

    public void setCommande1(Player event) {
        String player_name = event.getName();
        Boolean commande1 = Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + getRegistertemps() * 20 + " 10 ");
        this.commande1 = commande1;
    }
   


    /**
     * 
     * EVENT PLAYER JOIN EVENT
     * 
     * 
     */    

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent event) {

        final Player player = event.getPlayer();
        final String player_name = player.getName();

        setCommande1(player);
        player.sendTitle(getTitreMessage(), getSoustitreMessage());
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
