package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageTempban {

    public static String getTempban() {
        String tempban = Main.plugin.getConfig().getString("string.tempban");

        return tempban;
    }

    public static String getErrorTempban() {
        String errorTempban = Main.plugin.getConfig().getString("string.error_tempban");

        return errorTempban;
    }

    public static String getAlreadyTempban() {
        String alreadyTempban = Main.plugin.getConfig().getString("string.already_tempban");

        return alreadyTempban;
    }

    public static String setColorTempban() {
        String colorTempban = Main.plugin.getConfig().getString("string.color_tempban");

        return colorTempban;
    }

    public static String setColorErrorTempban() {
        String colorErrorTempban = Main.plugin.getConfig().getString("string.color_error_tempban");

        return colorErrorTempban;
    }

    public static String setColoralreadyTempban() {
        String colorAlreadyTempban = Main.plugin.getConfig().getString("string.color_already_tempban");

        return colorAlreadyTempban;
    }

}
