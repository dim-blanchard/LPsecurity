package fr.loirelique.lpsecurity.Usefull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.String.MessageKick;

public class DataListFiles {

     /////////////////////////////////////////
     public static List<String> getIp(String nameFiles) {
        List<String> value = new ArrayList<String>();
        final String chemainFile = Main.plugin.getDataFolder().toString() +"/DataList/Ip";
        final String nameFile = nameFiles + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Get uuid from ip list");
            value = (List<String>) fileConfiguration.getList("list");
        }
        return value;
    }

    public static void setIp(String uuidPlayers, String ipPlayers) {
        final String chemainFile = Main.plugin.getDataFolder().toString() + "/DataList/Ip";
        final String nameFile = ipPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            Boolean testip = false;
            System.out.println("[LPsecurity] File Data List Ip Exists.");
            List<String> ip = new ArrayList<String>();
            ip = getIp(ipPlayers);
            for(int i=0;i<ip.size();i++){
                System.out.println(ip.get(i));
                String getIp = ip.get(i);
                if (getIp.equals(ipPlayers)==true) {
                    testip=true;
                    System.out.println(testip);               
                }else{
                    System.out.println(testip);
                }
            }
            System.out.println(testip);
            if (testip==false) {
                if (ip.size()==MessageKick.getKickOverIp()) {
                    System.out.println("Kick Le Joueur");
                   // p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                   //                             MessageKick.getKickIp());
                }else{
                ip.add(uuidPlayers);
                fileConfiguration.set("list",ip);
                try {
                    fileConfiguration.save(file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }
            }

            
        } else {
            System.out.println("[LPsecurity] File Data List Ip Not Exists.");
            final List ip = new ArrayList<String>();
            ip.add(uuidPlayers);
            fileConfiguration.set("list", ip);
            try {
                fileConfiguration.save(file);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("[LPsecurity] File Data List Ip Was Create.");
        }
    }

    public static void supportJoin(String chemainFiles, String nameSupport, String uuidPlayers, String Players) {
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = nameSupport + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("[LPsecurity] File Data List Support Exists.");
            List player = new ArrayList<String>();
            player = fileConfiguration.getList("list");
            player.add(uuidPlayers + "&" + Players);
            fileConfiguration.set("list", player);
            try {
                fileConfiguration.save(file);
                System.out.println("[LPsecurity] File Data List Support Was Update.");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
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
            final List player = new ArrayList<String>();
            player.add(uuidPlayers + "&" + Players);
            fileConfiguration.set("list", player);
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
    public static HashMap getSupport(String nameFiles) {
        HashMap<String, String> myMap = new HashMap<String, String>();
        String test = "null";
        List value = new ArrayList<String>();
        final String chemainFile = Main.plugin.getDataFolder().toString() + "/DataList/Support";
        final String nameFile = nameFiles + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Get String key");
            value = fileConfiguration.getList("list");
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

    public static boolean setListSupport(String nameFiles, String key, String chemainFiles) {
        boolean error = false;
        HashMap<String, String> myMap = new HashMap<String, String>();
        String str = "null";
        List value = new ArrayList<String>();
        final String chemainFile = Main.plugin.getDataFolder().toString() + chemainFiles;
        final String nameFile = nameFiles + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            System.out.println("Get String key");
            value = fileConfiguration.getList(key);
        }
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
        }

        return error;
    }

   

}
