package fr.loirelique.lpsecurity.String;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class MessageRegister {
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

    public static void sendRegister(Player p) {
        String player_name = p.getName();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title " + player_name + " times 10 " + MessageRegister.getRegisterTime() * 20 + " 10 ");
        p.sendTitle(MessageRegister.getRegisterTitle(), MessageRegister.getRegisterSubtitles());
    }

    public static String getErrorRegisterPass() {
        String errorRegisterPass = Main.plugin.getConfig().getString("string.error_register_pass");
        return errorRegisterPass;
    }

    public static String getErrorRegister() {
        String errorRegister = Main.plugin.getConfig().getString("string.error_register");
        return errorRegister;
    }
}
