package fr.loirelique.lpsecurity.String;

import java.nio.charset.StandardCharsets;

import fr.loirelique.lpsecurity.Main;

public class MessageUnmute {

    public static String setColorUnmute() {
        String colorUnmute = Main.plugin.getConfig().getString("string.color_unmute");
        colorUnmute = new String(colorUnmute.getBytes(), StandardCharsets.UTF_8);
        return colorUnmute;
    }

    public static String getUnmute() {
        String unMute = Main.plugin.getConfig().getString("string.unmute");
        unMute = new String(unMute.getBytes(), StandardCharsets.UTF_8);
        return unMute;
    }

    public static String setColorAlreadyUnmute() {
        String colorAlreadyUnmute = Main.plugin.getConfig().getString("string.color_already_unmute");
        colorAlreadyUnmute = new String(colorAlreadyUnmute.getBytes(), StandardCharsets.UTF_8);
        return colorAlreadyUnmute;
    }

    public static String getAlreadyUnmute() {
        String alreadyUnmute = Main.plugin.getConfig().getString("string.already_unmute");
        alreadyUnmute = new String(alreadyUnmute.getBytes(), StandardCharsets.UTF_8);
        return alreadyUnmute;
    }

    public static String setColorErrorUnmute() {
        String colorErrorUnmute = Main.plugin.getConfig().getString("string.color_error_unmute");
        colorErrorUnmute = new String(colorErrorUnmute.getBytes(), StandardCharsets.UTF_8);
        return colorErrorUnmute;
    }

    public static String getErrorUnmute() {
        String errorUnmute = Main.plugin.getConfig().getString("string.error_unmute");
        errorUnmute = new String(errorUnmute.getBytes(), StandardCharsets.UTF_8);
        return errorUnmute;
    }
    
}
