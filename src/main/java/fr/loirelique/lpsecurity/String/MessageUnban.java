package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageUnban {

    public static String getUnban() {
        String unban = Main.plugin.getConfig().getString("string.unban");

        return unban;
    }

    public static String getErrorUnban() {
        String errorUnban = Main.plugin.getConfig().getString("string.error_unban");

        return errorUnban;
    }

    public static String getAlreadyUnban() {
        String alreadyUnban = Main.plugin.getConfig().getString("string.already_unban");

        return alreadyUnban;
    }

    public static String setColorUnban() {
        String colorUnban = Main.plugin.getConfig().getString("string.color_unban");

        return colorUnban;
    }

    public static String setColorErrorUnban() {
        String colorErrorUnban = Main.plugin.getConfig().getString("string.color_error_unban");

        return colorErrorUnban;
    }

    public static String setColorAlreadyUnban() {
        String colorAlreadyUnban = Main.plugin.getConfig().getString("string.color_already_unban");

        return colorAlreadyUnban;
    }

}
