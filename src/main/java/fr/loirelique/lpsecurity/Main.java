package fr.loirelique.lpsecurity;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
//import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerPreLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Information sur la class!LPSECURITY
 *
 */

public class Main extends JavaPlugin implements Listener {

    public static Liaison liaison;
    private static BukkitTask task1;

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
        /* UUID player_uuid = p.getUniqueId();
        try {
            // si joueur dans la bdd
            if (liaison.isAccount(player_uuid) == true) {

                // si joueur et en ligne
                if (condition) {
                    p.disallow(Result.KICK_OTHER, "Joueur deja en ligne");
                }

                // si joueur et bannie
                if (condition) {
                    p.disallow(Result.KICK_OTHER, "Tu es bannie du serveur");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        } */

    }

    @EventHandler
    public void playerJoinServer(PlayerJoinEvent event) {
        // Variable de déclaration
        final Player player = event.getPlayer();
        final UUID player_uuid = player.getUniqueId();
        final String player_name = player.getName();

        // Thread Permet de definir le temps avant déconnection
        Runnable run1 = new Runnable() {

            // Variable utile au fonctionnement
            String title = getConfig().getString("Connection.titre");
            int time_run1 = Integer.parseInt(getConfig().getString("Connection.temps_enregistrement"));
            String subtitle = getConfig().getString("Connection.soustitre");
            double x = 205;
            double y = 69;
            double z = 235;
            World player_world = player.getWorld();
            Location player_tp = new Location(player_world, x, y, z);

            // Afficher le texte de bienvenue.(5 seconde == 100 ticks)
            {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                        "title " + player_name + " times 10 " + time_run1 * 20 + " 10 ");
                player.sendTitle(title, subtitle);
            }

            @Override
            public void run() {
                player.teleport(player_tp);
                System.out.println("Temps: " + time_run1);

                if (time_run1 == 0) {
                    liaison.disconnect();
                    player.kickPlayer(getConfig().getString("Connection.message_kick"));
                    task1.cancel();
                }
                time_run1--;
            }

        };

        try {
            if (liaison.isAccount(player_uuid) == true) {

                // si il est bannie ou si il est deja en ligne alors on le kick et deco la bdd.
                // afficher texte à l'écran + 120 section pour ce login.
                // Bloquer le personnage au spawn et attendre le retour de login
            } else if (liaison.isAccount(player_uuid) == false) {

                task1 = Bukkit.getScheduler().runTaskTimer(this, run1, 20, 20);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    @EventHandler
    private void playerQuitServer(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        task1.cancel();
        liaison.disconnect();
    }

}
