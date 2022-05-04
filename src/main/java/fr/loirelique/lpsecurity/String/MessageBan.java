package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageBan {

    public static String getBan() {
        String ban = Main.plugin.getConfig().getString("string.ban");
        return ban;
    }

    public static String getErrorBan() {
        String errorBan = Main.plugin.getConfig().getString("string.error_ban");
        return errorBan;
    }

    public static String getAlreadyBan() {
        String alreadyBan = Main.plugin.getConfig().getString("string.already_ban");
        return alreadyBan;
    }

    public static String setColorBan() {
        String colorBan = Main.plugin.getConfig().getString("string.color_ban");
        return colorBan;
    }

    public static String setColorErrorBan() {
        String colorErrorBan = Main.plugin.getConfig().getString("string.color_error_ban");
        return colorErrorBan;
    }

    public static String setColorAlreadyBan() {
        String colorAlreadyBan = Main.plugin.getConfig().getString("string.color_already_ban");
        return colorAlreadyBan;
    }
}
