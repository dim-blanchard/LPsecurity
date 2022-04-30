package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageTempban {

    public static String getTempban() {
        String tempban1 = Main.plugin.getConfig().getString("string.tempban");

        return tempban1;
    }

    public static String getErrorTempban() {
        String tempban2 = Main.plugin.getConfig().getString("string.error_tempban");

        return tempban2;
    }

    public static String getAlreadyTempban() {
        String tempban3 = Main.plugin.getConfig().getString("string.already_tempban");

        return tempban3;
    }

    public static String setColorTempban() {
        String tempban4 = Main.plugin.getConfig().getString("string.color_tempban");

        return tempban4;
    }

    public static String setColorErrorTempban() {
        String tempban5 = Main.plugin.getConfig().getString("string.color_error_tempban");

        return tempban5;
    }

    public static String setColoralreadyTempban() {
        String tempban6 = Main.plugin.getConfig().getString("string.color_already_tempban");

        return tempban6;
    }

}
