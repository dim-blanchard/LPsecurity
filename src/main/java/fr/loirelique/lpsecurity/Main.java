package fr.loirelique.lpsecurity;

import org.bukkit.Bukkit;
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
       
        System.out.println("Chargement plugin LPsecurity... ===> OK");
        
       
    }

    @Override
    public void onDisable(){

        System.out.println("Arret du plugin LPsecurity... ===> OK");
    }

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent event){
        player = event.getPlayer(); 
        player.sendMessage("Salut bienvuenue sur le serveur !");
    }





}
