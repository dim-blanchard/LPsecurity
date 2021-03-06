package fr.loirelique.lpsecurity.usefull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.loirelique.lpsecurity.Main;


public class DataListFiles {

     
    @SuppressWarnings("unchecked")
    public static void supportJoin(String chemainFiles, String nameSupport, String uuidPlayers, String Players) {
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = nameSupport + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("[LPsecurity] File Data List Support Exists.");
            List<String> player = new ArrayList<String>();
            player =  (List<String>) fileConfiguration.getList("list");
            player.add(uuidPlayers + "&" + Players);
            fileConfiguration.set("list", player);
            try {
                fileConfiguration.save(file);
                System.out.println("[LPsecurity] File Data List Support Was Update.");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } else {
            System.out.println("Ce support n'existe pas.");
        }
    }

    //////////////////////////////
    public static void setSupport(String chemainFiles, String nameSupport, String uuidPlayers, String Players) {
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = nameSupport + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("[LPsecurity] File Data List Support Exists.");
        } else {
            System.out.println("[LPsecurity] File Data List Support Not Exists.");
            final List<String> player = new ArrayList<String>();
            player.add(uuidPlayers + "&" + Players);
            fileConfiguration.set("list", player);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("[LPsecurity] File Data List Support Was Create.");
        }
    }

    /////////////////////////////////////////
    @SuppressWarnings("unchecked")
    public static List<String> getKeyList(String nameFiles, String key, String chemainFiles) {
        List<String> value = new ArrayList<String>();
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = nameFiles + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            value = (List<String>) fileConfiguration.getList(key);
        }
        return value;
    }

    ///////////////////////
    public static HashMap<String,String> getSupport(String nameFiles) {
        HashMap<String, String> myMap = new HashMap<String, String>();
        String test = "null";
        final String chemainFile = Main.plugin.getDataFolder().toString() + "/DataList/Support";
        final String nameFile = nameFiles + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
        }
        test = fileConfiguration.get("list").toString();
        test = test.substring(1, (test.length() - 1));
        test = test.replaceAll(" ", "");
        System.out.println(test);
        String[] pairs = test.split(",");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split("&");
            myMap.put(keyValue[0], keyValue[1]);

            System.out.println(keyValue[0] + "" + keyValue[1]);
        }

        return myMap;
    }

/*     @SuppressWarnings("unchecked")
    public static boolean setListSupport(String nameFiles, String key, String chemainFiles) {
        boolean error = false;
        HashMap<String, String> myMap = new HashMap<String, String>();
        String str = "null";
        List<String> value = new ArrayList<String>();
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = nameFiles + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Get String key");
            value = (List<String>) fileConfiguration.getList(key);
        
        str = fileConfiguration.get(key).toString();
        str = str.substring(1, (str.length() - 1));
        str = str.replaceAll(" ", "");
        System.out.println(str);
        String[] pairs = str.split(",");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split("&");
            myMap.put(keyValue[0], keyValue[1]);

            System.out.println(keyValue[0] + "" + keyValue[1]);
        }}

        return error;
    } */

   

}
