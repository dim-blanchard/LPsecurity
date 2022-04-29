package fr.loirelique.lpsecurity.String;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class MessageLogin {
    public static String getLoginTitle() {
        String titre = Main.plugin.getConfig().getString("string.login_title");
        return titre;
    }

    public static String getLoginSubtitles() {
        String soustitre = Main.plugin.getConfig().getString("string.login_subtitles");
        return soustitre;
    }

    public static int getLoginTime() {
        int logintemps = Integer.parseInt(Main.plugin.getConfig().getString("string.login_time"));
        return logintemps;
    }

    public static String getErrorLogin() {
        String errorlogin = Main.plugin.getConfig().getString("string.error_login");
        return errorlogin;
    }

    public static String getErrorLoginPass() {
        String errorLoginPass = Main.plugin.getConfig().getString("string.error_login_pass");
        return errorLoginPass;
    }

    public static String getAfterLoginTitle() {
        String afterLoginTitle = Main.plugin.getConfig().getString("string.after_login_title");
        return afterLoginTitle;
    }

    public static String getAfterLoginSubtitles() {
        String afterLoginSubtitles = Main.plugin.getConfig().getString("string.after_login_subtitles");
        return afterLoginSubtitles;
    }

    public static void sendLogin(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + MessageLogin.getLoginTime() * 20 + " 10 ");
        p.sendTitle(MessageLogin.getLoginTitle(), MessageLogin.getLoginSubtitles());
    }

    public static void sendAfterLogin(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + player_name + " times 10 20 10 ");
        p.sendTitle(MessageLogin.getAfterLoginTitle(), MessageLogin.getAfterLoginSubtitles());
    }
}
