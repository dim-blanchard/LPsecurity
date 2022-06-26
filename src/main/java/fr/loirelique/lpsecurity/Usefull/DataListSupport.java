package fr.loirelique.lpsecurity.usefull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class DataListSupport {
    
    public static void create(String reason, Player p) { 
        final String uuidPlayers = Main.plugin.getUuidHash(p);      
        final String nameFile = p.getName()+".yml";
        final String chemainFile = Main.plugin.getDataFolder().toString()+Main.plugin.dataListSupport;
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        List<HashMap<String, Player>> listSupportPlayers = new ArrayList<HashMap<String, Player>>();
        listSupportPlayers.add(new HashMap<String,Player>());
        listSupportPlayers.get(0).put(uuidPlayers, p);
        if (file.exists() == false) {
            fileConfiguration.set("reason",reason );
            fileConfiguration.set("list",listSupportPlayers);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } 
    }

}
