package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageUnban {

    public static String getUnban() {
        String unban = Main.plugin.getConfig().getString("string.already");

        return unban;
    }
}
