package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessagePerso {
    


    /**
     * GETTER DE CONFIG MESSAGE
     */
    public static int getRegistertemps() {
        int registertemps = Integer.parseInt(Main.plugin.getConfig().getString("Connection.temps_enregistrement"));
        return registertemps;
    }

    public static String getSoustitre() {
        String soustitre = Main.plugin.getConfig().getString("Connection.soustitre");
        return soustitre;
    }

    public static String getTitre() {
        String titre = Main.plugin.getConfig().getString("Connection.titre");
        return titre;
    }

    public static String getKick() {
        String kick = Main.plugin.getConfig().getString("Connection.message_kick");
        return kick;
    }        



}
