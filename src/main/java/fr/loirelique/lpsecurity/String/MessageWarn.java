package fr.loirelique.lpsecurity.string;

import java.nio.charset.StandardCharsets;

import fr.loirelique.lpsecurity.Main;

public class MessageWarn {

    public static String setColorWarn() {
        String colorWarn = Main.plugin.getConfig().getString("string.color_warn");
        colorWarn = new String(colorWarn.getBytes(), StandardCharsets.UTF_8);
        return colorWarn;
    }

    public static String getWarn() {
        String warn = Main.plugin.getConfig().getString("string.warn");
        warn = new String(warn.getBytes(), StandardCharsets.UTF_8);
        return warn;
    }

    public static String setColorWarnAndBan() {
        String colorWarnAndBan = Main.plugin.getConfig().getString("string.color_warn_ban");
        colorWarnAndBan = new String(colorWarnAndBan.getBytes(), StandardCharsets.UTF_8);
        return colorWarnAndBan;
    }

    public static String getWarnAndBan() {
        String warnAndBan = Main.plugin.getConfig().getString("string.warn_ban");
        warnAndBan = new String(warnAndBan.getBytes(), StandardCharsets.UTF_8);
        return warnAndBan;
    }

    public static String setColorAlreadyWarnAndBan() {
        String colorAlreadyWarnAndBan = Main.plugin.getConfig().getString("string.color_already_warn_ban");
        colorAlreadyWarnAndBan = new String(colorAlreadyWarnAndBan.getBytes(), StandardCharsets.UTF_8);
        return colorAlreadyWarnAndBan;
    }

    public static String getAlreadyWarnAndBan() {
        String alreadyWarnAndBan = Main.plugin.getConfig().getString("string.already_warn_ban");
        alreadyWarnAndBan = new String(alreadyWarnAndBan.getBytes(), StandardCharsets.UTF_8);
        return alreadyWarnAndBan;
    }

    public static String setColorErrorWarn() {
        String colorErrorWarn = Main.plugin.getConfig().getString("string.color_error_warn");
        colorErrorWarn = new String(colorErrorWarn.getBytes(), StandardCharsets.UTF_8);
        return colorErrorWarn;
    }
    public static String getErrorWarn() {
        String errorWarn = Main.plugin.getConfig().getString("string.error_warn");
        errorWarn = new String(errorWarn.getBytes(), StandardCharsets.UTF_8);
        return errorWarn;
    }

    public static String getMotifWarnLvl1() {
        String motif1 = Main.plugin.getConfig().getString("string.motif_warn_lvl_1");
        motif1 = new String(motif1.getBytes(), StandardCharsets.UTF_8);
        return motif1;
    }

    public static int getMotifWarnlvl1Degres() {
        int motif1Degres = Integer.parseInt(Main.plugin.getConfig().getString("string.motif_warn_lvl_1_degres"));
        return motif1Degres;
    }

    public static String getMotifWarnLvl1Text() {
        String motif1Text = Main.plugin.getConfig().getString("string.motif_warn_lvl_1_text");
        motif1Text = new String(motif1Text.getBytes(), StandardCharsets.UTF_8);
        return motif1Text;
    }
    public static String getMotifWarnLvl2() {
        String motif2 = Main.plugin.getConfig().getString("string.motif_warn_lvl_2");
        motif2 = new String(motif2.getBytes(), StandardCharsets.UTF_8);
        return motif2;
    }

    public static int getMotifWarnlvl2Degres() {
        int motif2Degres = Integer.parseInt(Main.plugin.getConfig().getString("string.motif_warn_lvl_2_degres"));
        return motif2Degres;
    }

    public static String getMotifWarnLvl2Text() {
        String motif2Text = Main.plugin.getConfig().getString("string.motif_warn_lvl_2_text");
        motif2Text = new String(motif2Text.getBytes(), StandardCharsets.UTF_8);
        return motif2Text;
    }
    public static String getMotifWarnLvl3() {
        String motif3 = Main.plugin.getConfig().getString("string.motif_warn_lvl_3");
        motif3 = new String(motif3.getBytes(), StandardCharsets.UTF_8);
        return motif3;
    }

    public static int getMotifWarnlvl3Degres() {
        int motif3Degres = Integer.parseInt(Main.plugin.getConfig().getString("string.motif_warn_lvl_3_degres"));
        return motif3Degres;
    }

    public static String getMotifWarnLvl3Text() {
        String motif3Text = Main.plugin.getConfig().getString("string.motif_warn_lvl_3_text");
        motif3Text = new String(motif3Text.getBytes(), StandardCharsets.UTF_8);
        return motif3Text;
    }

    public static int getWarnDegresMax() {
        int warnDegresMax = Integer.parseInt(Main.plugin.getConfig().getString("string.warn_degres_max"));
        return warnDegresMax;
    }


}
