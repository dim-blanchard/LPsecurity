package fr.loirelique.lpsecurity;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;



/**
 * Information sur la class!LPSECURITY
 *
 */



public class Main extends JavaPlugin implements Listener{
    

    @Override
    public void onEnable(){
        // Actions à effectuer au démarrage du plugin, c'est-à-dire :
        //   - Au démarrage du serveur
        //   - Après un /reload

        System.out.println("Plugin pour la sécurité Erizia activer.");
    }


    @Override
    public void onDisable(){
        // Actions à effectuer à la désactivation du plugin
        //   - A l'extinction du serveur
        //   - Pendant un /reload

        System.out.println("Plugin pour la sécurité Erizia désactiver.");
    }

    public static void main( String[] args )
    {
        System.out.println( "TEST" );
    }


}
