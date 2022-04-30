package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageUnban {

    public static String getUnban() {
        String unban1 = Main.plugin.getConfig().getString("string.unban");

        return unban1;
    }

    public static String getAlreadyUnban() {
        String unban2 = Main.plugin.getConfig().getString("string.already_unban");

        return unban2;
    }

    public static String setColorUnban() {
        String unban3 = Main.plugin.getConfig().getString("string.color_unban");

        return unban3;
    }

    public static String setColorAlreadyUnban() {
        String unban4 = Main.plugin.getConfig().getString("string.color_already_unban");

        return unban4;
    }

}
