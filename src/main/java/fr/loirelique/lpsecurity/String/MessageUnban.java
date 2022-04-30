package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageUnban {

    public static String getUnban() {
        String unban = Main.plugin.getConfig().getString("string.debannie");

        return unban;
    }

    public static String getAlreadyUnban() {
        String unban = Main.plugin.getConfig().getString("string.already_debannie");

        return unban;
    }

    public static String setColorDebannie() {
        String unban = Main.plugin.getConfig().getString("string.couleur_debannie");

        return unban;
    }

    public static String setColorAlreadyDebannie() {
        String unban = Main.plugin.getConfig().getString("string.couleur_already_debannie");

        return unban;
    }
    
}
