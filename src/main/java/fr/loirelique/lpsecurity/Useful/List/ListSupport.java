package fr.loirelique.lpsecurity.Useful.List;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class ListSupport {

    private static HashMap<String, HashMap<String, Player>> listSupport = new HashMap<String, HashMap<String, Player>>();

    public static void creatSupport(String nom, Player player) {

        Object lisObject[] = listSupport.keySet().toArray();
        String list = "";
        for (int i = 0; i < lisObject.length; i++) {
            list = lisObject[i].toString();
            if (listSupport.get(list).get(nom) == null) {
                System.out.println("Pas de joueur dans list");
                if (listSupport.get(nom) == null) {
                    listSupport.put(nom, new HashMap<String, Player>());
                    listSupport.get(nom).put(player.getName(), player);
                } else {
                    player.sendMessage("Support deja exsistant choisir un autre nom.");
                }
            } else {
               player.sendMessage("Tu es deja dans un support quit le pour en créé un nouveau.");

            }
        }

    }

    public static String getlistSupport() {
        StringBuilder builder = new StringBuilder();
        Object lisObject[] = listSupport.keySet().toArray();
        String list = "";
        for (int i = 0; i < lisObject.length; i++) {
            String ar = "[Support][" + i + "]:" + lisObject[i].toString().replace(" ' ", " \' ");
            builder.append(ar).append(" \n");
            list = builder.toString();
        }
        return list;
    }

    public static void joinSupport(String nom, Player player) {
        if (listSupport.get(nom) != null) {
            if (listSupport.get(nom).get(player.getName()) == null) {
                listSupport.get(nom).put(player.getName(), player);
            } else {
                player.sendMessage("Tu fais deja partie du support.");
            }
        } else {
            player.sendMessage("le nom de support n'existe pas.");
        }
    }

    public static void removeSupport(String nom, Player player) {
        if (listSupport.get(nom) != null) {
            listSupport.remove(nom);
        } else {
            player.sendMessage("le nom de support n'existe pas.");
        }
    }

}
