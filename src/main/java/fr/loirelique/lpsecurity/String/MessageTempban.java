package fr.loirelique.lpsecurity.string;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import fr.loirelique.lpsecurity.Main;

public class MessageTempban {

    public static String getTempban() {
        String tempban = Main.plugin.getConfig().getString("string.tempban");
        tempban = new String(tempban.getBytes(), StandardCharsets.UTF_8);
        return tempban;
    }

    public static String getErrorTempban() {
        String errorTempban = Main.plugin.getConfig().getString("string.error_tempban");
        errorTempban = new String(errorTempban.getBytes(), StandardCharsets.UTF_8);
        return errorTempban;
    }

    public static String getAlreadyTempban() {
        String alreadyTempban = Main.plugin.getConfig().getString("string.already_tempban");
        alreadyTempban = new String(alreadyTempban.getBytes(), StandardCharsets.UTF_8);
        return alreadyTempban;
    }

    public static String setColorTempban() {
        String colorTempban = Main.plugin.getConfig().getString("string.color_tempban");
        colorTempban  = new String(colorTempban .getBytes(), StandardCharsets.UTF_8);
        return colorTempban;
    }

    public static String setColorErrorTempban() {
        String colorErrorTempban = Main.plugin.getConfig().getString("string.color_error_tempban");
        colorErrorTempban = new String(colorErrorTempban.getBytes(), StandardCharsets.UTF_8);
        return colorErrorTempban;
    }

    public static String setColoralreadyTempban() {
        String colorAlreadyTempban = Main.plugin.getConfig().getString("string.color_already_tempban");
        colorAlreadyTempban = new String(colorAlreadyTempban.getBytes(), StandardCharsets.UTF_8);
        return colorAlreadyTempban;
    }

    private static ArrayList<String> listDefaultReasonMessage = new ArrayList<String>(); 
    public static void initializelist() {
         String defaultReason=Main.plugin.getConfig().getString("string.default_reason_tempban");
        defaultReason = new String(defaultReason.getBytes(), StandardCharsets.UTF_8);
        listDefaultReasonMessage.add(defaultReason);
    }
    public static ArrayList<String> getListDefaultReasonMessage() {
        return listDefaultReasonMessage;
    }

}
