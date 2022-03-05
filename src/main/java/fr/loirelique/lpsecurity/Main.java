package fr.loirelique.lpsecurity;


import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;



/**
 * Information sur la class!LPSECURITY
 *
 */



public class Main extends JavaPlugin implements Listener{
    
    public static Liaison liaison;
    public static Player player;
    private static String player_name;
    private static Runnable task1;


    //private static HashMap<Integer,Runnable> tasks = new HashMap<Integer,Runnable>();
    
    private static Map<Integer, Runnable> tasks = new HashMap<>();
    

    @Override
    public void onEnable(){ 
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        saveDefaultConfig();
        System.out.println("Chargement plugin LPsecurity... ===> OK");
        
       
    }

    @Override
    public void onDisable(){
        System.out.println("Arret du plugin LPsecurity... ===> OK");
    }

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent event){
        player = event.getPlayer(); 
        player_name = player.getName();
        float speed_default = player.getWalkSpeed();
        Long player_time = player.getPlayerTime();

        tasks.put(1," task1");
      
        

        


       

        String title = getConfig().getString("premiereConnection.titre") ;
        String subtitle =  getConfig().getString("premiereConnection.soustitre");
        
        //5 seconde == 100 ticks
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+player_name+" times 10 1200 10 ");
        player.sendTitle(title, subtitle);
    
        System.out.println("Vitess: " +speed_default);
        player.setWalkSpeed(0f);
        System.out.println("temps joueur: " +player_time);
     

        task1 = new Runnable() {

            int time = 10;

            @Override
            public void run() {
                System.out.println("Temps: "+time);

                if(time == 5){
                    System.out.println("TIME 5");
                    player.kickPlayer("Exclu");
                }

                if(time == 0)
                {
                    
                    Bukkit.getScheduler().cancelTask();
                }
                time--;
            }
        };
        

        Bukkit.getScheduler().runTaskTimer(this, , 20, 20);


    }
}
