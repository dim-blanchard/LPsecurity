package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageMute {
    
    public static String getMute() {
        String mute = Main.plugin.getConfig().getString("string.mute");
        return mute;
    }

    public static String setColorMute() {
        String colorMute = Main.plugin.getConfig().getString("string.color_mute");
        return colorMute;
    }

    public static String setColorAlreadyMute() {
        String colorAlreadyMute = Main.plugin.getConfig().getString("string.color_already_mute");
        return colorAlreadyMute;
    }

    public static String getAlreadyMute() {
        String alreadyMute = Main.plugin.getConfig().getString("string.already_mute");
        return alreadyMute;
    }

    public static String setColorErrorMute() {
        String colorErrorMute = Main.plugin.getConfig().getString("string.color_error_mute");
        return colorErrorMute;
    }

    public static String getErrorMute() {
        String errorMute = Main.plugin.getConfig().getString("string.error_mute");
        return errorMute;
    }
}
