package fr.loirelique.lpsecurity.String;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
        String errorTempmute = Main.plugin.getConfig().getString("string.error_tempmute");
        errorTempmute = new String(errorTempmute.getBytes(), StandardCharsets.UTF_8);
        return errorTempmute;
    }

    public static String setColorErrorTempmute() {
        String colorErrorTempmute = Main.plugin.getConfig().getString("string.color_error_tempmute");
        colorErrorTempmute = new String(colorErrorTempmute.getBytes(), StandardCharsets.UTF_8);
        return colorErrorTempmute;
    }


    private static ArrayList<String> listDefaultReasonMessage = new ArrayList<String>(); 
    public static void initializelist() {
         String defaultReason=Main.plugin.getConfig().getString("string.default_reason_tempmute");
        defaultReason = new String(defaultReason.getBytes(), StandardCharsets.UTF_8);
        listDefaultReasonMessage.add(defaultReason);
    }
    public static ArrayList<String> getListDefaultReasonMessage() {
        return listDefaultReasonMessage;
    }
    
}
