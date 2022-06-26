package fr.loirelique.lpsecurity.string;

import java.nio.charset.StandardCharsets;

import fr.loirelique.lpsecurity.Main;

public class MessageUnban {

    public static String getUnban() {
        String unban = Main.plugin.getConfig().getString("string.unban");
        unban = new String(unban.getBytes(), StandardCharsets.UTF_8);
        return unban;
    }

    public static String getErrorUnban() {
        String errorUnban = Main.plugin.getConfig().getString("string.error_unban");
        errorUnban = new String(errorUnban.getBytes(), StandardCharsets.UTF_8);
        return errorUnban;
    }

    public static String getAlreadyUnban() {
        String alreadyUnban = Main.plugin.getConfig().getString("string.already_unban");
        alreadyUnban = new String(alreadyUnban.getBytes(), StandardCharsets.UTF_8);
        return alreadyUnban;
    }

    public static String setColorUnban() {
        String colorUnban = Main.plugin.getConfig().getString("string.color_unban");
        colorUnban = new String(colorUnban.getBytes(), StandardCharsets.UTF_8);
        return colorUnban;
    }

    public static String setColorErrorUnban() {
        String colorErrorUnban = Main.plugin.getConfig().getString("string.color_error_unban");
        colorErrorUnban = new String(colorErrorUnban.getBytes(), StandardCharsets.UTF_8);
        return colorErrorUnban;
    }

    public static String setColorAlreadyUnban() {
        String colorAlreadyUnban = Main.plugin.getConfig().getString("string.color_already_unban");
        colorAlreadyUnban = new String(colorAlreadyUnban.getBytes(), StandardCharsets.UTF_8);
        return colorAlreadyUnban;
    }

}
