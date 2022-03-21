package fr.loirelique.lpsecurity.String;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class ConfigMessage {
    


    /**
     * GETTER DE CONFIG MESSAGE
     */
    public static int getRegistertemps() {
        int registertemps = Integer.parseInt(Main.plugin.getConfig().getString("Connection.temps_enregistrement"));
        return registertemps;
    }

    public static int getLogintemps() {
        int logintemps = Integer.parseInt(Main.plugin.getConfig().getString("Connection.temps_indentification"));
        return logintemps;
    }

    public static String getSoustitreRegister() {
        String soustitre = Main.plugin.getConfig().getString("Connection.soustitre_enregistrement");
        return soustitre;
    }

    public static String getTitreRegister() {
        String titre = Main.plugin.getConfig().getString("Connection.titre_enregistrement");
        return titre;
    }

    public static String getSoustitreLogin() {
        String soustitre = Main.plugin.getConfig().getString("Connection.soustitre_indentification");
        return soustitre;
    }

    public static String getTitreLogin() {
        String titre = Main.plugin.getConfig().getString("Connection.titre_indentification");
        return titre;
    }

    public static String getKick() {
        String kick = Main.plugin.getConfig().getString("Connection.message_kick");
        return kick;
    } 

    public static String getMdpError() {
        String mdpError = Main.plugin.getConfig().getString("message_commande.mdp_erreur");
        return mdpError;
    } 
    
    
    public static void sendRegister(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title " + player_name + " times 10 " + ConfigMessage.getRegistertemps() * 20 + " 10 "); 
        p.sendTitle(ConfigMessage.getTitreRegister(), ConfigMessage.getSoustitreRegister()); 
    }
    
    public static void sendLogin(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title " + player_name + " times 10 " + ConfigMessage.getLogintemps() * 20 + " 10 "); 
        p.sendTitle(ConfigMessage.getTitreLogin(), ConfigMessage.getSoustitreLogin()); 
    }



}
