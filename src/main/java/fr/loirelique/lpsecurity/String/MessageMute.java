package fr.loirelique.lpsecurity.string;

import java.nio.charset.StandardCharsets;

import fr.loirelique.lpsecurity.Main;

public class MessageMute {
    
    public static String getMute() {
        String mute = Main.plugin.getConfig().getString("string.mute");
        mute = new String(mute.getBytes(), StandardCharsets.UTF_8);
        return mute;
    }

    public static String setColorMute() {
        String colorMute = Main.plugin.getConfig().getString("string.color_mute");
        colorMute = new String(colorMute.getBytes(), StandardCharsets.UTF_8);
        return colorMute;
    }

    public static String setColorAlreadyMute() {
        String colorAlreadyMute = Main.plugin.getConfig().getString("string.color_already_mute");
        colorAlreadyMute = new String(colorAlreadyMute.getBytes(), StandardCharsets.UTF_8);
        return colorAlreadyMute;
    }

    public static String getAlreadyMute() {
        String alreadyMute = Main.plugin.getConfig().getString("string.already_mute");
        alreadyMute = new String(alreadyMute.getBytes(), StandardCharsets.UTF_8);
        return alreadyMute;
    }

    public static String setColorErrorMute() {
        String colorErrorMute = Main.plugin.getConfig().getString("string.color_error_mute");
        colorErrorMute = new String(colorErrorMute.getBytes(), StandardCharsets.UTF_8);
        return colorErrorMute;
    }

    public static String getErrorMute() {
        String errorMute = Main.plugin.getConfig().getString("string.error_mute");
        errorMute = new String(errorMute.getBytes(), StandardCharsets.UTF_8);
        return errorMute;
    }
}
