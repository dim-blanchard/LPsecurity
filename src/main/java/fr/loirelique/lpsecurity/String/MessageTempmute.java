package fr.loirelique.lpsecurity.String;

import java.nio.charset.StandardCharsets;

import fr.loirelique.lpsecurity.Main;

public class MessageTempmute {

    public static String setColorTempmute() {
        String colorTempmute = Main.plugin.getConfig().getString("string.color_tempmute");
        colorTempmute = new String(colorTempmute.getBytes(), StandardCharsets.UTF_8);
        return colorTempmute;
    }

    public static String getTempmute() {
        String tempmute = Main.plugin.getConfig().getString("string.tempmute");
        tempmute = new String(tempmute.getBytes(), StandardCharsets.UTF_8);
        return tempmute;
    }

    public static String setColoralreadyTempmute() {
        String colorAlreadyTempmute = Main.plugin.getConfig().getString("string.color_already_tempmute");
        colorAlreadyTempmute = new String(colorAlreadyTempmute.getBytes(), StandardCharsets.UTF_8);
        return colorAlreadyTempmute;
    }

    public static String getAlreadyTempmute() {
        String alreadyTempmute = Main.plugin.getConfig().getString("string.already_tempmute");
        alreadyTempmute = new String(alreadyTempmute.getBytes(), StandardCharsets.UTF_8);
        return alreadyTempmute;
    }

    public static String getErrorTempmute() {
        String errorTempmute = Main.plugin.getConfig().getString("string.error_tempute");
        errorTempmute = new String(errorTempmute.getBytes(), StandardCharsets.UTF_8);
        return errorTempmute;
    }

    public static String setColorErrorTempmute() {
        String colorErrorTempmute = Main.plugin.getConfig().getString("string.color_error_tempmute");
        colorErrorTempmute = new String(colorErrorTempmute.getBytes(), StandardCharsets.UTF_8);
        return colorErrorTempmute;
    }
    
}
