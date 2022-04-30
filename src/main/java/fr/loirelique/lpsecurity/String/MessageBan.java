package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageBan {
    
    public static String getBan() {
        String ban = Main.plugin.getConfig().getString("string.ban");
        return ban;
    }
    public static String getErrorBan() {
        String ban = Main.plugin.getConfig().getString("string.error_ban");
        return ban;
    }
    public static String getAlreadyBan() {
        String ban = Main.plugin.getConfig().getString("string.already_ban");
        return ban;
    }

    public static String setColorBan() {
        String ban = Main.plugin.getConfig().getString("string.color_ban");
        return ban;
    }
    public static String setColorErrorBan() {
        String ban = Main.plugin.getConfig().getString("string.color_error_ban");
        return ban;
    }
    public static String setColorAlreadyBan() {
        String ban = Main.plugin.getConfig().getString("string.color_already_ban");
        return ban;
    }
}
