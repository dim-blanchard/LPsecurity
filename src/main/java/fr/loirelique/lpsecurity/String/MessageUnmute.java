package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageUnmute {

    public static String setColorUnmute() {
        String colorUnmute = Main.plugin.getConfig().getString("string.color_unmute");
        return colorUnmute;
    }

    public static String getUnmute() {
        String unMute = Main.plugin.getConfig().getString("string.unmute");
        return unMute;
    }

    public static String setColorAlreadyUnmute() {
        String colorAlreadyUnmute = Main.plugin.getConfig().getString("string.color_already_unmute");
        return colorAlreadyUnmute;
    }

    public static String getAlreadyUnmute() {
        String alreadyUnmute = Main.plugin.getConfig().getString("string.already_unmute");
        return alreadyUnmute;
    }

    public static String setColorErrorUnmute() {
        String colorErrorUnmute = Main.plugin.getConfig().getString("string.color_error_unmute");
        return colorErrorUnmute;
    }

    public static String getErrorUnmute() {
        String errorUnmute = Main.plugin.getConfig().getString("string.error_unmute");
        return errorUnmute;
    }
    
}
