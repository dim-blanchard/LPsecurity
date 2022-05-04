package fr.loirelique.lpsecurity.String;

import fr.loirelique.lpsecurity.Main;

public class MessageKick {

    /**
     * Getter de message de configuration.
     */

    public static String getKickOnline() {
        String kickOnline = Main.plugin.getConfig().getString("string.kick_online");
        return kickOnline;
    }

    public static String getKickIp() {
        String kickIp = Main.plugin.getConfig().getString("string.kick_ip");
        return kickIp;
    }

    public static String getKickBan() {
        String kickBan = Main.plugin.getConfig().getString("string.kick_ban");
        return kickBan;
    }

    public static String getKickOvertime() {
        String kickOvertime = Main.plugin.getConfig().getString("string.kick_over_time");
        return kickOvertime;
    }

    public static int getKickOverIp() {
        int kickOverIp = Integer.parseInt(Main.plugin.getConfig().getString("string.kick_over_ip"));
        return kickOverIp;
    }

}
