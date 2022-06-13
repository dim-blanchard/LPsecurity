package fr.loirelique.lpsecurity.Usefull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.loirelique.lpsecurity.Main;

public class DataListPlayers {

    public static void setFile(String uuidPlayers , Player p) {
        String nameFile = "Players.yml";
        final String chemainFile = Main.plugin.getDataFolder().toString() + Main.plugin.dataListPlayers;
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("[LPsecurity] File " + nameFile + " Exists.");
            if (getObjectPlayers(uuidPlayers).equals(null)) {
                List<HashMap<String,Player>>listPlayers=new ArrayList<HashMap<String,Player>>();
                listPlayers = getDataFile();
                if (listPlayers.get(0)==null) {
                    listPlayers.remove(0);
                    listPlayers.add(new HashMap<String,Player>());
                    listPlayers.get(0).put(uuidPlayers, p);
                }else{
                    System.out.println(listPlayers);
                    listPlayers.get(0).put(uuidPlayers,p);
                }      
                fileConfiguration.set("list",listPlayers);
                try {
                    fileConfiguration.save(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        } else {
            System.out.println("[LPsecurity] File " + nameFile + " Not Exists.");
            List<HashMap<String,Player>> listPlayers = new ArrayList<HashMap<String,Player>>();
            listPlayers.add(new HashMap<String,Player>());
            listPlayers.get(0).put(uuidPlayers, p);
            fileConfiguration.set("list", listPlayers);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("[LPsecurity] File " + nameFile + " Was Create.");
        }
    }

    public static List<HashMap<String,Player>> getDataFile() {
        String nameFile = "Players.yml";
        List<HashMap<String,Player>> listPlayers = new ArrayList<HashMap<String,Player>>();
        final String chemainFile = Main.plugin.getDataFolder().toString() + Main.plugin.dataListPlayers;
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            listPlayers = (List<HashMap<String, Player>>) fileConfiguration.getList("list");
        }

        return listPlayers;
    }


    public static Player getObjectPlayers(String uuidPlayers) {
        List<HashMap<String,Player>> listPlayers = new ArrayList<HashMap<String,Player>>();
        String nameFile = "Players.yml";
        Player objectPlayers = null;
        final String chemainFile = Main.plugin.getDataFolder().toString() + Main.plugin.dataListPlayers;
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            listPlayers = (List<HashMap<String, Player>>) fileConfiguration.getList("list");
            if (listPlayers.get(0)!=null) {
                if (listPlayers.get(0).get(uuidPlayers)==null) {
                    return objectPlayers=null;
                }else{
                    objectPlayers = listPlayers.get(0).get(uuidPlayers);
                }
            }else{
                return objectPlayers=null;
            }
            
            
        }

        return objectPlayers;
    }

    public static void removeObjectPlayers(String uuidPlayers) {
        List<HashMap<String,Player>> listPlayers = new ArrayList<HashMap<String,Player>>();
        String nameFile = "Players.yml";
        final String chemainFile = Main.plugin.getDataFolder().toString() + Main.plugin.dataListPlayers;
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            listPlayers = (List<HashMap<String, Player>>) fileConfiguration.getList("list");
            if (listPlayers.get(0).get(uuidPlayers)!=null) {
                listPlayers.get(0).remove(uuidPlayers);
                fileConfiguration.set("list",listPlayers);
                try {
                    fileConfiguration.save(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        }
    }

}
