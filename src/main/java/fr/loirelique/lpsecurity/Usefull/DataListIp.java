package fr.loirelique.lpsecurity.Usefull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import fr.loirelique.lpsecurity.Main;
import fr.loirelique.lpsecurity.String.MessageKick;

public class DataListIp {
    /////////////////////////////////////////
    public static List<String> getFileIp(String nameFiles) {
        List<String> value = new ArrayList<String>();
        final String chemainFile = Main.plugin.getDataFolder().toString() +Main.plugin.dataListIp;
        final String nameFile = nameFiles + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            value = (List<String>) fileConfiguration.getList("list");
        }
        return value;
    }

    public static void setFile(String uuidPlayers, String ipPlayers, AsyncPlayerPreLoginEvent p_event) {
        final String chemainFile = Main.plugin.getDataFolder().toString() + Main.plugin.dataListIp;
        final String nameFile = ipPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            Boolean testip = false;
            System.out.println("[LPsecurity] File Data List Ip Exists.");
            List<String> ip = new ArrayList<String>();
            ip = getFileIp(ipPlayers);
            for(int i=0;i<ip.size();i++){
                System.out.println(ip.get(i));
                System.out.println(ipPlayers);
                String getIp = ip.get(i);
                if (getIp.equals(uuidPlayers)==true) {
                    testip=true;               
                }
            }
            if (testip==false) {
                if (ip.size()==MessageKick.getKickOverIp()) {
                   p_event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                                                MessageKick.getKickIp());
                    DataPlayersFiles.setIsOnline(uuidPlayers, false, Main.plugin.dataPlayer);
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
            final List<String> ip = new ArrayList<String>();
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


    public static void removeFileOrPlayers(String uuidPlayers, String ipPlayers) {

        final String chemainFile = Main.plugin.getDataFolder().toString() + Main.plugin.dataListIp;
        final String nameFile = ipPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        if (file.exists() == true) {
            List<String> Listip = new ArrayList<String>();
            Listip = getFileIp(ipPlayers);
            for(int i=0;i<Listip.size();i++){
                String getIp = Listip.get(i);
                if (getIp.equals(uuidPlayers)==true) {
                    Listip.remove(i);          
                }
            }
            if (Listip.size()==0) {
                fileConfiguration.set("list", Listip);
                try {
                    fileConfiguration.save(file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{           
                fileConfiguration.set("list",Listip);
                try {
                    fileConfiguration.save(file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    public static void setFile(String uuidPlayers, String ipPlayers, Player p_event) {
        final String chemainFile = Main.plugin.getDataFolder().toString() + Main.plugin.dataListIp;
        final String nameFile = ipPlayers + ".yml";
        final File file = new File(chemainFile, nameFile);
        final YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (file.exists() == true) {
            Boolean testip = false;
            System.out.println("[LPsecurity] File Data List Ip Exists.");
            List<String> ip = new ArrayList<String>();
            ip = getFileIp(ipPlayers);
            for(int i=0;i<ip.size();i++){
                System.out.println(ip.get(i));
                System.out.println(ipPlayers);
                String getIp = ip.get(i);
                if (getIp.equals(uuidPlayers)==true) {
                    testip=true;
                    System.out.println(testip);               
                }else{
                    System.out.println(testip);
                }
            }
            System.out.println(testip);
            if (testip==false) {
                if (ip.size()==MessageKick.getKickOverIp()) {
                p_event.kickPlayer(MessageKick.getKickIp());
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
}


