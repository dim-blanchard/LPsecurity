package fr.loirelique.lpsecurity.string;

import java.nio.charset.StandardCharsets;

import fr.loirelique.lpsecurity.Main;

public class MessageKick {

    /**
     * Getter de message de configuration.
     */

    public static String getKickOnline() {
        String kickOnline = Main.plugin.getConfig().getString("string.kick_online");
        kickOnline = new String(kickOnline.getBytes(), StandardCharsets.UTF_8);
        return kickOnline;
    }

    public static String getKickIp() {
        String kickIp = Main.plugin.getConfig().getString("string.kick_ip");
        kickIp = new String(kickIp.getBytes(), StandardCharsets.UTF_8);
        return kickIp;
    }

    public static String getKickBan() {
        String kickBan = Main.plugin.getConfig().getString("string.kick_ban");
        kickBan = new String(kickBan.getBytes(), StandardCharsets.UTF_8);
        return kickBan;
    }

    public static String getKickOvertime() {
        String kickOvertime = Main.plugin.getConfig().getString("string.kick_over_time");
        kickOvertime = new String(kickOvertime.getBytes(), StandardCharsets.UTF_8);
        return kickOvertime;
    }

    public static int getKickOverIp() {
        int kickOverIp = Integer.parseInt(Main.plugin.getConfig().getString("string.kick_over_ip"));
        return kickOverIp;
    }

}
