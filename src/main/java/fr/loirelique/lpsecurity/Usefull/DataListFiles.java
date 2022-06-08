package fr.loirelique.lpsecurity.Usefull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.loirelique.lpsecurity.Main;

public class DataListFiles {
    
    public static void setIp(String uuidPlayers,String ipPlayers, String chemainFiles) {
        final String chemainFile = Main.plugin.getDataFolder().toString() +chemainFiles;
        final String nameFile = ipPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("[LPsecurity] File Data List Ip Exists.");

        } else {
            System.out.println("[LPsecurity] File Data List Ip Not Exists.");
            final List ip = new ArrayList<String>();
            ip.add(uuidPlayers);
            fileConfiguration.set("list",ip);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("[LPsecurity] File Data List Ip Was Create.");
        }
    }

    public static void setSupport(String uuidPlayers,String nameSupport, String chemainFiles) {
        final String chemainFile = Main.plugin.getDataFolder().toString() +chemainFiles;
        final String nameFile = nameSupport + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("[LPsecurity] File Data List Support Exists.");

        } else {
            System.out.println("[LPsecurity] File Data List Support Not Exists.");
            final List player = new ArrayList<String>();
            player.add(uuidPlayers);
            fileConfiguration.set("list",player);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("[LPsecurity] File Data List Support Was Create.");
        }
    }

    /////////////////////////////////////////
public static List getKeyList(String nameFiles, String key, String chemainFiles) {    
    List value = new ArrayList<String>();
    final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
    final String nameFile = nameFiles + ".yml";
    final File file = new File(chemainFile, nameFile);
    final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
    if (file.exists() == true) {
        System.out.println("Get String key");
        value = fileConfiguration.getList(key);
    }
    return value; 
}
///////////////////////
public static List getKeyListTest(String nameFiles, String key, String chemainFiles) {
    HashMap<String,String> myMap = new HashMap<String,String>();
    String test ="null";   
    List value = new ArrayList<String>();
    final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
    final String nameFile = nameFiles + ".yml";
    final File file = new File(chemainFile, nameFile);
    final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
    if (file.exists() == true) {
        System.out.println("Get String key");
        value = fileConfiguration.getList(key);
    }
    test = fileConfiguration.get(key).toString();
    test = test.substring(1, (test.length()-1));
    test = test.replaceAll(" ","");
    System.out.println(test);
    String[] pairs = test.split(",");
    for (int i = 0; i < pairs.length; i++) {
        String pair = pairs[i];
        String[] keyValue = pair.split("&");
        myMap.put(keyValue[0], keyValue[1]);

        System.out.println(keyValue[0] +""+keyValue[1] );}
    

    return value; 
}
}
