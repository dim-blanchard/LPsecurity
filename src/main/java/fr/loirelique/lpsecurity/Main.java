package fr.loirelique.lpsecurity;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;



/**
 * Information sur la class!LPSECURITY
 *
 */



public class Main extends JavaPlugin implements Listener{
    
    public static Liaison liaison;
    private static String player_name;
    private static BukkitTask task1;
    private static float speed_default;
    private static Long player_time;
 
    

    @Override
    public void onEnable(){ 
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        liaison = new Liaison(getConfig().getString("bdd.driver"),getConfig().getString("bdd.host"),getConfig().getString("bdd.port"), getConfig().getString("bdd.database"),getConfig().getString("bdd.user"), getConfig().getString("bdd.pass"));
        saveDefaultConfig();
        System.out.println("Chargement plugin LPsecurity... ===> OK");
        
    }

    @Override
    public void onDisable(){
        System.out.println("Arret du plugin LPsecurity... ===> OK");
    }


    @EventHandler 
    public void playerBeforeJoinServer(AsyncPlayerPreLoginEvent event){
        final String player = event.getName();
        final UUID playerUuid = event.getUniqueId();
        liaison.connect();
        liaison.isAccount(playerUuid);
        System.out.println(player);

        
        //si le nom du joueur ce trouve dans la bdd alors appler methode login 
        //si le nom du joueur ne ce trouve pas dans la bdd alors appler methode register
        //si le nom du joueur correspond à un joueur deja en ligne le kick ou si le joueur et bannie.
        
    }

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent event){
       final Player player = event.getPlayer(); 
        player_name = player.getName();
        speed_default = player.getWalkSpeed();
        player_time = player.getPlayerTime();

        String title = getConfig().getString("Connection.titre") ;
        String subtitle =  getConfig().getString("Connection.soustitre");
        
        //5 seconde == 100 ticks
        int time = Integer.parseInt(getConfig().getString("Connection.temps_enregistrement"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+player_name+" times 10"+time*20 +"10 ");
        player.sendTitle(title, subtitle);
        player.sendMessage("/register <pass> <pass>");
        System.out.println("Vitess: " +speed_default);
        player.setWalkSpeed(0f);
        System.out.println("temps joueur: " +player_time);
     
        

        // Run pour 
        Runnable run1 = new Runnable() {

            int time = Integer.parseInt(getConfig().getString("Connection.temps_enregistrement"));

            @Override
            public void run() {
                System.out.println("Temps: "+time);

                if(time == 5){
                    System.out.println("TIME 5");
                    
                }

                if(time == 0)
                {
                    player.kickPlayer(getConfig().getString("Connection.message_kick"));
                    task1.cancel();
                }
                time--;
            }

          
        };

        task1= Bukkit.getScheduler().runTaskTimer(this,run1 , 20, 20);


       

    }


    @EventHandler
    private void playerQuitServer(PlayerQuitEvent event){
       final Player player = event.getPlayer();
       task1.cancel();
       player.setWalkSpeed(speed_default);
       System.out.println(speed_default);
    }



}
