package fr.loirelique.lpsecurity;

import java.util.UUID;

import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Information sur la class!LPSECURITY
 *
 */

public class Main extends JavaPlugin implements Listener {

    public static Player player;
    private static Liaison liaison;
    public static RunTasks tache1;
    private static RunJoin runJoin;


/*     private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

    }
 */

    @Override
    public void onEnable() {
        
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        liaison = new Liaison(getConfig().getString("bdd.driver"), getConfig().getString("bdd.host"),
                getConfig().getString("bdd.port"), getConfig().getString("bdd.database"),
                getConfig().getString("bdd.user"), getConfig().getString("bdd.pass"));
        saveDefaultConfig();
        System.out.println("Chargement plugin LPsecurity... ===> OK");

    }

    @Override
    public void onDisable() {
        System.out.println("Arret du plugin LPsecurity... ===> OK");
    }

    @EventHandler
    public void playerBeforeJoinServer(AsyncPlayerPreLoginEvent p) {
        liaison.connect();
        /*
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
         */

    }

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent event) {
        // Variable de déclaration
        player = event.getPlayer();
        final String player_name = player.getName();

        // Au join le message à voire ou le mettre
        int time_run1 = Integer.parseInt(getConfig().getString("Connection.temps_enregistrement"));
        String title = getConfig().getString("Connection.titre");
        String subtitle = getConfig().getString("Connection.soustitre");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + time_run1 * 20 + " 10 ");
        player.sendTitle(title, subtitle);
        
        tache1.runTaskTimer(this, runJoin, 20, 20);

        
    }

    @EventHandler
    private void playerQuitServer(PlayerQuitEvent event) {
        tache1.cancel();
        liaison.disconnect();

    }

}
