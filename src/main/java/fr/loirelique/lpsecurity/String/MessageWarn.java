package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageWarn {

    public static String setColorWarn() {
        String colorWarn = Main.plugin.getConfig().getString("string.color_warn");
        return colorWarn;
    }

    public static String getWarn() {
        String warn = Main.plugin.getConfig().getString("string.warn");
        return warn;
    }

    public static String setColorWarnAndBan() {
        String colorWarnAndBan = Main.plugin.getConfig().getString("string.color_warn_ban");
        return colorWarnAndBan;
    }

    public static String getWarnAndBan() {
        String warnAndBan = Main.plugin.getConfig().getString("string.warn_ban");
        return warnAndBan;
    }

    public static String setColorAlreadyWarnAndBan() {
        String colorAlreadyWarnAndBan = Main.plugin.getConfig().getString("string.color_already_warn_ban");
        return colorAlreadyWarnAndBan;
    }

    public static String getAlreadyWarnAndBan() {
        String alreadyWarnAndBan = Main.plugin.getConfig().getString("string.already_warn_ban");
        return alreadyWarnAndBan;
    }

    public static String setColorErrorWarn() {
        String colorErrorWarn = Main.plugin.getConfig().getString("string.color_error_warn");
        return colorErrorWarn;
    }
    public static String getErrorWarn() {
        String errorWarn = Main.plugin.getConfig().getString("string.error_warn");
        return errorWarn;
    }

    public static String getMotifWarnLvl1() {
        String motif1 = Main.plugin.getConfig().getString("string.motif_warn_lvl_1");
        return motif1;
    }

    public static int getMotifWarnlvl1Degres() {
        int motif1Degres = Integer.parseInt(Main.plugin.getConfig().getString("string.motif_warn_lvl_1_degres"));
        return motif1Degres;
    }

    public static String getMotifWarnLvl1Text() {
        String motif1Text = Main.plugin.getConfig().getString("string.motif_warn_lvl_1_text");
        return motif1Text;
    }
    public static String getMotifWarnLvl2() {
        String motif2 = Main.plugin.getConfig().getString("string.motif_warn_lvl_2");
        return motif2;
    }

    public static int getMotifWarnlvl2Degres() {
        int motif2Degres = Integer.parseInt(Main.plugin.getConfig().getString("string.motif_warn_lvl_2_degres"));
        return motif2Degres;
    }

    public static String getMotifWarnLvl2Text() {
        String motif2Text = Main.plugin.getConfig().getString("string.motif_warn_lvl_2_text");
        return motif2Text;
    }
    public static String getMotifWarnLvl3() {
        String motif3 = Main.plugin.getConfig().getString("string.motif_warn_lvl_3");
        return motif3;
    }

    public static int getMotifWarnlvl3Degres() {
        int motif3Degres = Integer.parseInt(Main.plugin.getConfig().getString("string.motif_warn_lvl_3_degres"));
        return motif3Degres;
    }

    public static String getMotifWarnLvl3Text() {
        String motif3Text = Main.plugin.getConfig().getString("string.motif_warn_lvl_3_text");
        return motif3Text;
    }

    public static int getWarnDegresMax() {
        int warnDegresMax = Integer.parseInt(Main.plugin.getConfig().getString("string.warn_degres_max"));
        return warnDegresMax;
    }


}
