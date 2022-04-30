package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageTempban {
    
    public static String getTempban() {
        String unban = Main.plugin.getConfig().getString("string.debannie");

        return unban;
    }

    public static String getTempban2() {
        String unban = Main.plugin.getConfig().getString("string.already_debannie");

        return unban;
    }
    public static String getTempban3() {
        String unban = Main.plugin.getConfig().getString("string.debannie");

        return unban;
    }

}
