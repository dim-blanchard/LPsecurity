package fr.loirelique.lpsecurity.string;

import java.nio.charset.StandardCharsets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class MessageRegister {
    public static String getRegisterTitle() {
        String titre = Main.plugin.getConfig().getString("string.title_register");
        titre = new String(titre.getBytes(), StandardCharsets.UTF_8);
        return titre;
    }

    public static String getRegisterSubtitles() {
        String soustitre = Main.plugin.getConfig().getString("string.subtitles_register");
        soustitre = new String(soustitre.getBytes(), StandardCharsets.UTF_8);
        return soustitre;
    }

    public static int getRegisterTime() {
        int registertemps = Integer.parseInt(Main.plugin.getConfig().getString("string.time_register"));
        return registertemps;
    }

    @Deprecated
    public static void sendRegister(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + MessageRegister.getRegisterTime() * 20 + " 10 ");
        p.sendTitle(MessageRegister.getRegisterTitle(), MessageRegister.getRegisterSubtitles());
    }

    public static String getwrongRegisterPass() {
        String wrongRegisterPass = Main.plugin.getConfig().getString("string.wrong_register_pass");
        wrongRegisterPass = new String(wrongRegisterPass.getBytes(), StandardCharsets.UTF_8);
        return wrongRegisterPass;
    }

    public static String getwrongRegister() {
        String wrongRegister = Main.plugin.getConfig().getString("string.wrong_register");
        wrongRegister = new String(wrongRegister.getBytes(), StandardCharsets.UTF_8);
        return wrongRegister;
    }

    public static String getErrorRegister() {
        String errorRegister = Main.plugin.getConfig().getString("string.error_register");
        errorRegister = new String(errorRegister.getBytes(), StandardCharsets.UTF_8);
        return errorRegister;
    }
}
