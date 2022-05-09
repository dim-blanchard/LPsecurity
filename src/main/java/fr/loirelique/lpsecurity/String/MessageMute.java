package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageMute {
    
    public static String getMute() {
        String mute = Main.plugin.getConfig().getString("string.mute");
        return mute;
    }

    public static String setColorMute() {
        String mute = Main.plugin.getConfig().getString("string.mute");
        return mute;
    }

    public static String setColorAlreadyMute() {
        String mute = Main.plugin.getConfig().getString("string.mute");
        return mute;
    }

    public static String getAlreadyMute() {
        String mute = Main.plugin.getConfig().getString("string.mute");
        return mute;
    }

    public static String setColorErrorMute() {
        String mute = Main.plugin.getConfig().getString("string.mute");
        return mute;
    }

    public static String getErrorMute() {
        String mute = Main.plugin.getConfig().getString("string.mute");
        return mute;
    }
}
