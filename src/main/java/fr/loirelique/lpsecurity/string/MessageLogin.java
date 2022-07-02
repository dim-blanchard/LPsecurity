package fr.loirelique.lpsecurity.string;

import java.nio.charset.StandardCharsets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class MessageLogin {
    public static String getLoginTitle() {
        String titre = Main.plugin.getConfig().getString("string.title_login");
        titre = new String(titre.getBytes(), StandardCharsets.UTF_8);
        return titre;
    }

    public static String getLoginSubtitles() {
        String soustitre = Main.plugin.getConfig().getString("string.subtitles_login");
        soustitre = new String(soustitre.getBytes(), StandardCharsets.UTF_8);
        return soustitre;
    }

    public static int getLoginTime() {
        int logintemps = Integer.parseInt(Main.plugin.getConfig().getString("string.time_login"));
        return logintemps;
    }

    public static String getWrongLogin() {
        String wronglogin = Main.plugin.getConfig().getString("string.wrong_login");
        wronglogin = new String(wronglogin.getBytes(), StandardCharsets.UTF_8);
        return wronglogin;
    }

    public static String getWrongLoginPass() {
        String wrongLoginPass = Main.plugin.getConfig().getString("string.wrong_login_pass");
        wrongLoginPass = new String(wrongLoginPass.getBytes(), StandardCharsets.UTF_8);
        return wrongLoginPass;
    }

    public static String getErrorLogin() {
        String errorLogin = Main.plugin.getConfig().getString("string.error_login");
        errorLogin = new String(errorLogin.getBytes(), StandardCharsets.UTF_8);
        return errorLogin;
    }

    public static String getAfterLoginTitle() {
        String afterLoginTitle = Main.plugin.getConfig().getString("string.after_login_title");
        afterLoginTitle = new String(afterLoginTitle.getBytes(), StandardCharsets.UTF_8);
        return afterLoginTitle;
    }

    public static String getAfterLoginSubtitles() {
        String afterLoginSubtitles = Main.plugin.getConfig().getString("string.after_login_subtitles");
        afterLoginSubtitles = new String(afterLoginSubtitles.getBytes(), StandardCharsets.UTF_8);
        return afterLoginSubtitles;
    }

    @Deprecated
    public static void sendLogin(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + MessageLogin.getLoginTime() * 20 + " 10 ");
        p.sendTitle(MessageLogin.getLoginTitle(), MessageLogin.getLoginSubtitles());
    }

    @Deprecated
    public static void sendAfterLogin(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + player_name + " times 10 20 10 ");
        p.sendTitle(MessageLogin.getAfterLoginTitle(), MessageLogin.getAfterLoginSubtitles());
    }

    public static String getWrongPassTentativeKick(){
        String wrongPassTentativekick = Main.plugin.getConfig().getString("string.kick_tentative_login");
        wrongPassTentativekick = new String(wrongPassTentativekick.getBytes(), StandardCharsets.UTF_8);
        return wrongPassTentativekick;
        
    }

    public static int getWrongPassTentativeNumber() {
        int tentativeNumber = Integer.parseInt(Main.plugin.getConfig().getString("string.number_tentative_login"));
        return tentativeNumber;
    }

    public static String getAlreadyLogin() {
        String alreadyLogin = Main.plugin.getConfig().getString("string.already_login");
        alreadyLogin = new String(alreadyLogin.getBytes(), StandardCharsets.UTF_8);
        return alreadyLogin;
    }



    

    public static int getSpwanX() {
        int spawnLogin = Integer.parseInt(Main.plugin.getConfig().getString("string.spawnX"));
        return spawnLogin;
    }
    public static int getSpwanY() {
        int spawnLogin = Integer.parseInt(Main.plugin.getConfig().getString("string.spawnY"));
        return spawnLogin;
    }
    public static int getSpwanZ() {
        int spawnLogin = Integer.parseInt(Main.plugin.getConfig().getString("string.spawnZ"));
        return spawnLogin;
    }
}
