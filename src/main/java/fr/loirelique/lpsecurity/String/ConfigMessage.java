package fr.loirelique.lpsecurity.String;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class ConfigMessage {
    


    /**
     * Getter de message de configuration.
     */
    public static String getRegisterTitle() {
        String titre = Main.plugin.getConfig().getString("string.register_title");
        return titre;
    }
    public static String getRegisterSubtitles() {
        String soustitre = Main.plugin.getConfig().getString("string.register_subtitles");
        return soustitre;
    }
    public static int getRegisterTime() {
        int registertemps = Integer.parseInt(Main.plugin.getConfig().getString("string.register_time"));
        return registertemps;
    }

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



    public static String getErrorRegisterPass() {
        String errorRegisterPass = Main.plugin.getConfig().getString("string.error_register_pass");
        return errorRegisterPass;
    }
    public static String getErrorRegister() {
        String errorRegister = Main.plugin.getConfig().getString("string.error_register");
        return errorRegister;
    } 

    public static String getErrorLogin() {
        String errorlogin = Main.plugin.getConfig().getString("string.error_login");
        return errorlogin;
    }

    public static String getErrorLoginPass() {
        String errorLoginPass = Main.plugin.getConfig().getString("string.error_login_pass");
        return errorLoginPass;
    } 


    public static String getKickOnline() {
        String kickOnline = Main.plugin.getConfig().getString("string_kick.online");
        return kickOnline;
    }

    public static String getKickIp() {
        String kickIp = Main.plugin.getConfig().getString("string_kick.Ip");
        return kickIp;
    }

    public static String getKickBan() {
        String kickBan = Main.plugin.getConfig().getString("string_kick.ban");
        return kickBan;
    }

    public static String getKickOvertime() {
        String kickOvertime = Main.plugin.getConfig().getString("string_kick.over_time");
        return kickOvertime;
    } 

    public static int getKickOverIp() {
        int kickOverIp = Integer.parseInt(Main.plugin.getConfig().getString("string_kick.over_ip"));
        return kickOverIp;
    }
    

    public static String getAfterLoginTitle() {
        String afterLoginTitle = Main.plugin.getConfig().getString("string.after_login_title");
        return afterLoginTitle;
    }
    
    public static String getAfterLoginSubtitles() {
        String afterLoginSubtitles = Main.plugin.getConfig().getString("string.after_login_subtitles");
        return afterLoginSubtitles;
    } 

    public static void sendRegister(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title " + player_name + " times 10 " + ConfigMessage.getRegisterTime() * 20 + " 10 "); 
        p.sendTitle(ConfigMessage.getRegisterTitle(),ConfigMessage.getRegisterSubtitles()); 
    }
    
    public static void sendLogin(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title " + player_name + " times 10 " + ConfigMessage.getLoginTime() * 20 + " 10 "); 
        p.sendTitle(ConfigMessage.getLoginTitle(), ConfigMessage.getLoginSubtitles()); 
    }

    public static void sendAfterLogin(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title " + player_name + " times 10 20 10 "); 
        p.sendTitle(ConfigMessage.getAfterLoginTitle(),ConfigMessage.getAfterLoginSubtitles()); 
    }


    public static String getSel() {
        String sel = Main.plugin.getConfig().getString("security.sel");

        return sel;
    }


}
