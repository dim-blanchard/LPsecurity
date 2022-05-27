package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageTempmute {

    public static String setColorTempmute() {
        String colorTempmute = Main.plugin.getConfig().getString("string.color_tempmute");
        return colorTempmute;
    }

    public static String getTempmute() {
        String tempmute = Main.plugin.getConfig().getString("string.tempmute");
        return tempmute;
    }

    public static String setColoralreadyTempmute() {
        String colorAlreadyTempmute = Main.plugin.getConfig().getString("string.color_already_tempmute");
        return colorAlreadyTempmute;
    }

    public static String getAlreadyTempmute() {
        String alreadyTempmute = Main.plugin.getConfig().getString("string.already_tempmute");
        return alreadyTempmute;
    }

    public static String getErrorTempmute() {
        String errorTempmute = Main.plugin.getConfig().getString("string.error_tempute");
        return errorTempmute;
    }

    public static String setColorErrorTempmute() {
        String colorErrorTempmute = Main.plugin.getConfig().getString("string.color_error_tempmute");
        return colorErrorTempmute;
    }
    
}
