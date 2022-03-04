package fr.loirelique.lpsecurity;

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
      
        String player_name = player.getName();
        float speed_default = player.getWalkSpeed();
        Long player_time = player.getPlayerTime();


        String title = getConfig().getString("premiereConnection.titre") ;
        String subtitle =  getConfig().getString("premiereConnection.soustitre");
        
        //5 seconde == 100 ticks
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+player_name+" times 10 1200 10 ");
        player.sendTitle(title, subtitle);
    
        System.out.println("Vitess: " +speed_default);
        player.setWalkSpeed(0f);
        System.out.println("temps joueur: " +player_time);

        
        if (player_time > player_time+10L) {

            System.out.println("exclu");
            
        }

    }

   



}
