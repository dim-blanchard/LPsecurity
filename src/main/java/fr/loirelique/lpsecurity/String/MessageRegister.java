package fr.loirelique.lpsecurity.String;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class MessageRegister {
    public static String getRegisterTitle() {
        String titre = Main.plugin.getConfig().getString("string.title_register");
        return titre;
    }

    public static String getRegisterSubtitles() {
        String soustitre = Main.plugin.getConfig().getString("string.subtitles_register");
        return soustitre;
    }

    public static int getRegisterTime() {
        int registertemps = Integer.parseInt(Main.plugin.getConfig().getString("string.time_register"));
        return registertemps;
    }

    public static void sendRegister(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + MessageRegister.getRegisterTime() * 20 + " 10 ");
        p.sendTitle(MessageRegister.getRegisterTitle(), MessageRegister.getRegisterSubtitles());
    }

    public static String getwrongRegisterPass() {
        String wrongRegisterPass = Main.plugin.getConfig().getString("string.wrong_register_pass");
        return wrongRegisterPass;
    }

    public static String getwrongRegister() {
        String wrongRegister = Main.plugin.getConfig().getString("string.wrong_register");
        return wrongRegister;
    }

    public static String getErrorRegister() {
        String errorRegister = Main.plugin.getConfig().getString("string.error_register");
        return errorRegister;
    }
}
